package io.adminshell.aas.v3.dataformat.xml.mixin;



import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonPropertyOrder({ "extensions",  "idShort", "displayNames", "category", "descriptions",
	"kind", "semanticId", "qualifiers", "dataSpecifications", "value", "mimeType" })
public interface FileMixin {
	
	@JacksonXmlProperty(localName = "aas:mimeType")
	public String getMimeType();

    @JacksonXmlProperty(localName = "aas:value")
    public String getValue();
	
}
