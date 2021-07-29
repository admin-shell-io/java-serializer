package io.adminshell.aas.v3.dataformat.xml.deserialization;

import com.fasterxml.jackson.databind.JsonNode;

public interface CustomJsonNodeDeserializer<T extends Object> {
    public T readValue(JsonNode node);
}
