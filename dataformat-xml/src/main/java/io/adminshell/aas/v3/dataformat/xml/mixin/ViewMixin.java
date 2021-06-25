package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({ "extensions", "idShort", "displayNames", "category",
	"descriptions", "administration", "identification", "semanticId",
	"dataSpecifications", "containedElements" })
public interface ViewMixin {
    
	@JacksonXmlProperty(localName = "aas:containedElementRef")
	@JacksonXmlElementWrapper(localName = "aas:containedElements")
	@JsonInclude(JsonInclude.Include.ALWAYS)
    public List<Reference> getContainedElements();

}
