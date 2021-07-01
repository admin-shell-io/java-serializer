/*
Copyright (c) 2021 Fraunhofer IOSB-INA Lemgo,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IOSB-ILT Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IAIS,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IESE,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IWU Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

This source code is licensed under the Apache License 2.0 (see LICENSE.txt).

This source code may use other Open Source software components (see LICENSE.txt).
*/

package io.adminshell.aas.v3.dataformat.json;

import io.adminshell.aas.v3.model.Constraint;
import io.adminshell.aas.v3.model.DataSpecificationContent;
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
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class to collect relevant data needed for
 * ReflectionAnnotationIntrospector via reflection.
 */
public class ReflectionHelper {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionHelper.class);
    private static final String ROOT_PACKAGE_NAME = "io.adminshell.aas.v3";
    /**
     * Name of package where the generated model classes are defined
     */
    public static final String MODEL_PACKAGE_NAME = ROOT_PACKAGE_NAME + ".model";
    /**
     * Name of package where the generated default implementation files are
     * defined
     */
    public static final String DEFAULT_IMPLEMENTATION_PACKAGE_NAME = MODEL_PACKAGE_NAME + ".impl";
    /**
     * Name of package where the mixins are defined. These mixins are
     * automatically added to JsonSerializer and JsonDeserializer.
     */
    public static final String MIXINS_PACKAGE_NAME = ROOT_PACKAGE_NAME + ".dataformat.json.mixins";
    /**
     * Suffix that identifies a class as a mixin.
     */
    public static final String MIXIN_SUFFIX = "Mixin";
    /**
     * Prefix that defines a class as a default implementation
     */
    public static final String DEFAULT_IMPLEMENTATION_PREFIX = "Default";
    /**
     * Distinct root superclasses of which classify a class to include type
     * informatino via the modelType property
     */
    public static final Set<Class<?>> MODEL_TYPE_SUPERCLASSES = Set.of(Referable.class, Constraint.class);
    /**
     * Expanded list of all classes that shall be annotated with the modelType
     * property.
     */
    public static final Set<Class<?>> TYPES_WITH_MODEL_TYPE;
    /**
     * Map of all interfaces and their subinterfaces defined in the
     * MODEL_PACKAGE_NAME package.
     */
    public static final Map<Class<?>, Set<Class<?>>> SUBTYPES;
    /**
     * Expanded list of all mixin classes defined in the MIXINS_PACKAGE_NAME
     * package together with the corresponding class they should be applied to.
     */
    public static final Map<Class<?>, Class<?>> MIXINS;
    /**
     * Expanded list of all default implementations in the
     * DEFAULT_IMPLEMENTATION_PACKAGE_NAME package together with the interface
     * from the MODEL_PACKAGE_NAME package they are implementing.
     */
    public static final List<ImplementationInfo> DEFAULT_IMPLEMENTATIONS;
    /**
     * List of interfaces from the MODEL_PACKAGE_NAME package that are known to
     * not have any default implementation and therefore are excluded
     * explicitely.
     */
    public static final Set<Class<?>> INTERFACES_WITHOUT_DEFAULT_IMPLEMENTATION; // = List.of(DataSpecificationContent.class);
    /**
     * List of enums from the MODEL_PACKAGE_NAME package.
     */
    public static final List<Class<Enum>> ENUMS;

    public static class ImplementationInfo<T> {

        private final Class<T> interfaceType;
        private final Class<? extends T> implementationType;

        protected ImplementationInfo(Class<T> interfaceType, Class<? extends T> implementationType) {
            this.interfaceType = interfaceType;
            this.implementationType = implementationType;
        }

        public Class<T> getInterfaceType() {
            return interfaceType;
        }

        public Class<? extends T> getImplementationType() {
            return implementationType;
        }
    }

    /**
     * Returns whether the given class is an interface and from within the
     * MODEL_PACKAGE_NAME package
     *
     * @param type the class to check
     * @return whether the given class is an interface and from within the
     * MODEL_PACKAGE_NAME package
     */
    public static boolean isModelInterface(Class<?> type) {
        return type.isInterface() && MODEL_PACKAGE_NAME.equals(type.getPackageName());
    }

    /**
     * Returns whether the given class is a default implementation or not
     *
     * @param type the class to check
     * @return whether the given class is a default implementation or not
     */
    public static boolean isDefaultImplementation(Class<?> type) {
        return DEFAULT_IMPLEMENTATIONS.stream().anyMatch(x -> Objects.equals(x.getImplementationType(), type));
    }

    /**
     * Returns whether the given interface has a default implementation or not
     *
     * @param interfaceType the interface to check
     * @return whether the given interface has a default implementation or not
     */
    public static boolean hasDefaultImplementation(Class<?> interfaceType) {
        return DEFAULT_IMPLEMENTATIONS.stream().anyMatch(x -> x.getInterfaceType().equals(interfaceType));
    }

    /**
     * Returns whether the given class is an interface from within the
     * MODEL_PACKAGE_NAME package as well as a default implementation or not
     *
     * @param type the class to check
     * @return whether the given class is an interface from within the
     * MODEL_PACKAGE_NAME package as well as a default implementation or not
     */
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
        ENUMS = modelScan.getAllEnums().loadClasses(Enum.class);
        INTERFACES_WITHOUT_DEFAULT_IMPLEMENTATION = getInterfacesWithoutDefaultImplementation(modelScan);
    }

    private static Set<Class<?>> getInterfacesWithoutDefaultImplementation(ScanResult modelScan) {
        return modelScan.getAllInterfaces().loadClasses().stream()
                .filter(x -> !hasDefaultImplementation(x))
                .collect(Collectors.toSet());
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
                    String interfaceName = x.getSimpleName().substring(DEFAULT_IMPLEMENTATION_PREFIX.length());//using conventions
                    ClassInfoList interfaceClassInfos = modelScan.getAllClasses().filter(y -> y.isInterface() && Objects.equals(y.getSimpleName(), interfaceName));
                    if (interfaceClassInfos.isEmpty()) {
                        logger.warn("could not find interface realized by default implementation class '{}'", x.getSimpleName());
                    } else {
                        Class<?> implementedClass = interfaceClassInfos.get(0).loadClass();
                        defaultImplementations.add(new ImplementationInfo(implementedClass, x));
                        logger.info("using default implementation class '{}' for interface '{}'",
                                x.getSimpleName(),
                                interfaceClassInfos.get(0).getName());

                    }
                });
        return defaultImplementations;
    }

    private static Map<Class<?>, Class<?>> scanMixins(ScanResult modelScan) {
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
                    if (modelClassInfos.isEmpty()) {
                        logger.warn("could not auto-resolve target class for mixin '{}'", x.getSimpleName());
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
        return modelScan.getAllInterfaces().stream()
                .filter(ReflectionHelper::hasSubclass)
                .collect(Collectors.toMap(x -> x.loadClass(), ReflectionHelper::getSubclasses));
    }

    private static Set<Class<?>> getSubclasses(ClassInfo clazzInfo) {
        return clazzInfo.getClassesImplementing()
                .directOnly()
                .filter(x -> x.isInterface())
                .loadClasses()
                .stream()
                .collect(Collectors.toSet());
    }

    private static boolean hasSubclass(ClassInfo clazzInfo) {
        return !getSubclasses(clazzInfo).isEmpty();
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
