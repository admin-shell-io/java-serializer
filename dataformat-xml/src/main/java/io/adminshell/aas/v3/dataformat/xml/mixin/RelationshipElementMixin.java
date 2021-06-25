package io.adminshell.aas.v3.dataformat.xml.mixin;



import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({ "extensions",  "idShort", "displayNames", "category", "descriptions",
	"kind", "semanticId", "qualifiers", "dataSpecifications", "first",
	"second" })
public interface RelationshipElementMixin {
	
	@JacksonXmlProperty(localName = "aas:first")
	public Reference getFirst();

	@JacksonXmlProperty(localName = "aas:second")
	public Reference getSecond();
	
}
