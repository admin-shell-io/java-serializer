package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.dataformat.xml.deserialization.ReferencesDeserializer;
import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({"extensions", "idShort", "displayNames", "category", "descriptions", "administration",
    "identification", "embeddedDataSpecifications", "isCaseOf"})
public interface ConceptDescriptionMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "isCaseOf")
    @JsonDeserialize(using = ReferencesDeserializer.class)
    public List<Reference> getIsCaseOfs();
}
