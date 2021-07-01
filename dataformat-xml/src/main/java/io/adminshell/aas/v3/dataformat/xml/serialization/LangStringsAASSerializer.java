/*
Copyright (c) 2021 Fraunhofer IOSB-INA Lemgo,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IOSB-ILT Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IAIS,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IESE,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IWU Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

This source code is licensed under the Apache License 2.0 (see LICENSE.txt).

This source code may use other Open Source software components (see LICENSE.txt).
*/

package io.adminshell.aas.v3.dataformat.xml.serialization;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import io.adminshell.aas.v3.model.LangString;

public class LangStringsAASSerializer extends JsonSerializer<List<LangString>> {

	@Override
	public void serialize(List<LangString> value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		
		ToXmlGenerator xgen = (ToXmlGenerator) gen;
        xgen.writeStartObject();
        
        if(value.size() == 0) {
        	value.add(new LangString());
        }
        
        for (LangString langString : value) {
            xgen.writeObjectFieldStart("aas:langString");
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
