package io.adminshell.aas.v3.dataformat.xml.serialization;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import io.adminshell.aas.v3.model.LangString;

public class LangStringsIEC61360Serializer extends JsonSerializer<List<LangString>> {

	@Override
	public void serialize(List<LangString> value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		
		ToXmlGenerator xgen = (ToXmlGenerator) gen;
        xgen.writeStartObject();
        
        if(value.size() == 0) {
        	value.add(new LangString());
        }
        
        for (LangString langString : value) {
            xgen.writeObjectFieldStart("IEC61360:langString");
            xgen.setNextIsAttribute(true);
            xgen.writeFieldName("lang");
            xgen.writeString(langString.getLanguage());
            xgen.setNextIsAttribute(false);
            xgen.setNextIsUnwrapped(true);
            xgen.writeFieldName("value");
            xgen.writeString(langString.getValue());
            xgen.writeEndObject();
		}
        
        xgen.writeEndObject();
		
	}

}
