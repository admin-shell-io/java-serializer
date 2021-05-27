package io.adminshell.aas.v3.dataformat.json;

import io.adminshell.aas.v3.dataformat.json.modeltype.ModelTypeProcessor;
import io.adminshell.aas.v3.dataformat.json.deserialization.DataSpecificationDeserializer;
import io.adminshell.aas.v3.dataformat.json.enums.IdentifierTypeMapping;
import io.adminshell.aas.v3.dataformat.json.enums.EnumDeserializer;
import io.adminshell.aas.v3.dataformat.json.enums.KeyTypeMapping;
import io.adminshell.aas.v3.dataformat.json.enums.UpperCamelCaseEnumNaming;
import io.adminshell.aas.v3.dataformat.json.enums.ScreamingSnakeCaseEnumNaming;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.fraunhofer.iais.eis.AdministrativeInformation;
import de.fraunhofer.iais.eis.AnnotatedRelationshipElement;
import de.fraunhofer.iais.eis.Asset;
import de.fraunhofer.iais.eis.AssetAdministrationShell;
import de.fraunhofer.iais.eis.AssetAdministrationShellEnvironment;
import de.fraunhofer.iais.eis.AssetInformation;
import de.fraunhofer.iais.eis.AssetKind;
import de.fraunhofer.iais.eis.BasicEvent;
import de.fraunhofer.iais.eis.Blob;
import de.fraunhofer.iais.eis.Capability;
import de.fraunhofer.iais.eis.Category;
import de.fraunhofer.iais.eis.ConceptDescription;
import de.fraunhofer.iais.eis.Constraint;
import de.fraunhofer.iais.eis.DataSpecification;
import de.fraunhofer.iais.eis.DataSpecificationIEC61360;
import de.fraunhofer.iais.eis.DataTypeIEC61360;
import de.fraunhofer.iais.eis.DefaultAdministrativeInformation;
import de.fraunhofer.iais.eis.DefaultAnnotatedRelationshipElement;
import de.fraunhofer.iais.eis.DefaultAsset;
import de.fraunhofer.iais.eis.DefaultAssetAdministrationShell;
import de.fraunhofer.iais.eis.DefaultAssetAdministrationShellEnvironment;
import de.fraunhofer.iais.eis.DefaultAssetInformation;
import de.fraunhofer.iais.eis.DefaultBasicEvent;
import de.fraunhofer.iais.eis.DefaultBlob;
import de.fraunhofer.iais.eis.DefaultCapability;
import de.fraunhofer.iais.eis.DefaultConceptDescription;
import de.fraunhofer.iais.eis.DefaultDataSpecificationIEC61360;
import de.fraunhofer.iais.eis.DefaultEntity;
import de.fraunhofer.iais.eis.DefaultFile;
import de.fraunhofer.iais.eis.DefaultFormula;
import de.fraunhofer.iais.eis.DefaultIdentifier;
import de.fraunhofer.iais.eis.DefaultIdentifierKeyValuePair;
import de.fraunhofer.iais.eis.DefaultKey;
import de.fraunhofer.iais.eis.DefaultMultiLanguageProperty;
import de.fraunhofer.iais.eis.DefaultOperation;
import de.fraunhofer.iais.eis.DefaultProperty;
import de.fraunhofer.iais.eis.DefaultQualifier;
import de.fraunhofer.iais.eis.DefaultRange;
import de.fraunhofer.iais.eis.DefaultReference;
import de.fraunhofer.iais.eis.DefaultReferenceElement;
import de.fraunhofer.iais.eis.DefaultRelationshipElement;
import de.fraunhofer.iais.eis.DefaultSubmodel;
import de.fraunhofer.iais.eis.DefaultSubmodelElementCollection;
import de.fraunhofer.iais.eis.DefaultValueList;
import de.fraunhofer.iais.eis.DefaultValueReferencePair;
import de.fraunhofer.iais.eis.DefaultView;
import de.fraunhofer.iais.eis.Entity;
import de.fraunhofer.iais.eis.EntityType;
import de.fraunhofer.iais.eis.File;
import de.fraunhofer.iais.eis.Formula;
import de.fraunhofer.iais.eis.IdentifiableElements;
import de.fraunhofer.iais.eis.Identifier;
import de.fraunhofer.iais.eis.IdentifierKeyValuePair;
import de.fraunhofer.iais.eis.IdentifierType;
import de.fraunhofer.iais.eis.Key;
import de.fraunhofer.iais.eis.KeyElements;
import de.fraunhofer.iais.eis.KeyType;
import de.fraunhofer.iais.eis.LevelType;
import de.fraunhofer.iais.eis.LocalKeyType;
import de.fraunhofer.iais.eis.ModelingKind;
import de.fraunhofer.iais.eis.MultiLanguageProperty;
import de.fraunhofer.iais.eis.Operation;
import de.fraunhofer.iais.eis.PermissionKind;
import de.fraunhofer.iais.eis.Property;
import de.fraunhofer.iais.eis.Qualifier;
import de.fraunhofer.iais.eis.Range;
import de.fraunhofer.iais.eis.ReferableElements;
import de.fraunhofer.iais.eis.Reference;
import de.fraunhofer.iais.eis.ReferenceElement;
import de.fraunhofer.iais.eis.RelationshipElement;
import de.fraunhofer.iais.eis.Submodel;
import de.fraunhofer.iais.eis.SubmodelElement;
import de.fraunhofer.iais.eis.SubmodelElementCollection;
import de.fraunhofer.iais.eis.ValueList;
import de.fraunhofer.iais.eis.ValueReferencePair;
import de.fraunhofer.iais.eis.View;
import io.adminshell.aas.v3.dataformat.json.mixins.AssetInformationMixin;
import io.adminshell.aas.v3.dataformat.json.deserialization.mixins.ConstraintMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.DataSpecificationIEC61360Mixin;
import io.adminshell.aas.v3.dataformat.json.mixins.IdentifierKeyValuePairMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.IdentifierMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.LangStringMixin;
import io.adminshell.aas.v3.dataformat.json.deserialization.mixins.RelationshipElementMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.SubmodelElementCollectionMixin;
import io.adminshell.aas.v3.dataformat.json.deserialization.mixins.SubmodelElementMixin;
import de.fraunhofer.iais.eis.util.LangString;

public class JsonDeserializer {

    private final ObjectMapper mapper = new ObjectMapper();

    public JsonDeserializer() {
        // needed because 'modelType' only handled for polymorphism and needs to be ignored otherwise
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        SimpleModule module = new SimpleModule();
        // enums with custom naming strategy
        module.addDeserializer(IdentifierType.class, new EnumDeserializer<>(new IdentifierTypeMapping()));
        module.addDeserializer(KeyType.class, new EnumDeserializer<>(new KeyTypeMapping()));
        // enums with screaming snake case naming strategy (e.g. HELLO_WORLD)
        module.addDeserializer(DataTypeIEC61360.class, new EnumDeserializer<>(new ScreamingSnakeCaseEnumNaming(DataTypeIEC61360.class)));
        module.addDeserializer(Category.class, new EnumDeserializer<>(new ScreamingSnakeCaseEnumNaming(Category.class))); // not used in JSON
        // enums with upper camel case (default) naming strategy (e.g. HelloWorld)
        module.addDeserializer(AssetKind.class, new EnumDeserializer<>(new UpperCamelCaseEnumNaming(AssetKind.class)));
        module.addDeserializer(EntityType.class, new EnumDeserializer<>(new UpperCamelCaseEnumNaming(EntityType.class)));
        module.addDeserializer(IdentifiableElements.class, new EnumDeserializer<>(new UpperCamelCaseEnumNaming(IdentifiableElements.class))); // not used in JSON
        module.addDeserializer(KeyElements.class, new EnumDeserializer<>(new UpperCamelCaseEnumNaming(KeyElements.class))); // not used in JSON
        module.addDeserializer(LevelType.class, new EnumDeserializer<>(new UpperCamelCaseEnumNaming(LevelType.class)));
        module.addDeserializer(LocalKeyType.class, new EnumDeserializer<>(new UpperCamelCaseEnumNaming(LocalKeyType.class))); // not used in JSON
        module.addDeserializer(ModelingKind.class, new EnumDeserializer<>(new UpperCamelCaseEnumNaming(ModelingKind.class)));
        module.addDeserializer(PermissionKind.class, new EnumDeserializer<>(new UpperCamelCaseEnumNaming(PermissionKind.class))); // not used in JSON
        module.addDeserializer(ReferableElements.class, new EnumDeserializer<>(new UpperCamelCaseEnumNaming(ReferableElements.class))); // not used in JSON
        // dynamic type resolution, can be overriden for custom interface implementations        
        SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver();
        resolver.addMapping(AdministrativeInformation.class, DefaultAdministrativeInformation.class);
        resolver.addMapping(AnnotatedRelationshipElement.class, DefaultAnnotatedRelationshipElement.class);
        resolver.addMapping(Asset.class, DefaultAsset.class);
        resolver.addMapping(AssetAdministrationShell.class, DefaultAssetAdministrationShell.class);
        resolver.addMapping(AssetAdministrationShellEnvironment.class, DefaultAssetAdministrationShellEnvironment.class);
        resolver.addMapping(AssetInformation.class, DefaultAssetInformation.class);
        resolver.addMapping(BasicEvent.class, DefaultBasicEvent.class);
        resolver.addMapping(Blob.class, DefaultBlob.class);
        resolver.addMapping(Capability.class, DefaultCapability.class);
        resolver.addMapping(ConceptDescription.class, DefaultConceptDescription.class);
        resolver.addMapping(DataSpecificationIEC61360.class, DefaultDataSpecificationIEC61360.class);
        // there is no default class for DataTypeIEC61360
        //resolver.addMapping(DataTypeIEC61360.class, DefaultDataTypeIEC61360.class);
        resolver.addMapping(Entity.class, DefaultEntity.class);
        resolver.addMapping(File.class, DefaultFile.class);
        resolver.addMapping(Formula.class, DefaultFormula.class);
        resolver.addMapping(Identifier.class, DefaultIdentifier.class);
        resolver.addMapping(IdentifierKeyValuePair.class, DefaultIdentifierKeyValuePair.class);
        resolver.addMapping(Key.class, DefaultKey.class);
        resolver.addMapping(MultiLanguageProperty.class, DefaultMultiLanguageProperty.class);
        resolver.addMapping(Operation.class, DefaultOperation.class);
        resolver.addMapping(Property.class, DefaultProperty.class);
        resolver.addMapping(Qualifier.class, DefaultQualifier.class);
        resolver.addMapping(Range.class, DefaultRange.class);
        resolver.addMapping(Reference.class, DefaultReference.class);
        resolver.addMapping(ReferenceElement.class, DefaultReferenceElement.class);
        resolver.addMapping(RelationshipElement.class, DefaultRelationshipElement.class);
        resolver.addMapping(Submodel.class, DefaultSubmodel.class);
        resolver.addMapping(SubmodelElementCollection.class, DefaultSubmodelElementCollection.class);
        resolver.addMapping(ValueList.class, DefaultValueList.class);
        resolver.addMapping(ValueReferencePair.class, DefaultValueReferencePair.class);
        resolver.addMapping(View.class, DefaultView.class);
        module.setAbstractTypes(resolver);
        module.addDeserializer(DataSpecification.class, new DataSpecificationDeserializer());
        mapper.registerModule(module);
        // mixins related to renaming getter/setter
        mapper.addMixIn(AssetInformation.class, AssetInformationMixin.class);
        mapper.addMixIn(Constraint.class, ConstraintMixin.class);
        mapper.addMixIn(DataSpecificationIEC61360.class, DataSpecificationIEC61360Mixin.class);
        mapper.addMixIn(Identifier.class, IdentifierMixin.class);
        mapper.addMixIn(IdentifierKeyValuePair.class, IdentifierKeyValuePairMixin.class);
        mapper.addMixIn(LangString.class, LangStringMixin.class);
        mapper.addMixIn(RelationshipElement.class, RelationshipElementMixin.class);
        mapper.addMixIn(SubmodelElement.class, SubmodelElementMixin.class);
        mapper.addMixIn(SubmodelElementCollection.class, SubmodelElementCollectionMixin.class);
    }

    // TODO untested if this actually works when adding another resolver
    public <T> void useCustomImplementation(Class<T> aasInterface, Class<? extends T> implementation) {
        SimpleModule module = new SimpleModule();
        SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver();
        resolver.addMapping(aasInterface, implementation);
        module.setAbstractTypes(resolver);
        mapper.registerModule(module);
    }

    public AssetAdministrationShellEnvironment deserialize(String value) throws JsonProcessingException {
        JsonNode node = ModelTypeProcessor.preprocess(value);
        return mapper.treeToValue(node, AssetAdministrationShellEnvironment.class);
    }

}
