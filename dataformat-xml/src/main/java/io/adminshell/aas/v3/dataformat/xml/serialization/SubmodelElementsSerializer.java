package io.adminshell.aas.v3.dataformat.xml.serialization;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import io.adminshell.aas.v3.model.SubmodelElement;

public class SubmodelElementsSerializer extends JsonSerializer<List<SubmodelElement>> {

    private SubmodelElementSerializer ser = new SubmodelElementSerializer();

    @Override
    public void serialize(List<SubmodelElement> value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        ToXmlGenerator xgen = (ToXmlGenerator) gen;
        xgen.writeStartObject();
        for (SubmodelElement element : value) {
            xgen.writeFieldName("submodelElement");
            ser.serialize(element, xgen, serializers);
        }
        xgen.writeEndObject();
    }
}
