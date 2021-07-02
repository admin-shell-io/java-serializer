package de.fraunhofer.iais.eis.ids.jsonld;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Map;

public class FallbackSerializer extends StdSerializer<Map<String, Object>> {


	public FallbackSerializer() {
		this(null);
	}

	public FallbackSerializer(Class clazz) {
		super(clazz);
	}


	@Override
	public void serialize(Map<String, Object> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
			gen.writeString(value.toString());
	}

}
