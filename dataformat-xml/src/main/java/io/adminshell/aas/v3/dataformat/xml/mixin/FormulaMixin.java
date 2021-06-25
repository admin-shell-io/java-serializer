package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.Reference;

// No order needed -> only one element
public interface FormulaMixin {
	
	@JacksonXmlProperty(localName = "aas:reference")
    @JacksonXmlElementWrapper(localName = "aas:dependsOnRefs")
    public List<Reference> getDependsOns();
	
}
