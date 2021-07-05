package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.dataformat.xml.deserialization.SubmodelElementsDeserializer;
import io.adminshell.aas.v3.dataformat.xml.serialization.SubmodelElementsSerializer;
import io.adminshell.aas.v3.model.SubmodelElement;

@JsonPropertyOrder({"extensions", "idShort", "displayNames", "category", "descriptions", "kind", "semanticId",
    "qualifiers", "dataSpecifications", "allowDuplicates", "ordered", "value"})
public interface SubmodelElementCollectionMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "value")
    @JsonSerialize(using = SubmodelElementsSerializer.class)
    @JsonDeserialize(using = SubmodelElementsDeserializer.class)
    public Collection<SubmodelElement> getValues();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "allowDuplicates")
    public boolean getAllowDuplicates();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "ordered")
    public boolean getOrdered();
}
