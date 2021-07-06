package io.adminshell.aas.v3.dataformat.xml;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import io.adminshell.aas.v3.dataformat.SerializationException;
import io.adminshell.aas.v3.dataformat.Serializer;
import io.adminshell.aas.v3.dataformat.core.serialization.EnumSerializer;
import io.adminshell.aas.v3.dataformat.xml.mixin.AdministrativeInformationMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.AnnotatedRelationshipElementMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.AssetAdministrationShellMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.AssetInformationMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.AssetMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.BasicEventMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.BlobMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.CapabilityMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.ConceptDescriptionMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.DataSpecificationIEC61360Mixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.EntityMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.ExtensionMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.FileMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.FormulaMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.HasDataSpecificationMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.HasExtensionsMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.HasKindMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.HasSemanticsMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.IdentifiableMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.IdentifierKeyValuePairMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.IdentifierMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.KeyMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.MultiLanguagePropertyMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.OperationMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.OperationVariableMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.PropertyMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.QualifiableMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.QualifierMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.RangeMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.ReferableMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.ReferenceElementMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.ReferenceMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.RelationshipElementMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.SubmodelElementCollectionMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.SubmodelMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.ValueListMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.ValueReferencePairMixin;
import io.adminshell.aas.v3.dataformat.xml.mixin.ViewMixin;
import io.adminshell.aas.v3.dataformat.xml.serialization.AssetAdministrationShellEnvironmentSerializer;
import io.adminshell.aas.v3.dataformat.xml.serialization.EmbeddedDataSpecificationSerializer;
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
import io.adminshell.aas.v3.model.DataSpecificationIEC61360;
import io.adminshell.aas.v3.model.DataTypeIEC61360;
import io.adminshell.aas.v3.model.EmbeddedDataSpecification;
import io.adminshell.aas.v3.model.Entity;
import io.adminshell.aas.v3.model.EntityType;
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
import io.adminshell.aas.v3.model.LevelType;
import io.adminshell.aas.v3.model.LocalKeyType;
import io.adminshell.aas.v3.model.ModelingKind;
import io.adminshell.aas.v3.model.MultiLanguageProperty;
import io.adminshell.aas.v3.model.Operation;
import io.adminshell.aas.v3.model.OperationVariable;
import io.adminshell.aas.v3.model.PermissionKind;
import io.adminshell.aas.v3.model.Property;
import io.adminshell.aas.v3.model.Qualifiable;
import io.adminshell.aas.v3.model.Qualifier;
import io.adminshell.aas.v3.model.Range;
import io.adminshell.aas.v3.model.Referable;
import io.adminshell.aas.v3.model.ReferableElements;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.ReferenceElement;
import io.adminshell.aas.v3.model.RelationshipElement;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.SubmodelElementCollection;
import io.adminshell.aas.v3.model.ValueList;
import io.adminshell.aas.v3.model.ValueReferencePair;
import io.adminshell.aas.v3.model.View;

public class XmlSerializer implements Serializer {

    private final XmlMapper mapper;
    private Map<String, String> namespacePrefixes;

    public XmlSerializer() {
        this(null);
    }

    public XmlSerializer(Map<String, String> namespacePrefixes) {
        this.namespacePrefixes = namespacePrefixes;
        mapper = initMapper();
    }

    protected XmlMapper initMapper() {
        return XmlMapper.builder().enable(SerializationFeature.INDENT_OUTPUT)
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .serializationInclusion(JsonInclude.Include.NON_EMPTY).defaultUseWrapper(false)
                .addModule(buildEnumModule()).addModule(buildCustomSerializerModule())
                .addMixIn(AdministrativeInformation.class, AdministrativeInformationMixin.class)
                .addMixIn(AnnotatedRelationshipElement.class, AnnotatedRelationshipElementMixin.class)
                .addMixIn(AssetAdministrationShell.class, AssetAdministrationShellMixin.class)
                .addMixIn(AssetInformation.class, AssetInformationMixin.class).addMixIn(Asset.class, AssetMixin.class)
                .addMixIn(BasicEvent.class, BasicEventMixin.class).addMixIn(Blob.class, BlobMixin.class)
                .addMixIn(Capability.class, CapabilityMixin.class)
                .addMixIn(ConceptDescription.class, ConceptDescriptionMixin.class)
                .addMixIn(DataSpecificationIEC61360.class, DataSpecificationIEC61360Mixin.class)
                .addMixIn(Entity.class, EntityMixin.class).addMixIn(Extension.class, ExtensionMixin.class)
                .addMixIn(File.class, FileMixin.class).addMixIn(Formula.class, FormulaMixin.class)
                .addMixIn(HasDataSpecification.class, HasDataSpecificationMixin.class)
                .addMixIn(HasExtensions.class, HasExtensionsMixin.class).addMixIn(HasKind.class, HasKindMixin.class)
                .addMixIn(HasSemantics.class, HasSemanticsMixin.class)
                .addMixIn(Identifiable.class, IdentifiableMixin.class)
                .addMixIn(IdentifierKeyValuePair.class, IdentifierKeyValuePairMixin.class)
                .addMixIn(Identifier.class, IdentifierMixin.class).addMixIn(Key.class, KeyMixin.class)
                .addMixIn(MultiLanguageProperty.class, MultiLanguagePropertyMixin.class)
                .addMixIn(Operation.class, OperationMixin.class)
                .addMixIn(OperationVariable.class, OperationVariableMixin.class)
                .addMixIn(Property.class, PropertyMixin.class).addMixIn(Qualifiable.class, QualifiableMixin.class)
                .addMixIn(Qualifier.class, QualifierMixin.class).addMixIn(Range.class, RangeMixin.class)
                .addMixIn(Referable.class, ReferableMixin.class)
                .addMixIn(ReferenceElement.class, ReferenceElementMixin.class)
                .addMixIn(Reference.class, ReferenceMixin.class)
                .addMixIn(RelationshipElement.class, RelationshipElementMixin.class)
                .addMixIn(SubmodelElementCollection.class, SubmodelElementCollectionMixin.class)
                .addMixIn(Submodel.class, SubmodelMixin.class).addMixIn(ValueList.class, ValueListMixin.class)
                .addMixIn(ValueReferencePair.class, ValueReferencePairMixin.class).addMixIn(View.class, ViewMixin.class)
                .configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true).build();
    }

    protected SimpleModule buildCustomSerializerModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(EmbeddedDataSpecification.class, new EmbeddedDataSpecificationSerializer());
        AssetAdministrationShellEnvironmentSerializer aasEnvSerializer;
        if (namespacePrefixes != null) {
            aasEnvSerializer = new AssetAdministrationShellEnvironmentSerializer(namespacePrefixes);
        } else {
            aasEnvSerializer = new AssetAdministrationShellEnvironmentSerializer();
        }
        module.addSerializer(AssetAdministrationShellEnvironment.class, aasEnvSerializer);
        return module;
    }

    protected SimpleModule buildEnumModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Enum.class, new EnumSerializer());
        return module;
    }

    @Override
    public String write(AssetAdministrationShellEnvironment aasEnvironment) throws SerializationException {
        try {
            ObjectWriter writer = mapper.writer();
            return writer.writeValueAsString(aasEnvironment);
        } catch (JsonProcessingException ex) {
            throw new SerializationException("serialization failed", ex);
        }
    }
}