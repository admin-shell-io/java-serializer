package io.adminshell.aas.v3.dataformat.xml.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import io.adminshell.aas.v3.dataformat.xml.SubmodelElementManager;
import io.adminshell.aas.v3.model.SubmodelElement;

public class SubmodelElementSerializer extends JsonSerializer<SubmodelElement> {

    @Override
    public void serialize(SubmodelElement value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        ToXmlGenerator xgen = (ToXmlGenerator) gen;
        xgen.writeStartObject();
        String name = SubmodelElementManager.CLASS_TO_NAME.get(value.getClass());
        xgen.writeFieldName(name);
        xgen.writeObject(value);
        xgen.writeEndObject();
    }
}
