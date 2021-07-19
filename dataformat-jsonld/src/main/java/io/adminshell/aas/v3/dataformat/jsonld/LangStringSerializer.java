package io.adminshell.aas.v3.dataformat.jsonld;

import java.io.IOException;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.adminshell.aas.v3.model.LangString;

public class LangStringSerializer extends StdSerializer<LangString> {


    public LangStringSerializer() {
        this(null);
    }

    public LangStringSerializer(Class clazz) {
        super(clazz);
    }


    @Override
    public void serialize(LangString value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        if(value.getLanguage() != null && !value.getLanguage().isEmpty())
        {
            gen.writeStringField("@language", value.getLanguage());
        }
        else
        {
            gen.writeStringField("@type", "rdf:langString");
        }
        gen.writeStringField("@value", value.getValue());
        gen.writeEndObject();
    }

}
