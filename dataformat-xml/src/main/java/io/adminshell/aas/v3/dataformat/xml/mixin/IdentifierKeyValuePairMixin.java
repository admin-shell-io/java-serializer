package io.adminshell.aas.v3.dataformat.xml.mixin;



import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({ "semanticId", "externalSubjectId", "key", "value" })
public interface IdentifierKeyValuePairMixin {
	
	@JacksonXmlProperty(localName = "aas:key")
	public String getKey();
	
	@JacksonXmlProperty(localName = "aas:value")
	public String getValue();

	@JacksonXmlProperty(localName = "aas:externalSubjectId")
	public Reference getExternalSubjectId();
	
}