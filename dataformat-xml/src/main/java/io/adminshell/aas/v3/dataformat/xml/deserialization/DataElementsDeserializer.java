package io.adminshell.aas.v3.dataformat.xml.deserialization;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import io.adminshell.aas.v3.model.DataElement;
import io.adminshell.aas.v3.model.SubmodelElement;

public class DataElementsDeserializer extends JsonDeserializer<List<DataElement>> {

    SubmodelElementDeserializer deserializer = new SubmodelElementDeserializer();

    public DataElementsDeserializer(SubmodelElementDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    public DataElementsDeserializer() {
    }

    @Override
    public List<DataElement> deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        TreeNode treeNode = DeserializationHelper.getRootTreeNode(parser);
        if (treeNode instanceof TextNode) {
            return Collections.emptyList();
        }
        ObjectNode node = (ObjectNode) treeNode;
        ObjectNode dataElementNode = (ObjectNode) node.get("dataElement");
        DataElement elem = createDataElementFromNode(parser, ctxt, dataElementNode);
        return Collections.singletonList(elem);
    }

    private DataElement createDataElementFromNode(JsonParser parser, DeserializationContext ctxt, ObjectNode dataElementNode) throws IOException, JsonProcessingException {
        return (DataElement) DeserializationHelper.createInstanceFromNode(parser, dataElementNode, SubmodelElement.class);
    }

}
