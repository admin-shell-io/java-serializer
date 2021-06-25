package io.adminshell.aas.v3.dataformat.xml.mixin;



import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.serialization.BlobValueSerializer;

@JsonPropertyOrder({ "extensions",  "idShort", "displayNames", "category", "descriptions",
	"kind", "semanticId", "qualifiers", "dataSpecifications", "value", "mimeType" })
public interface BlobMixin {
	
	@JacksonXmlProperty(localName = "aas:mimeType")
	public String getMimeType();

    @JacksonXmlProperty(localName = "aas:value")
    @JsonSerialize(using = BlobValueSerializer.class)
    public byte[] getValue();
    
}
