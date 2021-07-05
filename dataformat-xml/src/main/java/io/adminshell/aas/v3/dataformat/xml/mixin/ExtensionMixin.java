package io.adminshell.aas.v3.dataformat.xml.mixin;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({"semanticId", "name", "valueType", "value", "refersTo"})
public interface ExtensionMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "name")
    public String getName();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "valueType")
    public String getValueType();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "value")
    public String getValue();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "refersTo")
    public Reference getRefersTo();
}
