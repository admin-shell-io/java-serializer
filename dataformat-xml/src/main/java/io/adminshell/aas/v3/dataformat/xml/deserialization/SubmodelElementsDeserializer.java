package io.adminshell.aas.v3.dataformat.xml.deserialization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import io.adminshell.aas.v3.model.SubmodelElement;

public class SubmodelElementsDeserializer extends JsonDeserializer<List<SubmodelElement>> {

    private SubmodelElementDeserializer deserializer = new SubmodelElementDeserializer();

    public SubmodelElementsDeserializer(SubmodelElementDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    public SubmodelElementsDeserializer() {
    }

    @Override
    public List<SubmodelElement> deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = DeserializationHelper.getRootTreeNode(parser);
        if (treeNode instanceof TextNode) {
            return Collections.emptyList();
        } else {
            return createSubmodelElements(parser, ctxt, treeNode);
        }
    }

    private List<SubmodelElement> createSubmodelElements(JsonParser parser, DeserializationContext ctxt, TreeNode treeNode) throws IOException, JsonProcessingException {
        JsonNode nodeSubmodelElement = getSubmodelElementsNode(treeNode);
        if (nodeSubmodelElement.isArray()) {
            return getSubmodelElementsFromArrayNode(parser, ctxt, (ArrayNode) nodeSubmodelElement);
        } else {
            return getSubmodelElementsFromObjectNode(parser, ctxt, nodeSubmodelElement);
        }
    }

    private List<SubmodelElement> getSubmodelElementsFromObjectNode(JsonParser parser, DeserializationContext ctxt, JsonNode nodeSubmodelElement) throws IOException, JsonProcessingException {
        SubmodelElement elem = getSubmodelElementFromJsonNode(parser, ctxt, nodeSubmodelElement);
        return Collections.singletonList(elem);
    }

    private JsonNode getSubmodelElementsNode(TreeNode temp) {
        ObjectNode objNode = (ObjectNode) temp;
        JsonNode nodeSubmodelElement = objNode.get("submodelElement");
        return nodeSubmodelElement;
    }

    private List<SubmodelElement> getSubmodelElementsFromArrayNode(JsonParser parser, DeserializationContext ctxt, ArrayNode arrayNode) throws IOException, JsonProcessingException {
        List<SubmodelElement> elements = new ArrayList<>();
        for (int i = 0; i < arrayNode.size(); i++) {
            JsonNode jsonNode = arrayNode.get(i);
            SubmodelElement elem = getSubmodelElementFromJsonNode(parser, ctxt, jsonNode);
            elements.add(elem);
        }
        return elements;
    }

    private SubmodelElement getSubmodelElementFromJsonNode(JsonParser parser, DeserializationContext ctxt, JsonNode nodeSubmodelElement) throws IOException, JsonProcessingException {
        JsonParser parserReference = parser.getCodec().getFactory().getCodec().treeAsTokens(nodeSubmodelElement);
        SubmodelElement elem = deserializer.deserialize(parserReference, ctxt);
        return elem;
    }

}
