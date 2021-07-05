package io.adminshell.aas.v3.dataformat.xml.mixin;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.dataformat.xml.serialization.SubmodelElementSerializer;
import io.adminshell.aas.v3.model.SubmodelElement;

public interface OperationVariableMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "value")
    @JsonSerialize(using = SubmodelElementSerializer.class)
    public SubmodelElement getValue();
}
