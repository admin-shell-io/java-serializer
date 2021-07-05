package io.adminshell.aas.v3.dataformat.xml.mixin;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;

@JsonPropertyOrder({"extensions", "idShort", "displayNames", "category", "descriptions", "kind", "semanticId",
    "qualifiers", "dataSpecifications", "value", "mimeType"})
public interface FileMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "mimeType")
    public String getMimeType();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "value")
    public String getValue();
}
