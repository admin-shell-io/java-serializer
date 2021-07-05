package io.adminshell.aas.v3.dataformat.xml.mixin;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.dataformat.xml.serialization.NamespaceIndependentReferenceSerializer;
import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({"valueId", "value"})
public interface ValueReferencePairMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.IEC61360_URI, localName = "valueId")
    @JsonSerialize(using = NamespaceIndependentReferenceSerializer.class)
    Reference getValueId();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.IEC61360_URI, localName = "value")
    String getValue();
}
