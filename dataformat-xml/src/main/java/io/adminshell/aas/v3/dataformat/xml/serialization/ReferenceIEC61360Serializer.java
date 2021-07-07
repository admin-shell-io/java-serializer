/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
