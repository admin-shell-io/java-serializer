package io.adminshell.aas.v3.dataformat.xml.mixin;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.serialization.ReferenceIEC61360Serializer;
import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({ "valueId", "value" })
public interface ValueReferencePairMixin {
	
	@JacksonXmlProperty(localName = "IEC61360:valueId")
	@JsonSerialize(using = ReferenceIEC61360Serializer.class)
	Reference getValueId();

	@JacksonXmlProperty(localName = "IEC61360:value")
	String getValue();
	
}
