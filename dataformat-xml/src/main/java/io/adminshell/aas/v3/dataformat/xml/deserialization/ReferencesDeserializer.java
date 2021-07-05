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
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.adminshell.aas.v3.model.Reference;

public class ReferencesDeserializer extends JsonDeserializer<List<Reference>> {

    @Override
    public List<Reference> deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = DeserializationHelper.getRootTreeNode(parser);
        if (treeNode.isArray()) {
            return createReferencesFromArray(parser, (ArrayNode) treeNode);
        } else {
            return createReferencesFromObjectNode(parser, (ObjectNode) treeNode);
        }
    }

    private List<Reference> createReferencesFromObjectNode(JsonParser parser, ObjectNode node) throws IOException {
        Reference reference = createReference(parser, node);
        return Collections.singletonList(reference);
    }

    private List<Reference> createReferencesFromArray(JsonParser parser, ArrayNode arrayNode) throws IOException {
        List<Reference> references = new ArrayList<>();
        for (int i = 0; i < arrayNode.size(); i++) {
            Reference reference = createReference(parser, (ObjectNode) arrayNode.get(i));
            references.add(reference);
        }
        return references;
    }

    private Reference createReference(JsonParser parser, ObjectNode node) throws IOException {
        return DeserializationHelper.createInstanceFromNode(parser, node, Reference.class);
    }

}
