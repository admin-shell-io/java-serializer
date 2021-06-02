package io.adminshell.aas.v3.dataformat.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.dataformat.Deserializer;
import io.adminshell.aas.v3.dataformat.json.custommixins.AssetAdministrationShellEnvironmentMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.AssetAdministrationShellMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.AssetInformationMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.ConceptDescriptionMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.DataSpecificationMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.EntityMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.HasDataSpecificationMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.HasExtensionsMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.IdentifierKeyValuePairMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.IdentifierMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.LangStringMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.QualifiableMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.ReferenceMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.RelationshipElementMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.SubjectAttributesMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.SubmodelMixin;
import io.adminshell.aas.v3.dataformat.json.custommixins.ViewMixin;
import io.adminshell.aas.v3.dataformat.json.deserialization.ByteArrayDeserializer;
import io.adminshell.aas.v3.dataformat.json.deserialization.DataSpecificationDeserializer;
import io.adminshell.aas.v3.dataformat.json.enums.EnumDeserializer;
import io.adminshell.aas.v3.dataformat.json.enums.IdentifierTypeMapping;
import io.adminshell.aas.v3.dataformat.json.enums.KeyTypeMapping;
import io.adminshell.aas.v3.dataformat.json.enums.ScreamingSnakeCaseEnumNaming;
import io.adminshell.aas.v3.dataformat.json.enums.UpperCamelCaseEnumNaming;
import io.adminshell.aas.v3.dataformat.json.mixins.AccessControlMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.AccessControlPolicyPointsMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.AccessPermissionRuleMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.AdministrativeInformationMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.AnnotatedRelationshipElementMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.AssetMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.BasicEventMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.BlobCertificateMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.BlobMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.CapabilityMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.CertificateMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.ConstraintMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.DataElementMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.DataSpecificationContentMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.DataSpecificationIEC61360Mixin;
import io.adminshell.aas.v3.dataformat.json.mixins.EventElementMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.EventMessageMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.EventMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.ExtensionMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.FileMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.FormulaMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.HasKindMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.HasSemanticsMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.IdentifiableMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.KeyMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.MultiLanguagePropertyMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.ObjectAttributesMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.OperationMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.OperationVariableMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.PermissionMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.PermissionsPerObjectMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.PolicyAdministrationPointMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.PolicyDecisionPointMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.PolicyEnforcementPointsMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.PolicyInformationPointsMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.PropertyMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.QualifierMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.RC01Mixin;
import io.adminshell.aas.v3.dataformat.json.mixins.RangeMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.ReferableMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.ReferenceElementMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.SecurityMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.SubmodelElementCollectionMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.SubmodelElementMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.ValueListMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.ValueReferencePairMixin;
import io.adminshell.aas.v3.dataformat.json.modeltype.ModelTypeProcessor;
import io.adminshell.aas.v3.model.AccessControl;
import io.adminshell.aas.v3.model.AccessControlPolicyPoints;
import io.adminshell.aas.v3.model.AccessPermissionRule;
import io.adminshell.aas.v3.model.AdministrativeInformation;
import io.adminshell.aas.v3.model.AnnotatedRelationshipElement;
import io.adminshell.aas.v3.model.Asset;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.AssetInformation;
import io.adminshell.aas.v3.model.AssetKind;
import io.adminshell.aas.v3.model.BasicEvent;
import io.adminshell.aas.v3.model.Blob;
import io.adminshell.aas.v3.model.BlobCertificate;
import io.adminshell.aas.v3.model.Capability;
import io.adminshell.aas.v3.model.Category;
import io.adminshell.aas.v3.model.Certificate;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.Constraint;
import io.adminshell.aas.v3.model.DataElement;
import io.adminshell.aas.v3.model.DataSpecification;
import io.adminshell.aas.v3.model.DataSpecificationContent;
import io.adminshell.aas.v3.model.DataSpecificationIEC61360;
import io.adminshell.aas.v3.model.DataTypeIEC61360;
import io.adminshell.aas.v3.model.Entity;
import io.adminshell.aas.v3.model.EntityType;
import io.adminshell.aas.v3.model.Event;
import io.adminshell.aas.v3.model.EventElement;
import io.adminshell.aas.v3.model.EventMessage;
import io.adminshell.aas.v3.model.Extension;
import io.adminshell.aas.v3.model.File;
import io.adminshell.aas.v3.model.Formula;
import io.adminshell.aas.v3.model.HasDataSpecification;
import io.adminshell.aas.v3.model.HasExtensions;
import io.adminshell.aas.v3.model.HasKind;
import io.adminshell.aas.v3.model.HasSemantics;
import io.adminshell.aas.v3.model.Identifiable;
import io.adminshell.aas.v3.model.IdentifiableElements;
import io.adminshell.aas.v3.model.Identifier;
import io.adminshell.aas.v3.model.IdentifierKeyValuePair;
import io.adminshell.aas.v3.model.IdentifierType;
import io.adminshell.aas.v3.model.Key;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.KeyType;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.LevelType;
import io.adminshell.aas.v3.model.LocalKeyType;
import io.adminshell.aas.v3.model.ModelingKind;
import io.adminshell.aas.v3.model.MultiLanguageProperty;
import io.adminshell.aas.v3.model.ObjectAttributes;
import io.adminshell.aas.v3.model.Operation;
import io.adminshell.aas.v3.model.OperationVariable;
import io.adminshell.aas.v3.model.Permission;
import io.adminshell.aas.v3.model.PermissionKind;
import io.adminshell.aas.v3.model.PermissionsPerObject;
import io.adminshell.aas.v3.model.PolicyAdministrationPoint;
import io.adminshell.aas.v3.model.PolicyDecisionPoint;
import io.adminshell.aas.v3.model.PolicyEnforcementPoints;
import io.adminshell.aas.v3.model.PolicyInformationPoints;
import io.adminshell.aas.v3.model.Property;
import io.adminshell.aas.v3.model.Qualifiable;
import io.adminshell.aas.v3.model.Qualifier;
import io.adminshell.aas.v3.model.RC01;
import io.adminshell.aas.v3.model.Range;
import io.adminshell.aas.v3.model.Referable;
import io.adminshell.aas.v3.model.ReferableElements;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.ReferenceElement;
import io.adminshell.aas.v3.model.RelationshipElement;
import io.adminshell.aas.v3.model.Security;
import io.adminshell.aas.v3.model.SubjectAttributes;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.SubmodelElement;
import io.adminshell.aas.v3.model.SubmodelElementCollection;
import io.adminshell.aas.v3.model.ValueList;
import io.adminshell.aas.v3.model.ValueReferencePair;
import io.adminshell.aas.v3.model.View;
import io.adminshell.aas.v3.model.impl.DefaultAdministrativeInformation;
import io.adminshell.aas.v3.model.impl.DefaultAnnotatedRelationshipElement;
import io.adminshell.aas.v3.model.impl.DefaultAsset;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShell;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.impl.DefaultAssetInformation;
import io.adminshell.aas.v3.model.impl.DefaultBasicEvent;
import io.adminshell.aas.v3.model.impl.DefaultBlob;
import io.adminshell.aas.v3.model.impl.DefaultCapability;
import io.adminshell.aas.v3.model.impl.DefaultConceptDescription;
import io.adminshell.aas.v3.model.impl.DefaultDataSpecificationIEC61360;
import io.adminshell.aas.v3.model.impl.DefaultEntity;
import io.adminshell.aas.v3.model.impl.DefaultFile;
import io.adminshell.aas.v3.model.impl.DefaultFormula;
import io.adminshell.aas.v3.model.impl.DefaultIdentifier;
import io.adminshell.aas.v3.model.impl.DefaultIdentifierKeyValuePair;
import io.adminshell.aas.v3.model.impl.DefaultKey;
import io.adminshell.aas.v3.model.impl.DefaultMultiLanguageProperty;
import io.adminshell.aas.v3.model.impl.DefaultOperation;
import io.adminshell.aas.v3.model.impl.DefaultOperationVariable;
import io.adminshell.aas.v3.model.impl.DefaultProperty;
import io.adminshell.aas.v3.model.impl.DefaultQualifier;
import io.adminshell.aas.v3.model.impl.DefaultRange;
import io.adminshell.aas.v3.model.impl.DefaultReference;
import io.adminshell.aas.v3.model.impl.DefaultReferenceElement;
import io.adminshell.aas.v3.model.impl.DefaultRelationshipElement;
import io.adminshell.aas.v3.model.impl.DefaultSubmodel;
import io.adminshell.aas.v3.model.impl.DefaultSubmodelElementCollection;
import io.adminshell.aas.v3.model.impl.DefaultValueList;
import io.adminshell.aas.v3.model.impl.DefaultValueReferencePair;
import io.adminshell.aas.v3.model.impl.DefaultView;

public class JsonDeserializer implements Deserializer {

	private JsonMapper mapper;
	private SimpleAbstractTypeResolver typeResolver;

	public JsonDeserializer() {
		initTypeResolver();
		buildMapper();
	}

	protected void buildMapper() {
		mapper = JsonMapper.builder()
				// needed because 'modelType' only handled for polymorphism and needs to be
				// ignored otherwise
				.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				.serializationInclusion(JsonInclude.Include.NON_NULL).addModule(buildEnumModule())
				.addModule(buildImplementationModule()).addModule(buildCustomDeserializerModule())
				.addMixIn(AccessControl.class, AccessControlMixin.class)
				.addMixIn(AccessControlPolicyPoints.class, AccessControlPolicyPointsMixin.class)
				.addMixIn(AccessPermissionRule.class, AccessPermissionRuleMixin.class)
				.addMixIn(AdministrativeInformation.class, AdministrativeInformationMixin.class)
				.addMixIn(AnnotatedRelationshipElement.class, AnnotatedRelationshipElementMixin.class)
				.addMixIn(AssetAdministrationShellEnvironment.class, AssetAdministrationShellEnvironmentMixin.class)
				.addMixIn(AssetAdministrationShell.class, AssetAdministrationShellMixin.class)
				.addMixIn(AssetInformation.class, AssetInformationMixin.class).addMixIn(Asset.class, AssetMixin.class)
				.addMixIn(BasicEvent.class, BasicEventMixin.class)
				.addMixIn(BlobCertificate.class, BlobCertificateMixin.class).addMixIn(Blob.class, BlobMixin.class)
				.addMixIn(Capability.class, CapabilityMixin.class).addMixIn(Certificate.class, CertificateMixin.class)
				.addMixIn(ConceptDescription.class, ConceptDescriptionMixin.class)
				.addMixIn(Constraint.class, ConstraintMixin.class).addMixIn(DataElement.class, DataElementMixin.class)
				.addMixIn(DataSpecificationContent.class, DataSpecificationContentMixin.class)
				.addMixIn(DataSpecificationIEC61360.class, DataSpecificationIEC61360Mixin.class)
				.addMixIn(DataSpecification.class, DataSpecificationMixin.class)
				.addMixIn(Entity.class, EntityMixin.class).addMixIn(EventElement.class, EventElementMixin.class)
				.addMixIn(EventMessage.class, EventMessageMixin.class).addMixIn(Event.class, EventMixin.class)
				.addMixIn(Extension.class, ExtensionMixin.class).addMixIn(File.class, FileMixin.class)
				.addMixIn(Formula.class, FormulaMixin.class)
				.addMixIn(HasDataSpecification.class, HasDataSpecificationMixin.class)
				.addMixIn(HasExtensions.class, HasExtensionsMixin.class).addMixIn(HasKind.class, HasKindMixin.class)
				.addMixIn(HasSemantics.class, HasSemanticsMixin.class)
				.addMixIn(Identifiable.class, IdentifiableMixin.class)
				.addMixIn(IdentifierKeyValuePair.class, IdentifierKeyValuePairMixin.class)
				.addMixIn(Identifier.class, IdentifierMixin.class).addMixIn(Key.class, KeyMixin.class)
				.addMixIn(LangString.class, LangStringMixin.class)
				.addMixIn(MultiLanguageProperty.class, MultiLanguagePropertyMixin.class)
				.addMixIn(ObjectAttributes.class, ObjectAttributesMixin.class)
				.addMixIn(Operation.class, OperationMixin.class)
				.addMixIn(OperationVariable.class, OperationVariableMixin.class)
				.addMixIn(Permission.class, PermissionMixin.class)
				.addMixIn(PermissionsPerObject.class, PermissionsPerObjectMixin.class)
				.addMixIn(PolicyAdministrationPoint.class, PolicyAdministrationPointMixin.class)
				.addMixIn(PolicyDecisionPoint.class, PolicyDecisionPointMixin.class)
				.addMixIn(PolicyEnforcementPoints.class, PolicyEnforcementPointsMixin.class)
				.addMixIn(PolicyInformationPoints.class, PolicyInformationPointsMixin.class)
				.addMixIn(Property.class, PropertyMixin.class).addMixIn(Qualifiable.class, QualifiableMixin.class)
				.addMixIn(Qualifier.class, QualifierMixin.class).addMixIn(RC01.class, RC01Mixin.class)
				.addMixIn(Range.class, RangeMixin.class).addMixIn(Referable.class, ReferableMixin.class)
				.addMixIn(ReferenceElement.class, ReferenceElementMixin.class)
				.addMixIn(Reference.class, ReferenceMixin.class)
				.addMixIn(RelationshipElement.class, RelationshipElementMixin.class)
				.addMixIn(Security.class, SecurityMixin.class)
				.addMixIn(SubjectAttributes.class, SubjectAttributesMixin.class)
				.addMixIn(SubmodelElementCollection.class, SubmodelElementCollectionMixin.class)
				.addMixIn(SubmodelElement.class, SubmodelElementMixin.class)
				.addMixIn(Submodel.class, SubmodelMixin.class).addMixIn(ValueList.class, ValueListMixin.class)
				.addMixIn(ValueReferencePair.class, ValueReferencePairMixin.class).addMixIn(View.class, ViewMixin.class)
				.build();
	}

	protected SimpleModule buildCustomDeserializerModule() {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(DataSpecification.class, new DataSpecificationDeserializer());
		module.addDeserializer(byte[].class, new ByteArrayDeserializer());
		return module;
	}

	private void initTypeResolver() {
		typeResolver = new SimpleAbstractTypeResolver();
		typeResolver.addMapping(AdministrativeInformation.class, DefaultAdministrativeInformation.class);
		typeResolver.addMapping(AnnotatedRelationshipElement.class, DefaultAnnotatedRelationshipElement.class);
		typeResolver.addMapping(Asset.class, DefaultAsset.class);
		typeResolver.addMapping(AssetAdministrationShell.class, DefaultAssetAdministrationShell.class);
		typeResolver.addMapping(AssetAdministrationShellEnvironment.class,
				DefaultAssetAdministrationShellEnvironment.class);
		typeResolver.addMapping(AssetInformation.class, DefaultAssetInformation.class);
		typeResolver.addMapping(BasicEvent.class, DefaultBasicEvent.class);
		typeResolver.addMapping(Blob.class, DefaultBlob.class);
		typeResolver.addMapping(Capability.class, DefaultCapability.class);
		typeResolver.addMapping(ConceptDescription.class, DefaultConceptDescription.class);
		typeResolver.addMapping(DataSpecificationIEC61360.class, DefaultDataSpecificationIEC61360.class);
		typeResolver.addMapping(Entity.class, DefaultEntity.class);
		typeResolver.addMapping(File.class, DefaultFile.class);
		typeResolver.addMapping(Formula.class, DefaultFormula.class);
		typeResolver.addMapping(Identifier.class, DefaultIdentifier.class);
		typeResolver.addMapping(IdentifierKeyValuePair.class, DefaultIdentifierKeyValuePair.class);
		typeResolver.addMapping(Key.class, DefaultKey.class);
		typeResolver.addMapping(MultiLanguageProperty.class, DefaultMultiLanguageProperty.class);
		typeResolver.addMapping(Operation.class, DefaultOperation.class);
		typeResolver.addMapping(OperationVariable.class, DefaultOperationVariable.class);
		typeResolver.addMapping(Property.class, DefaultProperty.class);
		typeResolver.addMapping(Qualifier.class, DefaultQualifier.class);
		typeResolver.addMapping(Range.class, DefaultRange.class);
		typeResolver.addMapping(Reference.class, DefaultReference.class);
		typeResolver.addMapping(ReferenceElement.class, DefaultReferenceElement.class);
		typeResolver.addMapping(RelationshipElement.class, DefaultRelationshipElement.class);
		typeResolver.addMapping(Submodel.class, DefaultSubmodel.class);
		typeResolver.addMapping(SubmodelElementCollection.class, DefaultSubmodelElementCollection.class);
		typeResolver.addMapping(ValueList.class, DefaultValueList.class);
		typeResolver.addMapping(ValueReferencePair.class, DefaultValueReferencePair.class);
		typeResolver.addMapping(View.class, DefaultView.class);
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
			throw new DeserializationException("deserialization failed", ex);
		}
	}

	@Override
	public <T> void useImplementation(Class<T> aasInterface, Class<? extends T> implementation) {
		typeResolver.addMapping(aasInterface, implementation);
		buildMapper();
	}
}
