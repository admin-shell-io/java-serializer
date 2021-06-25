package io.adminshell.aas.v3.dataformat.xml.enums;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.util.Map;

public class EnumDeserializer<T extends Enum> extends JsonDeserializer<T> {

    private final Map<String, T> mapping;

    public EnumDeserializer(Map<String, T> mapping) {
        this.mapping = mapping;
    }

    public EnumDeserializer(CustomEnumNaming<T> mapping) {
        this.mapping = mapping.getMapping();
    }

    @Override
    public T deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        return mapping.get(parser.getText());
    }

}
