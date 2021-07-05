package io.adminshell.aas.v3.dataformat.xml.deserialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.adminshell.aas.v3.dataformat.xml.SubmodelElementManager;
import io.adminshell.aas.v3.model.SubmodelElement;

public class SubmodelElementDeserializer extends JsonDeserializer<SubmodelElement> {

    @Override
    public SubmodelElement deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectNode node = DeserializationHelper.getRootObjectNode(parser);
        String elemName = findSubmodelElementName(parser, node);
        JsonNode nodeContent = node.get(elemName);
        return (SubmodelElement) DeserializationHelper.createInstanceFromNode(parser, nodeContent, SubmodelElementManager.getClassByXmlName(elemName));
    }

    private String findSubmodelElementName(JsonParser parser, ObjectNode node) throws JsonMappingException {
        for (String value : SubmodelElementManager.NAME_TO_CLASS.keySet()) {
            if (node.has(value)) {
                return value;
            }
        }
        throw new JsonMappingException(parser, "Unknown element " + node);
    }
}
