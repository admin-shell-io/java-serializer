package io.adminshell.aas.v3.dataformat.json;

import io.adminshell.aas.v3.dataformat.json.enums.EnumSerializer;
import io.adminshell.aas.v3.dataformat.json.enums.UpperCamelCaseEnumNaming;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import io.adminshell.aas.v3.dataformat.json.mixins.DataSpecificationMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.LangStringMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.ValueListMixin;
import io.adminshell.aas.v3.dataformat.json.mixins.ValueReferencePairMixin;
import io.adminshell.aas.v3.dataformat.json.modeltype.ModelTypeProcessor;
import io.adminshell.aas.v3.dataformat.json.serialization.DataSpecificationSerializer;
import io.adminshell.aas.v3.dataformat.json.mixins.AssetAdministrationShellEnvironmentMixin;

public class JsonSerializer {

    private final ObjectMapper mapper = new ObjectMapper();

    public JsonSerializer() {
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

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

        module.addSerializer(DataSpecification.class, new DataSpecificationSerializer());
        mapper.registerModule(module);
        mapper.addMixIn(AccessControl.class, io.adminshell.aas.v3.dataformat.json.mixins.AccessControlMixin.class);
        mapper.addMixIn(AccessControlPolicyPoints.class, io.adminshell.aas.v3.dataformat.json.mixins.AccessControlPolicyPointsMixin.class);
        mapper.addMixIn(AccessPermissionRule.class, io.adminshell.aas.v3.dataformat.json.mixins.AccessPermissionRuleMixin.class);
        mapper.addMixIn(AdministrativeInformation.class, io.adminshell.aas.v3.dataformat.json.mixins.AdministrativeInformationMixin.class);
        mapper.addMixIn(AnnotatedRelationshipElement.class, io.adminshell.aas.v3.dataformat.json.mixins.AnnotatedRelationshipElementMixin.class);
        mapper.addMixIn(AssetAdministrationShellEnvironment.class, AssetAdministrationShellEnvironmentMixin.class);
        mapper.addMixIn(AssetAdministrationShell.class, io.adminshell.aas.v3.dataformat.json.mixins.AssetAdministrationShellMixin.class);
        mapper.addMixIn(AssetInformation.class, io.adminshell.aas.v3.dataformat.json.mixins.AssetInformationMixin.class);
        mapper.addMixIn(Asset.class, io.adminshell.aas.v3.dataformat.json.mixins.AssetMixin.class);
        mapper.addMixIn(BasicEvent.class, io.adminshell.aas.v3.dataformat.json.mixins.BasicEventMixin.class);
        mapper.addMixIn(BlobCertificate.class, io.adminshell.aas.v3.dataformat.json.mixins.BlobCertificateMixin.class);
        mapper.addMixIn(Blob.class, io.adminshell.aas.v3.dataformat.json.mixins.BlobMixin.class);
        mapper.addMixIn(Capability.class, io.adminshell.aas.v3.dataformat.json.mixins.CapabilityMixin.class);
        mapper.addMixIn(Certificate.class, io.adminshell.aas.v3.dataformat.json.mixins.CertificateMixin.class);
        mapper.addMixIn(ConceptDescription.class, io.adminshell.aas.v3.dataformat.json.mixins.ConceptDescriptionMixin.class);
        mapper.addMixIn(Constraint.class, io.adminshell.aas.v3.dataformat.json.mixins.ConstraintMixin.class);
        mapper.addMixIn(DataElement.class, io.adminshell.aas.v3.dataformat.json.mixins.DataElementMixin.class);
        mapper.addMixIn(DataSpecificationContent.class, io.adminshell.aas.v3.dataformat.json.mixins.DataSpecificationContentMixin.class);
        mapper.addMixIn(DataSpecificationIEC61360.class, io.adminshell.aas.v3.dataformat.json.mixins.DataSpecificationIEC61360Mixin.class);
        mapper.addMixIn(DataSpecification.class, DataSpecificationMixin.class);
        mapper.addMixIn(Entity.class, io.adminshell.aas.v3.dataformat.json.mixins.EntityMixin.class);
        mapper.addMixIn(EventElement.class, io.adminshell.aas.v3.dataformat.json.mixins.EventElementMixin.class);
        mapper.addMixIn(EventMessage.class, io.adminshell.aas.v3.dataformat.json.mixins.EventMessageMixin.class);
        mapper.addMixIn(Event.class, io.adminshell.aas.v3.dataformat.json.mixins.EventMixin.class);
        mapper.addMixIn(Extension.class, io.adminshell.aas.v3.dataformat.json.mixins.ExtensionMixin.class);
        mapper.addMixIn(File.class, io.adminshell.aas.v3.dataformat.json.mixins.FileMixin.class);
        mapper.addMixIn(Formula.class, io.adminshell.aas.v3.dataformat.json.mixins.FormulaMixin.class);
        mapper.addMixIn(HasDataSpecification.class, io.adminshell.aas.v3.dataformat.json.mixins.HasDataSpecificationMixin.class);
        mapper.addMixIn(HasExtensions.class, io.adminshell.aas.v3.dataformat.json.mixins.HasExtensionsMixin.class);
        mapper.addMixIn(HasKind.class, io.adminshell.aas.v3.dataformat.json.mixins.HasKindMixin.class);
        mapper.addMixIn(HasSemantics.class, io.adminshell.aas.v3.dataformat.json.mixins.HasSemanticsMixin.class);
        mapper.addMixIn(Identifiable.class, io.adminshell.aas.v3.dataformat.json.mixins.IdentifiableMixin.class);
        mapper.addMixIn(IdentifierKeyValuePair.class, io.adminshell.aas.v3.dataformat.json.mixins.IdentifierKeyValuePairMixin.class);
        mapper.addMixIn(Identifier.class, io.adminshell.aas.v3.dataformat.json.mixins.IdentifierMixin.class);
        mapper.addMixIn(Key.class, io.adminshell.aas.v3.dataformat.json.mixins.KeyMixin.class);
        mapper.addMixIn(LangString.class, LangStringMixin.class);
        mapper.addMixIn(MultiLanguageProperty.class, io.adminshell.aas.v3.dataformat.json.mixins.MultiLanguagePropertyMixin.class);
        mapper.addMixIn(ObjectAttributes.class, io.adminshell.aas.v3.dataformat.json.mixins.ObjectAttributesMixin.class);
        mapper.addMixIn(Operation.class, io.adminshell.aas.v3.dataformat.json.mixins.OperationMixin.class);
        mapper.addMixIn(OperationVariable.class, io.adminshell.aas.v3.dataformat.json.mixins.OperationVariableMixin.class);
        mapper.addMixIn(Permission.class, io.adminshell.aas.v3.dataformat.json.mixins.PermissionMixin.class);
        mapper.addMixIn(PermissionsPerObject.class, io.adminshell.aas.v3.dataformat.json.mixins.PermissionsPerObjectMixin.class);
        mapper.addMixIn(PolicyAdministrationPoint.class, io.adminshell.aas.v3.dataformat.json.mixins.PolicyAdministrationPointMixin.class);
        mapper.addMixIn(PolicyDecisionPoint.class, io.adminshell.aas.v3.dataformat.json.mixins.PolicyDecisionPointMixin.class);
        mapper.addMixIn(PolicyEnforcementPoints.class, io.adminshell.aas.v3.dataformat.json.mixins.PolicyEnforcementPointsMixin.class);
        mapper.addMixIn(PolicyInformationPoints.class, io.adminshell.aas.v3.dataformat.json.mixins.PolicyInformationPointsMixin.class);
        mapper.addMixIn(Property.class, io.adminshell.aas.v3.dataformat.json.mixins.PropertyMixin.class);
        mapper.addMixIn(Qualifiable.class, io.adminshell.aas.v3.dataformat.json.mixins.QualifiableMixin.class);
        mapper.addMixIn(Qualifier.class, io.adminshell.aas.v3.dataformat.json.mixins.QualifierMixin.class);
        mapper.addMixIn(RC01.class, io.adminshell.aas.v3.dataformat.json.mixins.RC01Mixin.class);
        mapper.addMixIn(Range.class, io.adminshell.aas.v3.dataformat.json.mixins.RangeMixin.class);
        mapper.addMixIn(Referable.class, io.adminshell.aas.v3.dataformat.json.mixins.ReferableMixin.class);
        mapper.addMixIn(ReferenceElement.class, io.adminshell.aas.v3.dataformat.json.mixins.ReferenceElementMixin.class);
        mapper.addMixIn(Reference.class, io.adminshell.aas.v3.dataformat.json.mixins.ReferenceMixin.class);
        mapper.addMixIn(RelationshipElement.class, io.adminshell.aas.v3.dataformat.json.mixins.RelationshipElementMixin.class);
        mapper.addMixIn(Security.class, io.adminshell.aas.v3.dataformat.json.mixins.SecurityMixin.class);
        mapper.addMixIn(SubjectAttributes.class, io.adminshell.aas.v3.dataformat.json.mixins.SubjectAttributesMixin.class);
        mapper.addMixIn(SubmodelElementCollection.class, io.adminshell.aas.v3.dataformat.json.mixins.SubmodelElementCollectionMixin.class);
        mapper.addMixIn(SubmodelElement.class, io.adminshell.aas.v3.dataformat.json.mixins.SubmodelElementMixin.class);
        mapper.addMixIn(Submodel.class, io.adminshell.aas.v3.dataformat.json.mixins.SubmodelMixin.class);
        mapper.addMixIn(ValueList.class, ValueListMixin.class);
        mapper.addMixIn(ValueReferencePair.class, ValueReferencePairMixin.class);
        mapper.addMixIn(View.class, io.adminshell.aas.v3.dataformat.json.mixins.ViewMixin.class);
    }

    public String serialize(AssetAdministrationShellEnvironment aasEnvironment) throws JsonProcessingException {
        JsonNode node = mapper.valueToTree(aasEnvironment);
        ModelTypeProcessor.postprocess(node);
        return mapper.writeValueAsString(node);
    }
}
