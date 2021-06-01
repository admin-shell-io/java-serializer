package io.adminshell.aas.v3.dataformat.json;

import io.adminshell.aas.v3.dataformat.json.enums.EnumSerializer;
import io.adminshell.aas.v3.dataformat.json.enums.UpperCamelCaseEnumNaming;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.fraunhofer.iais.eis.AccessControl;
import de.fraunhofer.iais.eis.AccessControlPolicyPoints;
import de.fraunhofer.iais.eis.AccessPermissionRule;
import de.fraunhofer.iais.eis.AdministrativeInformation;
import de.fraunhofer.iais.eis.AnnotatedRelationshipElement;
import de.fraunhofer.iais.eis.Asset;
import de.fraunhofer.iais.eis.AssetAdministrationShell;
import de.fraunhofer.iais.eis.AssetAdministrationShellEnvironment;
import de.fraunhofer.iais.eis.AssetInformation;
import de.fraunhofer.iais.eis.AssetKind;
import de.fraunhofer.iais.eis.BasicEvent;
import de.fraunhofer.iais.eis.Blob;
import de.fraunhofer.iais.eis.BlobCertificate;
import de.fraunhofer.iais.eis.Capability;
import de.fraunhofer.iais.eis.Category;
import de.fraunhofer.iais.eis.Certificate;
import de.fraunhofer.iais.eis.ConceptDescription;
import de.fraunhofer.iais.eis.Constraint;
import de.fraunhofer.iais.eis.DataElement;
import de.fraunhofer.iais.eis.DataSpecification;
import de.fraunhofer.iais.eis.DataSpecificationContent;
import de.fraunhofer.iais.eis.DataSpecificationIEC61360;
import de.fraunhofer.iais.eis.DataTypeIEC61360;
import de.fraunhofer.iais.eis.Entity;
import de.fraunhofer.iais.eis.EntityType;
import de.fraunhofer.iais.eis.Event;
import de.fraunhofer.iais.eis.EventElement;
import de.fraunhofer.iais.eis.EventMessage;
import de.fraunhofer.iais.eis.Extension;
import de.fraunhofer.iais.eis.File;
import de.fraunhofer.iais.eis.Formula;
import de.fraunhofer.iais.eis.HasDataSpecification;
import de.fraunhofer.iais.eis.HasExtensions;
import de.fraunhofer.iais.eis.HasKind;
import de.fraunhofer.iais.eis.HasSemantics;
import de.fraunhofer.iais.eis.Identifiable;
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
import de.fraunhofer.iais.eis.ObjectAttributes;
import de.fraunhofer.iais.eis.Operation;
import de.fraunhofer.iais.eis.OperationVariable;
import de.fraunhofer.iais.eis.Permission;
import de.fraunhofer.iais.eis.PermissionKind;
import de.fraunhofer.iais.eis.PermissionsPerObject;
import de.fraunhofer.iais.eis.PolicyAdministrationPoint;
import de.fraunhofer.iais.eis.PolicyDecisionPoint;
import de.fraunhofer.iais.eis.PolicyEnforcementPoints;
import de.fraunhofer.iais.eis.PolicyInformationPoints;
import de.fraunhofer.iais.eis.Property;
import de.fraunhofer.iais.eis.Qualifiable;
import de.fraunhofer.iais.eis.Qualifier;
import de.fraunhofer.iais.eis.RC01;
import de.fraunhofer.iais.eis.Range;
import de.fraunhofer.iais.eis.Referable;
import de.fraunhofer.iais.eis.ReferableElements;
import de.fraunhofer.iais.eis.Reference;
import de.fraunhofer.iais.eis.ReferenceElement;
import de.fraunhofer.iais.eis.RelationshipElement;
import de.fraunhofer.iais.eis.Security;
import de.fraunhofer.iais.eis.SubjectAttributes;
import de.fraunhofer.iais.eis.Submodel;
import de.fraunhofer.iais.eis.SubmodelElement;
import de.fraunhofer.iais.eis.SubmodelElementCollection;
import de.fraunhofer.iais.eis.ValueList;
import de.fraunhofer.iais.eis.ValueReferencePair;
import de.fraunhofer.iais.eis.View;
import de.fraunhofer.iais.eis.util.LangString;

import io.adminshell.aas.v3.dataformat.json.enums.IdentifierTypeMapping;
import io.adminshell.aas.v3.dataformat.json.enums.KeyTypeMapping;
import io.adminshell.aas.v3.dataformat.json.enums.ScreamingSnakeCaseEnumNaming;
import io.adminshell.aas.v3.dataformat.json.mixins.AssetAdministrationShellMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.AssetAdministrationShellEnvironmentMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.AssetInformationMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.ConceptDescriptionMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.DataSpecificationMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.EntityMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.HasDataSpecificationMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.HasExtensionsMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.IdentifierKeyValuePairMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.IdentifierMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.LangStringMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.QualifiableMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.ReferenceMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.RelationshipElementMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.SubjectAttributesMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.SubmodelMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.ViewMixin;
import de.fraunhofer.iais.eis.AccessControlMixin;
import de.fraunhofer.iais.eis.AccessControlPolicyPointsMixin;
import de.fraunhofer.iais.eis.AccessPermissionRuleMixin;
import de.fraunhofer.iais.eis.AdministrativeInformationMixin;
import de.fraunhofer.iais.eis.AnnotatedRelationshipElementMixin;
import de.fraunhofer.iais.eis.AssetMixin;
import de.fraunhofer.iais.eis.BasicEventMixin;
import de.fraunhofer.iais.eis.BlobCertificateMixin;
import de.fraunhofer.iais.eis.BlobMixin;
import de.fraunhofer.iais.eis.CapabilityMixin;
import de.fraunhofer.iais.eis.CertificateMixin;
import de.fraunhofer.iais.eis.ConstraintMixin;
import de.fraunhofer.iais.eis.DataElementMixin;
import de.fraunhofer.iais.eis.DataSpecificationContentMixin;
import de.fraunhofer.iais.eis.DataSpecificationIEC61360Mixin;
import de.fraunhofer.iais.eis.EventElementMixin;
import de.fraunhofer.iais.eis.EventMessageMixin;
import de.fraunhofer.iais.eis.EventMixin;
import de.fraunhofer.iais.eis.ExtensionMixin;
import de.fraunhofer.iais.eis.FileMixin;
import de.fraunhofer.iais.eis.FormulaMixin;
import de.fraunhofer.iais.eis.HasKindMixin;
import de.fraunhofer.iais.eis.HasSemanticsMixin;
import de.fraunhofer.iais.eis.IdentifiableMixin;
import de.fraunhofer.iais.eis.KeyMixin;
import de.fraunhofer.iais.eis.MultiLanguagePropertyMixin;
import de.fraunhofer.iais.eis.ObjectAttributesMixin;
import de.fraunhofer.iais.eis.OperationMixin;
import de.fraunhofer.iais.eis.OperationVariableMixin;
import de.fraunhofer.iais.eis.PermissionMixin;
import de.fraunhofer.iais.eis.PermissionsPerObjectMixin;
import de.fraunhofer.iais.eis.PolicyAdministrationPointMixin;
import de.fraunhofer.iais.eis.PolicyDecisionPointMixin;
import de.fraunhofer.iais.eis.PolicyEnforcementPointsMixin;
import de.fraunhofer.iais.eis.PolicyInformationPointsMixin;
import de.fraunhofer.iais.eis.PropertyMixin;
import de.fraunhofer.iais.eis.QualifierMixin;
import de.fraunhofer.iais.eis.RC01Mixin;
import de.fraunhofer.iais.eis.RangeMixin;
import de.fraunhofer.iais.eis.ReferableMixin;
import de.fraunhofer.iais.eis.ReferenceElementMixin;
import de.fraunhofer.iais.eis.SecurityMixin;
import de.fraunhofer.iais.eis.SubmodelElementCollectionMixin;
import de.fraunhofer.iais.eis.SubmodelElementMixin;
import de.fraunhofer.iais.eis.ValueListMixin;
import de.fraunhofer.iais.eis.ValueReferencePairMixin;
import io.adminshell.aas.v3.dataformat.json.modeltype.ModelTypeProcessor;
import io.adminshell.aas.v3.dataformat.json.serialization.DataSpecificationSerializer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonSerializer implements Serializer {

    private final JsonMapper mapper;

    public JsonSerializer() {
        mapper = initMapper();
    }

    protected JsonMapper initMapper() {
        return JsonMapper.builder()
                .enable(SerializationFeature.INDENT_OUTPUT)
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .addModule(buildEnumModule())
                .addModule(buildCustomDeserializerModule())
                .addMixIn(AccessControl.class, AccessControlMixin.class)
                .addMixIn(AccessControlPolicyPoints.class, AccessControlPolicyPointsMixin.class)
                .addMixIn(AccessPermissionRule.class, AccessPermissionRuleMixin.class)
                .addMixIn(AdministrativeInformation.class, AdministrativeInformationMixin.class)
                .addMixIn(AnnotatedRelationshipElement.class, AnnotatedRelationshipElementMixin.class)
                .addMixIn(AssetAdministrationShellEnvironment.class, AssetAdministrationShellEnvironmentMixin.class)
                .addMixIn(AssetAdministrationShell.class, AssetAdministrationShellMixin.class)
                .addMixIn(AssetInformation.class, AssetInformationMixin.class)
                .addMixIn(Asset.class, AssetMixin.class)
                .addMixIn(BasicEvent.class, BasicEventMixin.class)
                .addMixIn(BlobCertificate.class, BlobCertificateMixin.class)
                .addMixIn(Blob.class, BlobMixin.class)
                .addMixIn(Capability.class, CapabilityMixin.class)
                .addMixIn(Certificate.class, CertificateMixin.class)
                .addMixIn(ConceptDescription.class, ConceptDescriptionMixin.class)
                .addMixIn(Constraint.class, ConstraintMixin.class)
                .addMixIn(DataElement.class, DataElementMixin.class)
                .addMixIn(DataSpecificationContent.class, DataSpecificationContentMixin.class)
                .addMixIn(DataSpecificationIEC61360.class, DataSpecificationIEC61360Mixin.class)
                .addMixIn(DataSpecification.class, DataSpecificationMixin.class)
                .addMixIn(Entity.class, EntityMixin.class)
                .addMixIn(EventElement.class, EventElementMixin.class)
                .addMixIn(EventMessage.class, EventMessageMixin.class)
                .addMixIn(Event.class, EventMixin.class)
                .addMixIn(Extension.class, ExtensionMixin.class)
                .addMixIn(File.class, FileMixin.class)
                .addMixIn(Formula.class, FormulaMixin.class)
                .addMixIn(HasDataSpecification.class, HasDataSpecificationMixin.class)
                .addMixIn(HasExtensions.class, HasExtensionsMixin.class)
                .addMixIn(HasKind.class, HasKindMixin.class)
                .addMixIn(HasSemantics.class, HasSemanticsMixin.class)
                .addMixIn(Identifiable.class, IdentifiableMixin.class)
                .addMixIn(IdentifierKeyValuePair.class, IdentifierKeyValuePairMixin.class)
                .addMixIn(Identifier.class, IdentifierMixin.class)
                .addMixIn(Key.class, KeyMixin.class)
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
                .addMixIn(Property.class, PropertyMixin.class)
                .addMixIn(Qualifiable.class, QualifiableMixin.class)
                .addMixIn(Qualifier.class, QualifierMixin.class)
                .addMixIn(RC01.class, RC01Mixin.class)
                .addMixIn(Range.class, RangeMixin.class)
                .addMixIn(Referable.class, ReferableMixin.class)
                .addMixIn(ReferenceElement.class, ReferenceElementMixin.class)
                .addMixIn(Reference.class, ReferenceMixin.class)
                .addMixIn(RelationshipElement.class, RelationshipElementMixin.class)
                .addMixIn(Security.class, SecurityMixin.class)
                .addMixIn(SubjectAttributes.class, SubjectAttributesMixin.class)
                .addMixIn(SubmodelElementCollection.class, SubmodelElementCollectionMixin.class)
                .addMixIn(SubmodelElement.class, SubmodelElementMixin.class)
                .addMixIn(Submodel.class, SubmodelMixin.class)
                .addMixIn(ValueList.class, ValueListMixin.class)
                .addMixIn(ValueReferencePair.class, ValueReferencePairMixin.class)
                .addMixIn(View.class, ViewMixin.class)
                .build();
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
        module.addSerializer(DataTypeIEC61360.class, new EnumSerializer<>(new ScreamingSnakeCaseEnumNaming(DataTypeIEC61360.class)));
        module.addSerializer(Category.class, new EnumSerializer<>(new ScreamingSnakeCaseEnumNaming(Category.class))); // not used in JSON
        // enums with upper camel case (default) naming strategy (e.g. HelloWorld)
        module.addSerializer(AssetKind.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(AssetKind.class)));
        module.addSerializer(EntityType.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(EntityType.class)));
        module.addSerializer(IdentifiableElements.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(IdentifiableElements.class))); // not used in JSON
        module.addSerializer(KeyElements.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(KeyElements.class))); // not used in JSON
        module.addSerializer(LevelType.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(LevelType.class)));
        module.addSerializer(LocalKeyType.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(LocalKeyType.class))); // not used in JSON
        module.addSerializer(ModelingKind.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(ModelingKind.class)));
        module.addSerializer(PermissionKind.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(PermissionKind.class))); // not used in JSON
        module.addSerializer(ReferableElements.class, new EnumSerializer<>(new UpperCamelCaseEnumNaming(ReferableElements.class))); // not used in JSON
        return module;
    }

    @Override
    public String write(AssetAdministrationShellEnvironment aasEnvironment) throws SerializationException {
        try {
            return mapper.writeValueAsString(
                    ModelTypeProcessor.postprocess(
                            mapper.valueToTree(aasEnvironment)));
        } catch (JsonProcessingException ex) {
            throw new SerializationException("serialization failed", ex);
        }
    }
}
