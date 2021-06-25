package io.adminshell.aas.v3.dataformat.xml.mixin;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({ "revision", "version" })
public interface AdministrativeInformationMixin {

	@JacksonXmlProperty(localName = "aas:revision")
	public String getRevision();
	
	@JacksonXmlProperty(localName = "aas:version")
	public String getVersion();
	
	@JsonIgnore
	public List<Reference> getDataSpecifications();
	
}
