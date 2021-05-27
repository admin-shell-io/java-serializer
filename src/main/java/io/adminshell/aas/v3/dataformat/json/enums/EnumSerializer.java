package io.adminshell.aas.v3.dataformat.json.enums;

import io.adminshell.aas.v3.dataformat.json.enums.CustomEnumNaming;
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
