package io.adminshell.aas.v3.dataformat.json.modeltype;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ModelTypeProcessor {

    private static final String MODEL_TYPE = "modelType";
    private static final String MODEL_TYPE_NAME = "name";

    public static JsonNode preprocess(String json) throws JsonProcessingException {
        JsonNode result = new ObjectMapper().readTree(json);
        JsonTreeProcessor.traverse(result,
                x -> {
                    if (x.get(MODEL_TYPE) != null) {
                        x.replace(MODEL_TYPE, x.get(MODEL_TYPE).get(MODEL_TYPE_NAME));
                    }
                });
        return result;
    }

    public static void postprocess(JsonNode node) throws JsonProcessingException {
        JsonTreeProcessor.traverse(node,
                x -> {
                    if (x.get(MODEL_TYPE) != null && x.get(MODEL_TYPE).isTextual()) {
                        ObjectNode nodeModelType = JsonNodeFactory.instance.objectNode();
                        nodeModelType.set(MODEL_TYPE_NAME, JsonNodeFactory.instance.textNode(x.get(MODEL_TYPE).asText()));
                        x.replace(MODEL_TYPE, nodeModelType);
                    }
                });
    }
}
