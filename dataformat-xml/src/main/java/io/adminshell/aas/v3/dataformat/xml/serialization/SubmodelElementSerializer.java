package io.adminshell.aas.v3.dataformat.xml.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import io.adminshell.aas.v3.model.AnnotatedRelationshipElement;
import io.adminshell.aas.v3.model.BasicEvent;
import io.adminshell.aas.v3.model.Blob;
import io.adminshell.aas.v3.model.Capability;
import io.adminshell.aas.v3.model.Entity;
import io.adminshell.aas.v3.model.EventElement;
import io.adminshell.aas.v3.model.EventMessage;
import io.adminshell.aas.v3.model.File;
import io.adminshell.aas.v3.model.MultiLanguageProperty;
import io.adminshell.aas.v3.model.Operation;
import io.adminshell.aas.v3.model.OperationVariable;
import io.adminshell.aas.v3.model.Property;
import io.adminshell.aas.v3.model.Range;
import io.adminshell.aas.v3.model.ReferenceElement;
import io.adminshell.aas.v3.model.RelationshipElement;
import io.adminshell.aas.v3.model.SubmodelElement;
import io.adminshell.aas.v3.model.SubmodelElementCollection;

public class SubmodelElementSerializer extends JsonSerializer<SubmodelElement> {

	@Override
	public void serialize(SubmodelElement value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException {
		
		ToXmlGenerator xgen = (ToXmlGenerator) gen;
		xgen.writeStartObject();
		
		String name = "noNameFound";
	    if(value instanceof AnnotatedRelationshipElement) {
		    name = "aas:annotatedRelationshipElement";
	    } else if(value instanceof RelationshipElement) {
			name = "aas:relationshipElement";
		} else if(value instanceof ReferenceElement) {
			name = "aas:referenceElement";
		} else if(value instanceof Property) {
			name = "aas:property";
		} else if(value instanceof File) {
			name = "aas:file";
		} else if(value instanceof Blob) {
			name = "aas:blob";
		} else if(value instanceof Range) {
			name = "aas:range";
		} else if(value instanceof MultiLanguageProperty) {
			name = "aas:multiLanguageProperty";
		} else if(value instanceof Capability) {
			name = "aas:capability";
		} else if(value instanceof Entity) {
			name = "aas:entity";
		} else if(value instanceof BasicEvent) {
			name = "aas:basicEvent";
		} else if(value instanceof EventElement) {
			name = "aas:eventElement";
		} else if(value instanceof EventMessage) {
			name = "aas:eventMessage";
		} else if(value instanceof Operation) {
			name = "aas:operation";
		} else if(value instanceof OperationVariable) {
			name = "aas:operationVariable";
		} else if(value instanceof SubmodelElementCollection) {
			name = "aas:submodelElementCollection";
		}
		
		xgen.writeFieldName(name);
		xgen.writeObject(value);
		
		xgen.writeEndObject();
		
	}

	
	
}
