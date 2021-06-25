package io.adminshell.aas.v3.dataformat.xml.mixin;



import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.ModelingKind;

// No order needed -> only one element
public interface HasKindMixin {

	@JacksonXmlProperty(localName = "aas:kind")
	public ModelingKind getKind();
	
}
