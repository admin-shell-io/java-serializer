package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonInclude;

public interface PolicyDecisionPointMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public boolean getExternalPolicyDecisionPoints();
}
