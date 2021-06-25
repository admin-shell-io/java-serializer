package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.serialization.SubmodelElementsSerializer;
import io.adminshell.aas.v3.model.SubmodelElement;

@JsonPropertyOrder({ "extensions", "idShort", "displayNames", "category", "descriptions",
	"administration", "identification", "kind", "semanticId", "qualifiers",
	"dataSpecifications", "submodelElements" })
public interface SubmodelMixin {
 
	@JacksonXmlProperty(localName = "aas:submodelElements")
	@JsonSerialize(using = SubmodelElementsSerializer.class)
    public List<SubmodelElement> getSubmodelElements();

}