package io.adminshell.aas.v3.dataformat.xml.mixin;



import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.Reference;

// No order needed -> only one element
public interface HasSemanticsMixin {

	@JacksonXmlProperty(localName = "aas:semanticId")
	public Reference getSemanticId();
	
}
