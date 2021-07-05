package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.dataformat.xml.deserialization.LangStringsDeserializer;
import io.adminshell.aas.v3.dataformat.xml.serialization.LangStringsSerializer;
import io.adminshell.aas.v3.model.LangString;

@JsonPropertyOrder({"extensions", "idShort", "displayNames", "category", "descriptions"})
public interface ReferableMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "description")
    @JsonSerialize(using = LangStringsSerializer.class)
    @JsonDeserialize(using = LangStringsDeserializer.class)
    public List<LangString> getDescriptions();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "description")
    public void setDescriptions(List<LangString> descriptions);

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "displayName")
    @JsonSerialize(using = LangStringsSerializer.class)
    @JsonDeserialize(using = LangStringsDeserializer.class)
    public List<LangString> getDisplayNames();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "displayName")
    public void setDisplayNames(List<LangString> displayNames);

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "category")
    public String getCategory();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "category")
    public void setCategory(String category);

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "idShort")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    public String getIdShort();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "idShort")
    public void setIdShort(String idShort);
}
