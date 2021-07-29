package io.adminshell.aas.v3.dataformat.xml.deserialization;

import com.fasterxml.jackson.databind.JsonNode;

import io.adminshell.aas.v3.model.LangString;

public class LangStringNodeDeserializer implements CustomJsonNodeDeserializer<LangString> {
    @Override
    public LangString readValue(JsonNode node) {
        String lang = node.get("lang").asText();
        String text = node.get("").asText();
        return new LangString(text, lang);
    }
}
