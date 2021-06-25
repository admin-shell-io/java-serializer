package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.serialization.ConstraintsSerializer;
import io.adminshell.aas.v3.model.Constraint;

// No order needed -> only one element
public interface QualifiableMixin {
	
	@JacksonXmlProperty(localName = "aas:qualifiers")
	@JsonSerialize(using = ConstraintsSerializer.class)
    public List<Constraint> getQualifiers();
    
}
