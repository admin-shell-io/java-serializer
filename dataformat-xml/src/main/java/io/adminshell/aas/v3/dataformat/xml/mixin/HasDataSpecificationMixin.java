package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.dataformat.xml.deserialization.EmbeddedDataSpecificationsDeserializer;
import io.adminshell.aas.v3.model.EmbeddedDataSpecification;

public interface HasDataSpecificationMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "embeddedDataSpecification")
    @JsonDeserialize(using = EmbeddedDataSpecificationsDeserializer.class)
    public List<EmbeddedDataSpecification> getEmbeddedDataSpecifications();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "embeddedDataSpecification")
    public void setEmbeddedDataSpecifications(List<EmbeddedDataSpecification> embeddedDataSpecifications);
}
