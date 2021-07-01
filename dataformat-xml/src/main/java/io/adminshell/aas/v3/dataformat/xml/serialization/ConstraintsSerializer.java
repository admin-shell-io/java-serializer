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

import io.adminshell.aas.v3.model.Constraint;
import io.adminshell.aas.v3.model.Formula;
import io.adminshell.aas.v3.model.Qualifier;

public class ConstraintsSerializer extends JsonSerializer<List<Constraint>> {

	@Override
	public void serialize(List<Constraint> value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {

		ToXmlGenerator xgen = (ToXmlGenerator) gen;
        xgen.writeStartObject();
        
        
        
        for(Constraint constraint : value) {
        	if(constraint instanceof Qualifier) {
        		xgen.writeFieldName("aas:qualifier");				
        	} else if(constraint instanceof Formula) {
        		xgen.writeFieldName("aas:formula");				
			}
        	xgen.writeObject(constraint);
		}
        
        
        xgen.writeEndObject();
		
	}

}
