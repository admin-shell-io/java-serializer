package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.dataformat.xml.deserialization.LangStringsDeserializer;
import io.adminshell.aas.v3.dataformat.xml.serialization.LangStringsSerializer;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({"extensions", "idShort", "displayNames", "category", "descriptions", "kind", "semanticId",
    "qualifiers", "dataSpecifications", "valueId", "value"})
public interface MultiLanguagePropertyMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "valueId")
    public Reference getValueId();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "value")
    @JsonSerialize(using = LangStringsSerializer.class)
    @JsonDeserialize(using = LangStringsDeserializer.class)
    public List<LangString> getValues();
}
