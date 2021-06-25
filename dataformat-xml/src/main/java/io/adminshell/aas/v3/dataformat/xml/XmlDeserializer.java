package io.adminshell.aas.v3.dataformat.xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.dataformat.Deserializer;
import io.adminshell.aas.v3.dataformat.xml.deserialization.ByteArrayDeserializer;
import io.adminshell.aas.v3.dataformat.xml.deserialization.DataSpecificationDeserializer;
import io.adminshell.aas.v3.dataformat.xml.enums.EnumDeserializer;
import io.adminshell.aas.v3.dataformat.xml.enums.IdentifierTypeMapping;
import io.adminshell.aas.v3.dataformat.xml.enums.KeyTypeMapping;
import io.adminshell.aas.v3.dataformat.xml.enums.ScreamingSnakeCaseEnumNaming;
import io.adminshell.aas.v3.dataformat.xml.enums.UpperCamelCaseEnumNaming;
import io.adminshell.aas.v3.model.AdministrativeInformation;
import io.adminshell.aas.v3.model.AnnotatedRelationshipElement;
import io.adminshell.aas.v3.model.Asset;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.AssetInformation;
import io.adminshell.aas.v3.model.AssetKind;
import io.adminshell.aas.v3.model.BasicEvent;
import io.adminshell.aas.v3.model.Blob;
import io.adminshell.aas.v3.model.Capability;
import io.adminshell.aas.v3.model.Category;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.DataSpecification;
import io.adminshell.aas.v3.model.DataSpecificationIEC61360;
import io.adminshell.aas.v3.model.DataTypeIEC61360;
import io.adminshell.aas.v3.model.Entity;
import io.adminshell.aas.v3.model.EntityType;
import io.adminshell.aas.v3.model.File;
import io.adminshell.aas.v3.model.Formula;
import io.adminshell.aas.v3.model.IdentifiableElements;
import io.adminshell.aas.v3.model.Identifier;
import io.adminshell.aas.v3.model.IdentifierKeyValuePair;
import io.adminshell.aas.v3.model.IdentifierType;
import io.adminshell.aas.v3.model.Key;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.KeyType;
import io.adminshell.aas.v3.model.LevelType;
import io.adminshell.aas.v3.model.LocalKeyType;
import io.adminshell.aas.v3.model.ModelingKind;
import io.adminshell.aas.v3.model.MultiLanguageProperty;
import io.adminshell.aas.v3.model.Operation;
import io.adminshell.aas.v3.model.OperationVariable;
import io.adminshell.aas.v3.model.PermissionKind;
import io.adminshell.aas.v3.model.Property;
import io.adminshell.aas.v3.model.Qualifier;
import io.adminshell.aas.v3.model.Range;
import io.adminshell.aas.v3.model.ReferableElements;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.ReferenceElement;
import io.adminshell.aas.v3.model.RelationshipElement;
import io.adminshell.aas.v3.model.Submodel;
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

public class XmlDeserializer implements Deserializer {

	private XmlMapper mapper;
	private SimpleAbstractTypeResolver typeResolver;

	public XmlDeserializer() {
		initTypeResolver();
		buildMapper();
	}

	protected void buildMapper() {
		mapper = XmlMapper.builder()
				// needed because 'modelType' only handled for polymorphism and needs to be
				// ignored otherwise
				.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				.serializationInclusion(JsonInclude.Include.NON_NULL).addModule(buildEnumModule())
				.addModule(buildImplementationModule()).addModule(buildCustomDeserializerModule())
//				.addMixIn(AccessControl.class, AccessControlMixin.class)
//				.addMixIn(AccessControlPolicyPoints.class, AccessControlPolicyPointsMixin.class)
//				.addMixIn(AccessPermissionRule.class, AccessPermissionRuleMixin.class)
//				.addMixIn(AdministrativeInformation.class, AdministrativeInformationMixin.class)
//				.addMixIn(AnnotatedRelationshipElement.class, AnnotatedRelationshipElementMixin.class)
//				.addMixIn(AssetAdministrationShellEnvironment.class, AssetAdministrationShellEnvironmentMixin.class)
//				.addMixIn(AssetAdministrationShell.class, AssetAdministrationShellMixin.class)
//				.addMixIn(AssetInformation.class, AssetInformationMixin.class).addMixIn(Asset.class, AssetMixin.class)
//				.addMixIn(BasicEvent.class, BasicEventMixin.class)
//				.addMixIn(BlobCertificate.class, BlobCertificateMixin.class).addMixIn(Blob.class, BlobMixin.class)
//				.addMixIn(Capability.class, CapabilityMixin.class).addMixIn(Certificate.class, CertificateMixin.class)
//				.addMixIn(ConceptDescription.class, ConceptDescriptionMixin.class)
//				.addMixIn(Constraint.class, ConstraintMixin.class).addMixIn(DataElement.class, DataElementMixin.class)
//				.addMixIn(DataSpecificationContent.class, DataSpecificationContentMixin.class)
//				.addMixIn(DataSpecificationIEC61360.class, DataSpecificationIEC61360Mixin.class)
//				.addMixIn(DataSpecification.class, DataSpecificationMixin.class)
//				.addMixIn(Entity.class, EntityMixin.class).addMixIn(EventElement.class, EventElementMixin.class)
//				.addMixIn(EventMessage.class, EventMessageMixin.class).addMixIn(Event.class, EventMixin.class)
//				.addMixIn(Extension.class, ExtensionMixin.class).addMixIn(File.class, FileMixin.class)
//				.addMixIn(Formula.class, FormulaMixin.class)
//				.addMixIn(HasDataSpecification.class, HasDataSpecificationMixin.class)
//				.addMixIn(HasExtensions.class, HasExtensionsMixin.class).addMixIn(HasKind.class, HasKindMixin.class)
//				.addMixIn(HasSemantics.class, HasSemanticsMixin.class)
//				.addMixIn(Identifiable.class, IdentifiableMixin.class)
//				.addMixIn(IdentifierKeyValuePair.class, IdentifierKeyValuePairMixin.class)
//				.addMixIn(Identifier.class, IdentifierMixin.class).addMixIn(Key.class, KeyMixin.class)
//				.addMixIn(LangString.class, LangStringMixin.class)
//				.addMixIn(MultiLanguageProperty.class, MultiLanguagePropertyMixin.class)
//				.addMixIn(ObjectAttributes.class, ObjectAttributesMixin.class)
//				.addMixIn(Operation.class, OperationMixin.class)
//				.addMixIn(OperationVariable.class, OperationVariableMixin.class)
//				.addMixIn(Permission.class, PermissionMixin.class)
//				.addMixIn(PermissionsPerObject.class, PermissionsPerObjectMixin.class)
//				.addMixIn(PolicyAdministrationPoint.class, PolicyAdministrationPointMixin.class)
//				.addMixIn(PolicyDecisionPoint.class, PolicyDecisionPointMixin.class)
//				.addMixIn(PolicyEnforcementPoints.class, PolicyEnforcementPointsMixin.class)
//				.addMixIn(PolicyInformationPoints.class, PolicyInformationPointsMixin.class)
//				.addMixIn(Property.class, PropertyMixin.class).addMixIn(Qualifiable.class, QualifiableMixin.class)
//				.addMixIn(Qualifier.class, QualifierMixin.class)
//				.addMixIn(DataSpecificationPhysicalUnit.class, DataSpecificationPhysicalUnitMixin.class)
//				.addMixIn(Range.class, RangeMixin.class).addMixIn(Referable.class, ReferableMixin.class)
//				.addMixIn(ReferenceElement.class, ReferenceElementMixin.class)
//				.addMixIn(Reference.class, ReferenceMixin.class)
//				.addMixIn(RelationshipElement.class, RelationshipElementMixin.class)
//				.addMixIn(Security.class, SecurityMixin.class)
//				.addMixIn(SubjectAttributes.class, SubjectAttributesMixin.class)
//				.addMixIn(SubmodelElementCollection.class, SubmodelElementCollectionMixin.class)
//				.addMixIn(SubmodelElement.class, SubmodelElementMixin.class)
//				.addMixIn(Submodel.class, SubmodelMixin.class).addMixIn(ValueList.class, ValueListMixin.class)
//				.addMixIn(ValueReferencePair.class, ValueReferencePairMixin.class).addMixIn(View.class, ViewMixin.class)
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
			return mapper.readValue(value, AssetAdministrationShellEnvironment.class);
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
