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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.adminshell.aas.v3.model.LangString;

public class LangStringsDeserializer extends JsonDeserializer<List<LangString>> {

    @Override
    public List<LangString> deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectNode node = DeserializationHelper.getRootObjectNode(parser);
        JsonNode langStringNode = node.get("langString");
        if (langStringNode.isObject()) {
            return createLangStringsFromObjectNode(langStringNode);
        } else {
            return createLangStringsFromArrayNode((ArrayNode) langStringNode);
        }
    }

    private List<LangString> createLangStringsFromArrayNode(ArrayNode langStringsNode) {
        List<LangString> langStrings = new ArrayList<>();
        for (int i = 0; i < langStringsNode.size(); i++) {
            langStrings.add(constructLangString(langStringsNode.get(i)));
        }
        return langStrings;
    }

    private List<LangString> createLangStringsFromObjectNode(JsonNode langStringNode) {
        LangString langString = constructLangString(langStringNode);
        return Collections.singletonList(langString);
    }

    private LangString constructLangString(JsonNode langStringNode) {
        String lang = langStringNode.get("lang").asText();
        String text = langStringNode.get("").asText();
        return new LangString(text, lang);
    }

}
