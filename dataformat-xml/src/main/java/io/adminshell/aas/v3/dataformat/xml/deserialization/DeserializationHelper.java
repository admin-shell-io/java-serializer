package io.adminshell.aas.v3.dataformat.xml.deserialization;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class DeserializationHelper {
    public static <T> T createInstanceFromNode(JsonParser parser, JsonNode node, Class<T> clazz) throws IOException {
        JsonParser parserContent = parser.getCodec().getFactory().getCodec().treeAsTokens(node);
        parserContent.nextToken();
        T instance = parserContent.readValueAs(clazz);
        return instance;
    }

    public static <T> List<T> createInstancesFromArrayNode(JsonParser parser, ArrayNode node, Class<T> clazz) throws IOException {
        List<T> instances = new ArrayList<>();
        for (int i = 0; i < node.size(); i++) {
            T instance = DeserializationHelper.createInstanceFromNode(parser, node.get(i), clazz);
            instances.add(instance);
        }
        return instances;
    }

    public static TreeNode getRootTreeNode(JsonParser parser) throws IOException {
        return parser.getCodec().readTree(parser);
    }

    public static ObjectNode getRootObjectNode(JsonParser parser) throws IOException {
        return (ObjectNode) getRootTreeNode(parser);
    }
}
