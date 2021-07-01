package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.EmbeddedDataSpecification;
import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({ "extensions", "idShort", "displayNames", "category", "descriptions", "administration",
	"identification",  "dataSpecifications", "isCaseOfs" })
public interface ConceptDescriptionMixin {

	@JacksonXmlProperty(localName = "aas:isCaseOf")
    public List<Reference> getIsCaseOfs();

	@JsonIgnore
    public List<EmbeddedDataSpecification> getEmbeddedDataSpecifications();
    
}
