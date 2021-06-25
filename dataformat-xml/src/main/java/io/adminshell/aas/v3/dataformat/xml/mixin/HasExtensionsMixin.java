package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.Extension;

// No order needed -> only one element
public interface HasExtensionsMixin {
	
	@JacksonXmlProperty(localName = "aas:extension")
    @JacksonXmlElementWrapper(localName = "aas:extensions")
    public List<Extension> getExtensions();

}
