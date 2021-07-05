package io.adminshell.aas.v3.dataformat.xml.serialization;

import java.io.IOException;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import io.adminshell.aas.v3.model.LangString;

public class LangStringsSerializer extends JsonSerializer<List<LangString>> {

    @Override
    public void serialize(List<LangString> value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {

        ToXmlGenerator xgen = (ToXmlGenerator) gen;
        xgen.writeStartObject();
        if (value.isEmpty()) {
            // LangStringSet has [1..*] elements according to schema
            value.add(new LangString());
        }
        for (LangString langString : value) {
            xgen.writeObjectFieldStart("langString");
            try {
                xgen.getStaxWriter().writeAttribute("lang", langString.getLanguage());
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
            xgen.setNextIsAttribute(false);
            xgen.setNextIsUnwrapped(true);
            xgen.writeFieldName("value");
            xgen.writeString(langString.getValue());
            xgen.writeEndObject();
        }
        xgen.writeEndObject();
    }

    @Override
    public boolean isEmpty(SerializerProvider provider, List<LangString> value) {
        return value == null || value.isEmpty();
    }
}
