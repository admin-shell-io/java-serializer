package io.adminshell.aas.v3.dataformat.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AnnotationIntrospector extends JacksonAnnotationIntrospector {

    private static final String MODEL_TYPE_PROPERTY = "modelType";
    private static final String GETTER_PREFIX = "get";

    @Override
    public String findTypeName(AnnotatedClass ac) {
        String customType = findModelType(ac.getRawType());
        return customType != null
                ? customType
                : super.findTypeName(ac);
    }

    private Class<?> getMostSpecificTypeWithModelType(Class<?> clazz) {
        return ReflectionHelper.TYPES_WITH_MODEL_TYPE.stream()
                .filter(x -> clazz.isInterface() ? x.equals(clazz) : x.isAssignableFrom(clazz))
                .sorted((Class<?> o1, Class<?> o2) -> {
                    // -1: o1 more special than o2
                    // 0: o1 equals o2 or on same samelevel
                    // 1: o2 more special than o1
                    if (o1.isAssignableFrom(o2)) {
                        if (o2.isAssignableFrom(o1)) {
                            return 0;
                        }
                        return 1;
                    }
                    if (o2.isAssignableFrom(o1)) {
                        return -1;
                    }
                    return 0;
                })
                .findFirst()
                .orElse(null);
    }

    private String findModelType(Class<?> clazz) {
        Class<?> type = getMostSpecificTypeWithModelType(clazz);
        if (type != null) {
            return type.getSimpleName();
        }
        for (Class<?> interfaceClass : clazz.getInterfaces()) {
            String result = findModelType(interfaceClass);
            if (result != null) {
                return result;
            }
        }
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {
            return findModelType(superClass);
        }
        return null;
    }

    @Override
    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> config, AnnotatedClass ac, JavaType baseType) {
        String modelType = findModelType(ac.getRawType());
        if (modelType != null) {
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
    public List<NamedType> findSubtypes(Annotated a) {
        if (ReflectionHelper.SUBTYPES.containsKey(a.getRawType()) && !ReflectionHelper.SUBTYPES.get(a.getRawType()).isEmpty()) {
            return ReflectionHelper.SUBTYPES.get(a.getRawType()).stream()
                    .map(x -> new NamedType(x, x.getSimpleName()))
                    .collect(Collectors.toList());
        }
        return super.findSubtypes(a);
    }

    @Override
    public JsonInclude.Value findPropertyInclusion(Annotated a) {
        JsonInclude.Value result = super.findPropertyInclusion(a);
        if (result != JsonInclude.Value.empty()) {
            return result;
        }
        if (AnnotatedMethod.class.isAssignableFrom(a.getClass())) {
            AnnotatedMethod method = (AnnotatedMethod) a;
            if (method.getParameterCount() == 0
                    && method.getName().startsWith(GETTER_PREFIX)
                    && Objects.equals(List.class, method.getRawReturnType())
                    && ReflectionHelper.isModelInterfaceOrDefaultImplementation(method.getDeclaringClass())) {
                return result.withValueInclusion(JsonInclude.Include.NON_EMPTY);
            }
        }
        return result;
    }

}
