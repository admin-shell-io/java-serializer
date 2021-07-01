/*
Copyright (c) 2021 Fraunhofer IOSB-INA Lemgo,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IOSB-ILT Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IAIS,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IESE,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IWU Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

This source code is licensed under the Apache License 2.0 (see LICENSE.txt).

This source code may use other Open Source software components (see LICENSE.txt).
*/

package io.adminshell.aas.v3.dataformat.json.modeltype;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Helper class to deal with nested nature of modelType property in JSON. As
 * Jackson can not natively deal with such nested type information, this class
 * offers functions to unwrapp the modelType information via the preprocess(...)
 * method and wrap it again via postprocess(...).
 */
public class ModelTypeProcessor {

    private static final String MODEL_TYPE = "modelType";
    private static final String MODEL_TYPE_NAME = "name";

    /**
     * Unwrapps type information recursively, e.g. converts
     * <pre>
     * "modelType": {
     *      "name": "Foo"
     * }
     * </pre> to
     * <pre>
     * "modelType": "Foo"
     * </pre>
     *
     * @param json json as string
     * @return root node with unwrapped type information
     * @throws JsonProcessingException parsing JSON fails
     */
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

    /**
     * Wraps type information recursively, e.g. converts
     * <pre>
     * "modelType": "Foo"
     * </pre> to
     * <pre>
     * "modelType": {
     *      "name": "Foo"
     * }
     * </pre>
     *
     * @param node root node
     * @return transformed root node
     * @throws JsonProcessingException parsing JSON fails
     */
    public static JsonNode postprocess(JsonNode node) throws JsonProcessingException {
        JsonTreeProcessor.traverse(node,
                x -> {
                    if (x.get(MODEL_TYPE) != null && x.get(MODEL_TYPE).isTextual()) {
                        ObjectNode nodeModelType = JsonNodeFactory.instance.objectNode();
                        nodeModelType.set(MODEL_TYPE_NAME, JsonNodeFactory.instance.textNode(x.get(MODEL_TYPE).asText()));
                        x.replace(MODEL_TYPE, nodeModelType);
                    }
                });
        return node;
    }
}
