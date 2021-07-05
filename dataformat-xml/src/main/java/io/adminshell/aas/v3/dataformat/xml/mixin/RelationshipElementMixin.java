package io.adminshell.aas.v3.dataformat.xml.mixin;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({"extensions", "idShort", "displayNames", "category", "descriptions", "kind", "semanticId",
    "qualifiers", "dataSpecifications", "first", "second"})
public interface RelationshipElementMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "first")
    public Reference getFirst();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "second")
    public Reference getSecond();

}
