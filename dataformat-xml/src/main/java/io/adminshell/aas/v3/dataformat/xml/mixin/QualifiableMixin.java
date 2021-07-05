package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.dataformat.xml.deserialization.ConstraintsDeserializer;
import io.adminshell.aas.v3.dataformat.xml.serialization.ConstraintsSerializer;
import io.adminshell.aas.v3.model.Constraint;

public interface QualifiableMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "qualifiers")
    @JsonSerialize(using = ConstraintsSerializer.class)
    @JsonDeserialize(using = ConstraintsDeserializer.class)
    public List<Constraint> getQualifiers();
}
