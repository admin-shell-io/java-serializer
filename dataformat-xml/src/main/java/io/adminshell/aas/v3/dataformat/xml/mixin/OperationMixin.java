package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.OperationVariable;

@JsonPropertyOrder({ "extensions", "idShort", "displayNames", "category", "descriptions",
	"kind", "semanticId", "qualifiers", "dataSpecifications", "inoutputVariables",
	"inputVariables", "outputVariables" })
public interface OperationMixin {
	
	@JacksonXmlProperty(localName = "aas:inputVariable")
    public List<OperationVariable> getInputVariables();

	@JacksonXmlProperty(localName = "aas:inoutputVariable")
    public List<OperationVariable> getInoutputVariables();

	@JacksonXmlProperty(localName = "aas:outputVariable")
    public List<OperationVariable> getOutputVariables();

}
