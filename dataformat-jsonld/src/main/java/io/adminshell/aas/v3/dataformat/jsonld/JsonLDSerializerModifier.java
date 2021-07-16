package io.adminshell.aas.v3.dataformat.jsonld;


import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import io.adminshell.aas.v3.dataformat.jsonld.JsonLDSerializer;


public class JsonLDSerializerModifier extends BeanSerializerModifier {


    public JsonLDSerializerModifier() {
    }

    @Override
    public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc, JsonSerializer<?> serializer) {
        if (serializer instanceof BeanSerializerBase) {
            return new JsonLDSerializer((BeanSerializerBase) serializer);
        } else {
            return serializer;
        }
    }
}
