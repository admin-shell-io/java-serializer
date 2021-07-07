/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
