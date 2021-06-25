package io.adminshell.aas.v3.dataformat.xml.mixin;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.serialization.SubmodelElementSerializer;
import io.adminshell.aas.v3.model.SubmodelElement;

// Only one element -> needs no order
public interface OperationVariableMixin {

	@JacksonXmlProperty(localName = "aas:value")
	@JsonSerialize(using = SubmodelElementSerializer.class)
	public SubmodelElement getValue();
	
}
