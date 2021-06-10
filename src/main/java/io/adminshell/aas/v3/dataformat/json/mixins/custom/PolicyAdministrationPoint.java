package io.adminshell.aas.v3.dataformat.json.mixins.custom;

import com.fasterxml.jackson.annotation.JsonInclude;

public interface PolicyAdministrationPoint {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public boolean getExternalAccessControl();
}
