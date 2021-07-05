package io.adminshell.aas.v3.dataformat.xml.serialization;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import io.adminshell.aas.v3.dataformat.core.serialization.EnumSerializer;

import io.adminshell.aas.v3.model.Key;
import io.adminshell.aas.v3.model.Reference;

public class NamespaceIndependentReferenceSerializer extends JsonSerializer<Reference> {

    @Override
    public void serialize(Reference value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        ToXmlGenerator xgen = (ToXmlGenerator) gen;
        xgen.writeStartObject();
        xgen.writeObjectFieldStart("keys");
        for (Key key : value.getKeys()) {
            xgen.writeObjectFieldStart("key");
            try {                
                String idTypeValue = EnumSerializer.translate(key.getIdType().toString());
                xgen.getStaxWriter().writeAttribute("idType", idTypeValue);
                String keyTypeValue = EnumSerializer.translate(key.getType().toString());
                xgen.getStaxWriter().writeAttribute("type", keyTypeValue);
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
            xgen.setNextIsAttribute(false);
            xgen.setNextIsUnwrapped(true);
            xgen.writeFieldName("value");
            xgen.writeString(key.getValue());
            xgen.writeEndObject();
        }
        xgen.writeEndObject();
        xgen.writeEndObject();
    }
}
