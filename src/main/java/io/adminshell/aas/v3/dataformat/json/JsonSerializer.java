package io.adminshell.aas.v3.dataformat.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.adminshell.aas.v3.dataformat.SerializationException;
import io.adminshell.aas.v3.dataformat.Serializer;
import io.adminshell.aas.v3.dataformat.json.enums.EnumSerializer;
import io.adminshell.aas.v3.dataformat.json.enums.IdentifierTypeMapping;
import io.adminshell.aas.v3.dataformat.json.enums.KeyTypeMapping;
import io.adminshell.aas.v3.dataformat.json.enums.ScreamingSnakeCaseEnumNaming;
import io.adminshell.aas.v3.dataformat.json.enums.UpperCamelCaseEnumNaming;
import io.adminshell.aas.v3.dataformat.json.modeltype.ModelTypeProcessor;
import io.adminshell.aas.v3.dataformat.json.serialization.DataSpecificationSerializer;
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

public class JsonSerializer implements Serializer {

    protected JsonMapper mapper;

    public JsonSerializer() {
        buildMapper();
    }

    protected void buildMapper() {
        mapper = JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT)
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .addModule(buildEnumModule())
                .addModule(buildCustomDeserializerModule())
                .annotationIntrospector(new AnnotationIntrospector())
                .build();
        ReflectionHelper.MIXINS.entrySet().forEach(x -> mapper.addMixIn(x.getKey(), x.getValue()));
    }

    protected SimpleModule buildCustomDeserializerModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(DataSpecification.class, new DataSpecificationSerializer());
        return module;
    }

    protected SimpleModule buildEnumModule() {
        SimpleModule module = new SimpleModule();
        // enums with custom naming strategy
        module.addSerializer(IdentifierType.class, new EnumSerializer<>(new IdentifierTypeMapping()));
        module.addSerializer(KeyType.class, new EnumSerializer<>(new KeyTypeMapping()));
        // enums with screaming snake case naming strategy (e.g. HELLO_WORLD)
        module.addSerializer(DataTypeIEC61360.class,
                new EnumSerializer<>(new ScreamingSnakeCaseEnumNaming(DataTypeIEC61360.class)));
        module.addSerializer(Category.class, new EnumSerializer<>(new ScreamingSnakeCaseEnumNaming(Category.class))); // not used in JSON
        // enums with upper camel case (default) naming strategy (e.g. HelloWorld)
        module.addSerializer(AssetKind.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(AssetKind.class)));
        module.addSerializer(EntityType.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(EntityType.class)));
        module.addSerializer(IdentifiableElements.class,
                new EnumSerializer<>(new UpperCamelCaseEnumNaming(IdentifiableElements.class))); // not used in JSON
        module.addSerializer(KeyElements.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(KeyElements.class))); // not used in JSON
        module.addSerializer(LevelType.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(LevelType.class)));
        module.addSerializer(LocalKeyType.class,
                new EnumSerializer<>(new UpperCamelCaseEnumNaming(LocalKeyType.class))); // not used in JSON
        module.addSerializer(ModelingKind.class,
                new EnumSerializer<>(new UpperCamelCaseEnumNaming(ModelingKind.class)));
        module.addSerializer(PermissionKind.class,
                new EnumSerializer<>(new UpperCamelCaseEnumNaming(PermissionKind.class))); // not used in JSON
        module.addSerializer(ReferableElements.class,
                new EnumSerializer<>(new UpperCamelCaseEnumNaming(ReferableElements.class))); // not used in JSON
        return module;
    }

    @Override
    public String write(AssetAdministrationShellEnvironment aasEnvironment) throws SerializationException {
        try {
            return mapper.writeValueAsString(ModelTypeProcessor.postprocess(mapper.valueToTree(aasEnvironment)));
        } catch (JsonProcessingException ex) {
            throw new SerializationException("error serializing AssetAdministrationShellEnvironment", ex);
        }
    }
}
