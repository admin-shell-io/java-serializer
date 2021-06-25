package io.adminshell.aas.v3.dataformat.xml.mixin;



import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.AdministrativeInformation;
import io.adminshell.aas.v3.model.Identifier;

// No order needed -> dealt with in concrete classes
public interface IdentifiableMixin {

	@JacksonXmlProperty(localName = "aas:administration")
	public AdministrativeInformation getAdministration();
	
	@JacksonXmlProperty(localName = "aas:identification")
	public Identifier getIdentification();

}
