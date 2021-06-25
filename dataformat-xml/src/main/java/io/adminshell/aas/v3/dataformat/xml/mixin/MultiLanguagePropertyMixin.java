package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.serialization.LangStringsAASSerializer;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({ "extensions",  "idShort", "displayNames", "category", "descriptions",
	"kind", "semanticId", "qualifiers", "dataSpecifications", "valueId", "value" })
public interface MultiLanguagePropertyMixin {

	@JacksonXmlProperty(localName = "aas:valueId")
	public Reference getValueId();
	
	@JacksonXmlProperty(localName = "aas:value")
	@JsonSerialize(using = LangStringsAASSerializer.class)
	public List<LangString> getValues();
	
}
