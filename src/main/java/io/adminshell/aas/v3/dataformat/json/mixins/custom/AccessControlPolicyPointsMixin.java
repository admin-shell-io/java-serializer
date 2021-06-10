package io.adminshell.aas.v3.dataformat.json.mixins.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.adminshell.aas.v3.model.PolicyAdministrationPoint;
import io.adminshell.aas.v3.model.PolicyDecisionPoint;
import io.adminshell.aas.v3.model.PolicyEnforcementPoints;

public interface AccessControlPolicyPointsMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public PolicyAdministrationPoint getPolicyAdministrationPoint();

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public PolicyDecisionPoint getPolicyDecisionPoint();

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public PolicyEnforcementPoints getPolicyEnforcementPoint();
}
