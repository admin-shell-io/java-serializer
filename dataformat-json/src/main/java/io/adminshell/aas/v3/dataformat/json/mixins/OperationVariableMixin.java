package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.adminshell.aas.v3.model.SubmodelElement;

public interface OperationVariableMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public SubmodelElement getValue();
}
