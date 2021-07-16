package io.adminshell.aas.v3.dataformat.jsonld;

import java.io.IOException;
import java.net.URI;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class UriSerializer extends StdSerializer<URI> {


	public UriSerializer() {
		this(null);
	}

	public UriSerializer(Class clazz) {
		super(clazz);
	}


	@Override
	public void serialize(URI value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		String serializedUri = value.toString();
		//		String idPattern = "{\"@id\": \"" + serializedUri + "\"}";
		JsonStreamContext context = gen.getOutputContext();
		if (context.getCurrentName() != null && context.getCurrentName().contains("@id")) {
			gen.writeString(serializedUri);
		} else {
			gen.writeStartObject();
			gen.writeStringField("@id", serializedUri);
			gen.writeEndObject();
		}
	}

}
