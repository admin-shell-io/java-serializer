package io.adminshell.aas.v3.dataformat.json.serialization;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;

import io.adminshell.aas.v3.model.Referable;

public class SerializationAnnotationIntrospector extends JacksonAnnotationIntrospector {

	@Override
	public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> config, AnnotatedClass ac, JavaType baseType) {
		if (ac.getAnnotations().get(JsonTypeInfo.class) != null) {
			Class<?> c = ac.getRawType();
			if (!Referable.class.isAssignableFrom(c)) {
				return null;
			}
		}
		return super.findTypeResolver(config, ac, baseType);
	}

}