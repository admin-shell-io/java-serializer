package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.serialization.SubmodelElementsSerializer;
import io.adminshell.aas.v3.model.SubmodelElement;

@JsonPropertyOrder({ "extensions",  "idShort", "displayNames", "category", "descriptions",
	"kind", "semanticId", "qualifiers", "dataSpecifications", "allowDuplicates", "ordered", "value" })
public interface SubmodelElementCollectionMixin {

    @JacksonXmlProperty(localName = "aas:value")
	@JsonSerialize(using = SubmodelElementsSerializer.class)
    public Collection<SubmodelElement> getValues();
	
	@JacksonXmlProperty(localName = "aas:allowDuplicates")
	public boolean getAllowDuplicates();
	
	@JacksonXmlProperty(localName = "aas:ordered")
	public boolean getOrdered();

}
