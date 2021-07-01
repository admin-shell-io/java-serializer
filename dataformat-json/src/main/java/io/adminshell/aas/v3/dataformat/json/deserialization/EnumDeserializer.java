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

package io.adminshell.aas.v3.dataformat.json.deserialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * Deserializes enum values converting element names from UpperCamelCase to
 * SCREAMING_SNAKE_CASE
 *
 * @param <T> Type of enum to deserialize
 */
public class EnumDeserializer<T extends Enum> extends JsonDeserializer<T> {

    protected static final char UNDERSCORE = '_';
    protected final Class<T> type;

    public EnumDeserializer(Class<T> type) {
        this.type = type;
    }

    @Override
    public T deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        return (T) Enum.valueOf(type, translate(parser.getText()));
    }

    protected String translate(String input) {
        String result = "";
        if (input == null || input.isEmpty()) {
            return result;
        }
        result += input.charAt(0);
        for (int i = 1; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                result += UNDERSCORE;
            }
            result += Character.toUpperCase(currentChar);
        }
        return result;
    }
}
