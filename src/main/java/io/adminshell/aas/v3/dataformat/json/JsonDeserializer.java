package io.adminshell.aas.v3.dataformat.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.deser.AbstractDeserializer;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.dataformat.Deserializer;
import io.adminshell.aas.v3.dataformat.json.deserialization.DataSpecificationDeserializer;
import io.adminshell.aas.v3.dataformat.json.enums.EnumDeserializer;
import io.adminshell.aas.v3.dataformat.json.enums.IdentifierTypeMapping;
import io.adminshell.aas.v3.dataformat.json.enums.KeyTypeMapping;
import io.adminshell.aas.v3.dataformat.json.enums.ScreamingSnakeCaseEnumNaming;
import io.adminshell.aas.v3.dataformat.json.enums.UpperCamelCaseEnumNaming;
import io.adminshell.aas.v3.dataformat.json.modeltype.ModelTypeProcessor;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.AssetKind;
import io.adminshell.aas.v3.model.Category;
import io.adminshell.aas.v3.model.DataSpecification;
import io.adminshell.aas.v3.model.DataTypeIEC61360;
import io.adminshell.aas.v3.model.EntityType;
import io.adminshell.aas.v3.model.IdentifiableElements;
import io.adminshell.aas.v3.model.IdentifierType;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.KeyType;
import io.adminshell.aas.v3.model.LevelType;
import io.adminshell.aas.v3.model.LocalKeyType;
import io.adminshell.aas.v3.model.ModelingKind;
import io.adminshell.aas.v3.model.PermissionKind;
import io.adminshell.aas.v3.model.ReferableElements;

public class JsonDeserializer implements Deserializer {

    protected JsonMapper mapper;
    protected SimpleAbstractTypeResolver typeResolver;

    public JsonDeserializer() {
        initTypeResolver();
        buildMapper();
    }

    protected void buildMapper() {
        mapper = JsonMapper.builder()
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .annotationIntrospector(new AnnotationIntrospector())
                .addModule(buildEnumModule())
                .addModule(buildImplementationModule())
                .addModule(buildCustomDeserializerModule())
                .build();
        ReflectionHelper.MIXINS.entrySet().forEach(x -> mapper.addMixIn(x.getKey(), x.getValue()));
    }

    protected SimpleModule buildCustomDeserializerModule() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(DataSpecification.class, new DataSpecificationDeserializer());
        return module;
    }

    private void initTypeResolver() {
        typeResolver = new SimpleAbstractTypeResolver();
        ReflectionHelper.DEFAULT_IMPLEMENTATIONS.forEach(x -> typeResolver.addMapping(x.getImplementedClass(), x.getImplementationClass()));
    }

    protected SimpleModule buildEnumModule() {
        SimpleModule module = new SimpleModule();
        // enums with custom naming strategy
        module.addDeserializer(IdentifierType.class, new EnumDeserializer<>(new IdentifierTypeMapping()));
        module.addDeserializer(KeyType.class, new EnumDeserializer<>(new KeyTypeMapping()));
        // enums with screaming snake case naming strategy (e.g. HELLO_WORLD)
        module.addDeserializer(DataTypeIEC61360.class,
                new EnumDeserializer<>(new ScreamingSnakeCaseEnumNaming(DataTypeIEC61360.class)));
        module.addDeserializer(Category.class,
                new EnumDeserializer<>(new ScreamingSnakeCaseEnumNaming(Category.class))); // not used in JSON
        // enums with upper camel case (default) naming strategy (e.g. HelloWorld)
        module.addDeserializer(AssetKind.class, new EnumDeserializer<>(new UpperCamelCaseEnumNaming(AssetKind.class)));
        module.addDeserializer(EntityType.class,
                new EnumDeserializer<>(new UpperCamelCaseEnumNaming(EntityType.class)));
        module.addDeserializer(IdentifiableElements.class,
                new EnumDeserializer<>(new UpperCamelCaseEnumNaming(IdentifiableElements.class))); // not used in JSON
        module.addDeserializer(KeyElements.class,
                new EnumDeserializer<>(new UpperCamelCaseEnumNaming(KeyElements.class))); // not used in JSON
        module.addDeserializer(LevelType.class, new EnumDeserializer<>(new UpperCamelCaseEnumNaming(LevelType.class)));
        module.addDeserializer(LocalKeyType.class,
                new EnumDeserializer<>(new UpperCamelCaseEnumNaming(LocalKeyType.class))); // not used in JSON
        module.addDeserializer(ModelingKind.class,
                new EnumDeserializer<>(new UpperCamelCaseEnumNaming(ModelingKind.class)));
        module.addDeserializer(PermissionKind.class,
                new EnumDeserializer<>(new UpperCamelCaseEnumNaming(PermissionKind.class))); // not used in JSON
        module.addDeserializer(ReferableElements.class,
                new EnumDeserializer<>(new UpperCamelCaseEnumNaming(ReferableElements.class))); // not used in JSON
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
            AbstractDeserializer d;
            throw new DeserializationException("error deserializing AssetAdministrationShellEnvironment", ex);
        }
    }

    @Override
    public <T> void useImplementation(Class<T> aasInterface, Class<? extends T> implementation) {
        typeResolver.addMapping(aasInterface, implementation);
        buildMapper();
    }
}
