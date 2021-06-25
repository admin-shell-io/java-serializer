package io.adminshell.aas.v3.dataformat.xml.mixin;



import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({ "semanticId", "name", "valueType", "value", "refersTo" })
public interface ExtensionMixin {
	
	@JacksonXmlProperty(localName = "aas:name")
	public String getName();
	
	@JacksonXmlProperty(localName = "aas:valueType")
	public String getValueType();
	
	@JacksonXmlProperty(localName = "aas:value")
	public String getValue();

	@JacksonXmlProperty(localName = "aas:refersTo")
	public Reference getRefersTo();
		
}