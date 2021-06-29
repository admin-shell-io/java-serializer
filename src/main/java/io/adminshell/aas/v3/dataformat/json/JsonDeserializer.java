package io.adminshell.aas.v3.dataformat.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.dataformat.Deserializer;
import io.adminshell.aas.v3.dataformat.json.deserialization.DefaultEmbeddedDataSpecificationDeserializer;
import io.adminshell.aas.v3.dataformat.json.deserialization.EmbeddedDataSpecificationDeserializer;
import io.adminshell.aas.v3.dataformat.json.deserialization.EnumDeserializer;
import io.adminshell.aas.v3.dataformat.json.modeltype.ModelTypeProcessor;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.EmbeddedDataSpecification;
import java.util.Map;

/**
 * Class for deserializing/parsing AAS JSON documents.
 */
public class JsonDeserializer implements Deserializer {

    protected JsonMapper mapper;
    protected SimpleAbstractTypeResolver typeResolver;
    protected static Map<Class<?>, com.fasterxml.jackson.databind.JsonDeserializer> customDeserializers = Map.of(
            EmbeddedDataSpecification.class, new EmbeddedDataSpecificationDeserializer());

    public JsonDeserializer() {
        initTypeResolver();
        buildMapper();
    }

    protected void buildMapper() {
        mapper = JsonMapper.builder()
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .annotationIntrospector(new ReflectionAnnotationIntrospector())
                .addModule(buildEnumModule())
                .addModule(buildImplementationModule())
                .addModule(buildCustomDeserializerModule())
                .build();
        ReflectionHelper.MIXINS.entrySet().forEach(x -> mapper.addMixIn(x.getKey(), x.getValue()));
    }

    protected SimpleModule buildCustomDeserializerModule() {
        SimpleModule module = new SimpleModule();
        customDeserializers.forEach((k, v) -> module.addDeserializer(k, v));
        return module;
    }

    private void initTypeResolver() {
        typeResolver = new SimpleAbstractTypeResolver();
        ReflectionHelper.DEFAULT_IMPLEMENTATIONS
                .stream()
                .filter(x -> !customDeserializers.containsKey(x.getInterfaceType()))
                .forEach(x -> typeResolver.addMapping(x.getInterfaceType(), x.getImplementationType()));
    }

    protected SimpleModule buildEnumModule() {
        SimpleModule module = new SimpleModule();
        ReflectionHelper.ENUMS.forEach(x -> module.addDeserializer(x, new EnumDeserializer<>(x)));
        return module;
    }

    protected SimpleModule buildImplementationModule() {
        SimpleModule module = new SimpleModule();
        module.setAbstractTypes(typeResolver);
        return module;
    }

    @Override
    public AssetAdministrationShellEnvironment read(String value) throws DeserializationException {
        try {
            return mapper.treeToValue(ModelTypeProcessor.preprocess(value), AssetAdministrationShellEnvironment.class);
        } catch (JsonProcessingException ex) {
            throw new DeserializationException("error deserializing AssetAdministrationShellEnvironment", ex);
        }
    }

    @Override
    public <T> void useImplementation(Class<T> aasInterface, Class<? extends T> implementation) {
        typeResolver.addMapping(aasInterface, implementation);
        buildMapper();
    }
}
