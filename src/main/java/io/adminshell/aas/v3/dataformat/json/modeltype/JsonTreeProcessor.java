package io.adminshell.aas.v3.dataformat.json.modeltype;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.function.Consumer;

/**
 * Helper class to traverse a JsonNode recursive and applying an operation to
 * each node
 */
public class JsonTreeProcessor {

    private final Consumer<ObjectNode> operator;

    public static void traverse(JsonNode node, Consumer<ObjectNode> operator) {
        new JsonTreeProcessor(operator).traverse(node);
    }

    public JsonTreeProcessor(Consumer<ObjectNode> operator) {
        this.operator = operator;
    }

    public void traverse(JsonNode node) {
        if (null == node.getNodeType()) {
            return;
        }
        switch (node.getNodeType()) {
            case ARRAY:
                node.elements().forEachRemaining(x -> traverse(x));
                break;
            case OBJECT:
                operator.accept((ObjectNode) node);
                node.elements().forEachRemaining(x -> traverse(x));
                break;
            default:
            // do nothing
        }
    }
}
