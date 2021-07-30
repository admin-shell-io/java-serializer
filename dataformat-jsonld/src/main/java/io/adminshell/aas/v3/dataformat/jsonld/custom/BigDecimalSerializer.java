package io.adminshell.aas.v3.dataformat.jsonld.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.math.BigDecimal;

public class BigDecimalSerializer extends StdSerializer<BigDecimal> {

    public BigDecimalSerializer() {
        this(null);
    }

    public BigDecimalSerializer(Class clazz) {
        super(clazz);
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("@value", value.toString());
        gen.writeStringField("@type", "http://www.w3.org/2001/XMLSchema#decimal");
        gen.writeEndObject();
    }
}