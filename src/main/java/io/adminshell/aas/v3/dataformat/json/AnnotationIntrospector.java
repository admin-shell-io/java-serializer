package io.adminshell.aas.v3.dataformat.json;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import io.adminshell.aas.v3.model.Referable;
import io.github.classgraph.AnnotationInfo;
import io.github.classgraph.AnnotationParameterValue;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationIntrospector extends JacksonAnnotationIntrospector {

    private static final String MODEL_TYPE_PROPERTY = "modelType";
    private static final String TARGET_PACKAGE_NAME = "io.adminshell.aas.v3.model";

    @Override
    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> config, AnnotatedClass ac, JavaType baseType) {
        if (Referable.class.isAssignableFrom(ac.getRawType())) {
            TypeResolverBuilder<?> result = _constructStdTypeResolverBuilder();
            result = result.init(JsonTypeInfo.Id.NAME, null);
            result.inclusion(JsonTypeInfo.As.PROPERTY);
            result.typeProperty(MODEL_TYPE_PROPERTY);
            result.typeIdVisibility(false);
            return result;
        }
        return super.findTypeResolver(config, ac, baseType);
    }

    @Override
    public String findTypeName(AnnotatedClass ac) {
        // if in package 'io.adminshell.aas.v3.model' add @JsonTypeName("...")
        if (TARGET_PACKAGE_NAME.equals(ac.getRawType().getPackageName())) {
            return ac.getRawType().getSimpleName();
        }
        return super.findTypeName(ac);
    }

    @Override
    public List<NamedType> findSubtypes(Annotated a) {
        ScanResult scanResult = new ClassGraph()
                .enableClassInfo()
                .acceptPackagesNonRecursive(TARGET_PACKAGE_NAME)
                .scan();
//        if (a.getRawType().isInterface())
        ClassInfoList subclasses = scanResult.getClassesImplementing(a.getName());
        List<NamedType> result = subclasses.stream().map(x -> new NamedType(x.loadClass(), x.getSimpleName()))
                .collect(Collectors.toList());
        return result;
//        JsonSubTypes t = _findAnnotation(a, JsonSubTypes.class);
//        if (t == null) {
//            return null;
//        }
//        JsonSubTypes.Type[] types = t.value();
//        ArrayList<NamedType> result = new ArrayList<>(types.length);
//        for (JsonSubTypes.Type type : types) {
//            result.add(new NamedType(type.value(), type.name()));
//        }
//        return result;
    }

}
