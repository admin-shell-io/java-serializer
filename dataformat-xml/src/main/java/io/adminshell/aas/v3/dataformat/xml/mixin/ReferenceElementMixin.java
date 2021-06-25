package io.adminshell.aas.v3.dataformat.xml.mixin;



import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({ "extensions",  "idShort", "displayNames", "category", "descriptions",
	"kind", "semanticId", "qualifiers", "dataSpecifications", "value" })
public interface ReferenceElementMixin {

	@JacksonXmlProperty(localName = "aas:value")
	public Reference getValue();
	
}
