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
package io.adminshell.aas.v3.dataformat.core.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;

/**
 * Serializes enum values. If enum is part of the AAS Java model, the name will
 * be converted from SCREAMING_SNAKE_CASE to UpperCamelCase, else default
 * serialization will be used
 */
public class EnumSerializer extends JsonSerializer<Enum> {

    protected static final char UNDERSCORE = '_';

    @Override
    public void serialize(Enum value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (ReflectionHelper.ENUMS.contains(value.getClass())) {
            gen.writeString(translate(value.name()));
        } else {
            provider.findValueSerializer(Enum.class).serialize(value, gen, provider);
        }
    }

    /**
     * Translates an enum value from SCREAMING_SNAKE_CASE to CamelCase
     *
     * @param input input name in SCREAMING_SNAKE_CASE
     * @return name in CamelCase
     */
    public static String translate(String input) {
        String result = "";
        boolean capitalize = true;
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (UNDERSCORE == currentChar) {
                capitalize = true;
            } else {
                result += capitalize
                        ? currentChar
                        : Character.toLowerCase(currentChar);
                capitalize = false;
            }
        }
        return result;
    }
}
