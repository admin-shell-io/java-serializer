package io.adminshell.aas.v3.dataformat.xml.mixin;



import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import io.adminshell.aas.v3.model.IdentifierType;

// No order needed -> only one element
public interface IdentifierMixin {
    
	@JacksonXmlText
	public String getIdentifier();
	
	@JacksonXmlProperty(localName = "idType", isAttribute = true)
	public IdentifierType getIdType();
	
}
