package io.adminshell.aas.v3.dataformat.json.mixins.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.Reference;
import java.util.List;

public interface FormulaMixin {

    @JsonProperty("dependsOn")
    public List<Reference> getDependsOns();

    @JsonProperty("dependsOn")
    public void setDependsOns(List<Reference> dependsOns);
}
