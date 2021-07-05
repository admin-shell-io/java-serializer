package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.model.OperationVariable;

@JsonPropertyOrder({"extensions", "idShort", "displayNames", "category", "descriptions", "kind", "semanticId",
    "qualifiers", "dataSpecifications", "inoutputVariables", "inputVariables", "outputVariables"})
public interface OperationMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "inputVariable")
    public List<OperationVariable> getInputVariables();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "inoutputVariable")
    public List<OperationVariable> getInoutputVariables();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "outputVariable")
    public List<OperationVariable> getOutputVariables();
}
