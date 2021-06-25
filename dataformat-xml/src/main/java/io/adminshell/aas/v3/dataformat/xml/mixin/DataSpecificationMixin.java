package io.adminshell.aas.v3.dataformat.xml.mixin;



import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.DataSpecificationContent;

// No order needed -> only one element
public interface DataSpecificationMixin {

	@JacksonXmlProperty(localName = "aas:dataSpecificationIEC61360")
	//@JacksonXmlElementWrapper(localName = "aas:dataSpecificationContent")
	public DataSpecificationContent getDataSpecificationContent();
	
}
