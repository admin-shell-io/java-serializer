package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.dataformat.xml.deserialization.SubmodelElementsDeserializer;
import io.adminshell.aas.v3.dataformat.xml.serialization.SubmodelElementsSerializer;
import io.adminshell.aas.v3.model.EntityType;
import io.adminshell.aas.v3.model.IdentifierKeyValuePair;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.SubmodelElement;

@JsonPropertyOrder({"extensions", "idShort", "displayNames", "category", "descriptions", "kind", "semanticId",
    "qualifiers", "dataSpecifications", "globalAssetId", "externalAssetId", "entityType", "statements"})
public interface EntityMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "globalAssetId")
    public Reference getGlobalAssetId();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "specificAssetId")
    public IdentifierKeyValuePair getExternalAssetId();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "entityType")
    public EntityType getEntityType();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "statements")
    @JsonSerialize(using = SubmodelElementsSerializer.class)
    @JsonDeserialize(using = SubmodelElementsDeserializer.class)
    public List<SubmodelElement> getStatements();
}
