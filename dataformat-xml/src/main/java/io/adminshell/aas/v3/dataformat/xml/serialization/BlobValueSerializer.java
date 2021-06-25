package io.adminshell.aas.v3.dataformat.xml.serialization;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

public class BlobValueSerializer extends JsonSerializer<byte[]> {

	@Override
	public void serialize(byte[] value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		ToXmlGenerator xgen = (ToXmlGenerator) gen;
        xgen.writeString(Base64.getEncoder().encodeToString(value));
	}

}
