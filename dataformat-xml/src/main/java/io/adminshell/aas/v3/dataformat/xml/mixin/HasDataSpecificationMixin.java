package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.Reference;

// No order needed -> only one element
public interface HasDataSpecificationMixin {
	
	@JacksonXmlProperty(localName = "aas:dataSpecification")
    @JacksonXmlElementWrapper(localName = "aas:embeddedDataSpecification")
	public List<Reference> getDataSpecifications();
	
}
