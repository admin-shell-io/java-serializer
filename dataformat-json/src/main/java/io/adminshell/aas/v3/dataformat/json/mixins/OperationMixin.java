package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.OperationVariable;
import java.util.List;

public interface OperationMixin {

    @JsonProperty("inputVariable")
    public List<OperationVariable> getInputVariables();

    @JsonProperty("inputVariable")
    public void setInputVariables(List<OperationVariable> inputVariables);

    @JsonProperty("inoutputVariable")
    public List<OperationVariable> getInoutputVariables();

    @JsonProperty("inoutputVariable")
    public void setInoutputVariables(List<OperationVariable> inoutputVariables);

    @JsonProperty("outputVariable")
    public List<OperationVariable> getOutputVariables();

    @JsonProperty("outputVariable")
    public void setOutputVariables(List<OperationVariable> outputVariables);
}
