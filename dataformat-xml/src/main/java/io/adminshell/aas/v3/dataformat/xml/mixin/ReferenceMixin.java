package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.Key;

// No order needed -> only one element
public interface ReferenceMixin {
    
	@JacksonXmlProperty(localName = "aas:key")
    @JacksonXmlElementWrapper(localName = "aas:keys")
    public List<Key> getKeys();
    
}
