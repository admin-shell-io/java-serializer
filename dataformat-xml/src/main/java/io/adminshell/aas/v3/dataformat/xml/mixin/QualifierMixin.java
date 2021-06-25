package io.adminshell.aas.v3.dataformat.xml.mixin;



import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({ "semanticId", "valueId", "value", "type", "valueType" })
public interface QualifierMixin {
	
	@JacksonXmlProperty(localName = "aas:valueId")
	public Reference getValueId();
	
	@JacksonXmlProperty(localName = "aas:value")
	public String getValue();
	
	@JacksonXmlProperty(localName = "aas:type")
	public String getType();
	
	@JacksonXmlProperty(localName = "aas:valueType")
	public String getValueType();
	
}
