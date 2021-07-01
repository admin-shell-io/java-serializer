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

package io.adminshell.aas.v3.dataformat.xml.enums;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.util.Map;
import java.util.Objects;

public class EnumSerializer<T extends Enum> extends JsonSerializer<T> {

    private final Map<String, T> mapping;

    public EnumSerializer(Map<String, T> mapping) {
        this.mapping = mapping;
    }

    public EnumSerializer(CustomEnumNaming<T> mapping) {
        this.mapping = mapping.getMapping();
    }

    @Override
    public void serialize(Enum value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        for (Map.Entry<String, T> entry : mapping.entrySet()) {
            if (Objects.equals(entry.getValue(), value)) {
                gen.writeString(entry.getKey());
                return;
            }
        }
        provider.findValueSerializer(Enum.class).serialize(value, gen, provider);
    }
}
