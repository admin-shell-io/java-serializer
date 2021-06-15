package io.adminshell.aas.v3.dataformat.json;

import io.adminshell.aas.v3.model.Constraint;
import io.adminshell.aas.v3.model.DataSpecification;
import io.adminshell.aas.v3.model.Referable;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionHelper {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionHelper.class);
    public static final String ROOT_PACKAGE_NAME = "io.adminshell.aas.v3";
    public static final String MODEL_PACKAGE_NAME = ROOT_PACKAGE_NAME + ".model";
    public static final String DEFAULT_IMPLEMENTATION_PACKAGE_NAME = MODEL_PACKAGE_NAME + ".impl";
    public static final String MIXINS_PACKAGE_NAME = ROOT_PACKAGE_NAME + ".dataformat.json.mixins.custom";
    public static final String MIXIN_SUFFIX = "Mixin";
    public static final String DEFAULT_IMPLEMENTATION_PREFIX = "Default";
    public static final Set<Class<?>> MODEL_TYPE_SUPERCLASSES = Set.of(Referable.class, Constraint.class);
    public static final Set<Class<?>> TYPES_WITH_MODEL_TYPE;
    public static final Map<Class<?>, Set<Class<?>>> SUBTYPES;
    public static final Map<Class<?>, Class<?>> MIXINS;
    public static final List<ImplementationInfo> DEFAULT_IMPLEMENTATIONS;
    public static final List<Class<?>> INTERFACES_WITHOUT_DEFAULT_IMPLEMENTATION = List.of(DataSpecification.class);

    public static class ImplementationInfo<T> {

        private final Class<T> implementedClass;
        private final Class<? extends T> implementationClass;

        protected ImplementationInfo(Class<T> implementedClass, Class<? extends T> implementationClass) {
            this.implementedClass = implementedClass;
            this.implementationClass = implementationClass;
        }

        public Class<T> getImplementedClass() {
            return implementedClass;
        }

        public Class<? extends T> getImplementationClass() {
            return implementationClass;
        }
    }

    public static boolean isModelInterface(Class<?> type) {
        return type.isInterface() && MODEL_PACKAGE_NAME.equals(type.getPackageName());
    }

    public static boolean isDefaultImplementation(Class<?> type) {
        return DEFAULT_IMPLEMENTATIONS.stream().anyMatch(x -> Objects.equals(x.getImplementationClass(), type));
    }

    public static boolean isModelInterfaceOrDefaultImplementation(Class<?> type) {
        return isModelInterface(type) || isDefaultImplementation(type);
    }

    static {
        ScanResult modelScan = new ClassGraph()
                .enableClassInfo()
                .acceptPackagesNonRecursive(MODEL_PACKAGE_NAME)
                .scan();
        TYPES_WITH_MODEL_TYPE = scanModelTypes(modelScan);
        SUBTYPES = scanSubtypes(modelScan);
        MIXINS = scanMixins(modelScan);
        DEFAULT_IMPLEMENTATIONS = scanDefaultImplementations(modelScan);
    }

	private static List<ImplementationInfo> scanDefaultImplementations(ScanResult modelScan) {
		ScanResult defaulImplementationScan = new ClassGraph()
                .enableClassInfo()
                .acceptPackagesNonRecursive(DEFAULT_IMPLEMENTATION_PACKAGE_NAME)
                .scan();
		List<ImplementationInfo> defaultImplementations = new ArrayList<>();
        defaulImplementationScan.getAllClasses()
                .filter(x -> x.getSimpleName().startsWith(DEFAULT_IMPLEMENTATION_PREFIX))
                .loadClasses()
                .stream()
                .forEach(x -> {
                    String defaultImplementationClassName = x.getSimpleName().substring(DEFAULT_IMPLEMENTATION_PREFIX.length());
                    ClassInfoList modelClassInfos = modelScan.getAllClasses().filter(y -> y.isInterface() && Objects.equals(y.getSimpleName(), defaultImplementationClassName));
                    if (modelClassInfos == null || modelClassInfos.isEmpty()) {
                        logger.warn("could not find interface realized by default implementation class '{}'", x.getSimpleName());
                    } else if (modelClassInfos.size() > 1) {
                        logger.warn("found multiple potential interfaces realized by default implementation class '{}'. Default implementation class will not be used at all until ambiguity is resolved. (potential interfaces: {})",
                                x.getSimpleName(),
                                modelClassInfos.stream().map(y -> y.getName()).collect(Collectors.joining(", ")));
                    } else {
                        Class<?> implementedClass = modelClassInfos.get(0).loadClass();
                        if (INTERFACES_WITHOUT_DEFAULT_IMPLEMENTATION.contains(implementedClass)) {
                            logger.info("skipping found default implementation class '{}' for interface '{}' because explicitely excluded",
                                    x.getSimpleName(),
                                    modelClassInfos.get(0).getName());
                        } else {
                            defaultImplementations.add(new ImplementationInfo(implementedClass, x));
                            logger.info("using default implementation class '{}' for interface '{}'",
                                    x.getSimpleName(),
                                    modelClassInfos.get(0).getName());
                        }
                    }
                });
        return defaultImplementations;
	}

	private static Map<Class<?>, Class<?>>  scanMixins(ScanResult modelScan) {
		ScanResult mixinScan = new ClassGraph()
                .enableClassInfo()
                .acceptPackagesNonRecursive(MIXINS_PACKAGE_NAME)
                .scan();
		Map<Class<?>, Class<?>> mixins = new HashMap<>();
        mixinScan.getAllClasses()
                .filter(x -> x.getSimpleName().endsWith(MIXIN_SUFFIX))
                .loadClasses()
                .forEach(x -> {
                    String modelClassName = x.getSimpleName().substring(0, x.getSimpleName().length() - MIXIN_SUFFIX.length());
                    ClassInfoList modelClassInfos = modelScan.getAllClasses().filter(y -> Objects.equals(y.getSimpleName(), modelClassName));
                    if (modelClassInfos == null || modelClassInfos.isEmpty()) {
                        logger.warn("could not auto-resolve target class for mixin '{}'", x.getSimpleName());
                    } else if (modelClassInfos.size() > 1) {
                        logger.warn("found multiple target classes for mixin '{}'. Mixin will be applied to all of them. (target classes: {})",
                                x.getSimpleName(),
                                modelClassInfos.stream().map(y -> y.getName()).collect(Collectors.joining(", ")));
                    } else {
                    	mixins.put(modelClassInfos.get(0).loadClass(), x);
                        logger.info("using mixin '{}' for class '{}'",
                                x.getSimpleName(),
                                modelClassInfos.get(0).getName());
                    }
                });
        return mixins;
	}

	private static Map<Class<?>, Set<Class<?>>> scanSubtypes(ScanResult modelScan) {
		Function<ClassInfo, Set<Class<?>>> getSubclasses = x
                -> x.getClassesImplementing()
                        .directOnly()
                        .filter(y -> y.isInterface())
                        .loadClasses()
                        .stream()
                        .collect(Collectors.toSet());
        return modelScan.getAllInterfaces().stream()
                .filter(x -> !getSubclasses.apply(x).isEmpty())
                .collect(Collectors.toMap(x -> x.loadClass(), getSubclasses));
	}

	private static Set<Class<?>> scanModelTypes(ScanResult modelScan) {
	    Set<Class<?>> typesWithModelTypes;
		typesWithModelTypes = MODEL_TYPE_SUPERCLASSES.stream()
                .flatMap(x -> modelScan.getClassesImplementing(x.getName()).loadClasses().stream())
                .collect(Collectors.toSet());
        typesWithModelTypes.addAll(MODEL_TYPE_SUPERCLASSES);
        return typesWithModelTypes;
	}

    private ReflectionHelper() {
    }
}
