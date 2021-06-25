package io.adminshell.aas.v3.dataformat.xml.mixin;



import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({ "extensions",  "idShort", "displayNames", "category", "descriptions",
	"kind", "semanticId", "qualifiers", "dataSpecifications", "max", "min", "valueType" })
public interface RangeMixin {
	
	@JacksonXmlProperty(localName = "aas:max")
	public String getMax();
	
	@JacksonXmlProperty(localName = "aas:min")
	public String getMin();

	@JacksonXmlProperty(localName = "aas:valueType")
	public String getValueType();
	
}
