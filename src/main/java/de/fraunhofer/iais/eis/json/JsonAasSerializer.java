package de.fraunhofer.iais.eis.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.fraunhofer.iais.eis.*;

public class JsonAasSerializer {

    private final ObjectMapper mapper = new ObjectMapper();

    public JsonAasSerializer() {
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        mapper.addMixIn(AssetKind.class, EnumMixin.class);
        mapper.addMixIn(Category.class, EnumMixin.class);
        mapper.addMixIn(DataTypeIEC61360.class, EnumMixin.class);
        mapper.addMixIn(EntityType.class, EnumMixin.class);
        mapper.addMixIn(IdentifiableElements.class, EnumMixin.class);
        mapper.addMixIn(IdentifierType.class, EnumMixin.class);
        mapper.addMixIn(KeyElements.class, EnumMixin.class);
        mapper.addMixIn(KeyType.class, EnumMixin.class);
        mapper.addMixIn(LevelType.class, EnumMixin.class);
        mapper.addMixIn(LocalKeyType.class, EnumMixin.class);
        mapper.addMixIn(ModelingKind.class, EnumMixin.class);
        mapper.addMixIn(PermissionKind.class, EnumMixin.class);
        mapper.addMixIn(ReferableElements.class, EnumMixin.class);

        mapper.addMixIn(AccessControl.class, AccessControlMixin.class);
        mapper.addMixIn(AccessControlPolicyPoints.class, AccessControlPolicyPointsMixin.class);
        mapper.addMixIn(AccessPermissionRule.class, AccessPermissionRuleMixin.class);
        mapper.addMixIn(AdministrativeInformation.class, AdministrativeInformationMixin.class);
        mapper.addMixIn(AnnotatedRelationshipElement.class, AnnotatedRelationshipElementMixin.class);
        mapper.addMixIn(AssetAdministrationShellEnvironment.class, AssetAdministrationShellEnvironmentMixin.class);
        mapper.addMixIn(AssetAdministrationShell.class, AssetAdministrationShellMixin.class);
        mapper.addMixIn(AssetInformation.class, AssetInformationMixin.class);
        mapper.addMixIn(Asset.class, AssetMixin.class);
        mapper.addMixIn(BasicEvent.class, BasicEventMixin.class);
        mapper.addMixIn(BlobCertificate.class, BlobCertificateMixin.class);
        mapper.addMixIn(Blob.class, BlobMixin.class);
        mapper.addMixIn(Capability.class, CapabilityMixin.class);
        mapper.addMixIn(Certificate.class, CertificateMixin.class);
        mapper.addMixIn(ConceptDescription.class, ConceptDescriptionMixin.class);
        mapper.addMixIn(Constraint.class, ConstraintMixin.class);
        mapper.addMixIn(DataElement.class, DataElementMixin.class);
        mapper.addMixIn(DataSpecificationContent.class, DataSpecificationContentMixin.class);
        mapper.addMixIn(DataSpecificationIEC61360.class, DataSpecificationIEC61360Mixin.class);
        mapper.addMixIn(Entity.class, EntityMixin.class);
        mapper.addMixIn(EventElement.class, EventElementMixin.class);
        mapper.addMixIn(EventMessage.class, EventMessageMixin.class);
        mapper.addMixIn(Event.class, EventMixin.class);
        mapper.addMixIn(Extension.class, ExtensionMixin.class);
        mapper.addMixIn(File.class, FileMixin.class);
        mapper.addMixIn(Formula.class, FormulaMixin.class);
        mapper.addMixIn(HasDataSpecification.class, HasDataSpecificationMixin.class);
        mapper.addMixIn(HasExtensions.class, HasExtensionsMixin.class);
        mapper.addMixIn(HasKind.class, HasKindMixin.class);
        mapper.addMixIn(HasSemantics.class, HasSemanticsMixin.class);
        mapper.addMixIn(Identifiable.class, IdentifiableMixin.class);
        mapper.addMixIn(IdentifierKeyValuePair.class, IdentifierKeyValuePairMixin.class);
        mapper.addMixIn(Identifier.class, IdentifierMixin.class);
        mapper.addMixIn(Key.class, KeyMixin.class);
        mapper.addMixIn(MultiLanguageProperty.class, MultiLanguagePropertyMixin.class);
        mapper.addMixIn(ObjectAttributes.class, ObjectAttributesMixin.class);
        mapper.addMixIn(Operation.class, OperationMixin.class);
        mapper.addMixIn(OperationVariable.class, OperationVariableMixin.class);
        mapper.addMixIn(Permission.class, PermissionMixin.class);
        mapper.addMixIn(PermissionsPerObject.class, PermissionsPerObjectMixin.class);
        mapper.addMixIn(PolicyAdministrationPoint.class, PolicyAdministrationPointMixin.class);
        mapper.addMixIn(PolicyDecisionPoint.class, PolicyDecisionPointMixin.class);
        mapper.addMixIn(PolicyEnforcementPoints.class, PolicyEnforcementPointsMixin.class);
        mapper.addMixIn(PolicyInformationPoints.class, PolicyInformationPointsMixin.class);
        mapper.addMixIn(Property.class, PropertyMixin.class);
        mapper.addMixIn(Qualifiable.class, QualifiableMixin.class);
        mapper.addMixIn(Qualifier.class, QualifierMixin.class);
        mapper.addMixIn(Range.class, RangeMixin.class);
        mapper.addMixIn(RC01.class, RC01Mixin.class);
        mapper.addMixIn(Referable.class, ReferableMixin.class);
        mapper.addMixIn(ReferenceElement.class, ReferenceElementMixin.class);
        mapper.addMixIn(Reference.class, ReferenceMixin.class);
        mapper.addMixIn(RelationshipElement.class, RelationshipElementMixin.class);
        mapper.addMixIn(Security.class, SecurityMixin.class);
        mapper.addMixIn(SubjectAttributes.class, SubjectAttributesMixin.class);
        mapper.addMixIn(SubmodelElementCollection.class, SubmodelElementCollectionMixin.class);
        mapper.addMixIn(SubmodelElement.class, SubmodelElementMixin.class);
        mapper.addMixIn(Submodel.class, SubmodelMixin.class);
        mapper.addMixIn(View.class, ViewMixin.class);
    }

    public String serialize(Object object) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }
}
