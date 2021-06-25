package io.adminshell.aas.v3.dataformat.xml.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import io.adminshell.aas.v3.model.Key;
import io.adminshell.aas.v3.model.Reference;

public class ReferenceIEC61360Serializer extends JsonSerializer<Reference> {

	@Override
	public void serialize(Reference value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

		ToXmlGenerator xgen = (ToXmlGenerator) gen;
		xgen.writeStartObject();
		
        xgen.writeObjectFieldStart("IEC61360:keys");
        
        for(Key key : value.getKeys()) {
        	xgen.writeObjectFieldStart("IEC61360:key");
        	xgen.setNextIsAttribute(true);
        	xgen.writeFieldName("idType");
        	xgen.writeString(key.getIdType().toString());
            xgen.setNextIsAttribute(true);
            xgen.writeFieldName("type");
            xgen.writeString(key.getType().toString());
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
