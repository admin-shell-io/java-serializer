package io.adminshell.aas.v3.dataformat.xml.mixin;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "extensions",  "idShort", "displayNames", "category", "descriptions",
	"kind", "semanticId", "qualifiers", "dataSpecifications" })
public interface CapabilityMixin {

}
