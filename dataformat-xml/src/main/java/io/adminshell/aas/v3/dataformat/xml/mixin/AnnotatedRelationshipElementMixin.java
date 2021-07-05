package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.dataformat.xml.deserialization.DataElementsDeserializer;
import io.adminshell.aas.v3.dataformat.xml.serialization.DataElementsSerializer;
import io.adminshell.aas.v3.model.DataElement;

@JsonPropertyOrder({"extensions", "idShort", "displayNames", "category", "descriptions", "kind", "semanticId",
    "qualifiers", "dataSpecifications", "first", "second", "annotations"})
public interface AnnotatedRelationshipElementMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "annotations")
    @JsonSerialize(using = DataElementsSerializer.class)
    @JsonDeserialize(using = DataElementsDeserializer.class)
    public List<DataElement> getAnnotations();
}
