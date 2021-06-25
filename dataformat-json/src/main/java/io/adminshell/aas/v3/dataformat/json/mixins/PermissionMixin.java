package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.adminshell.aas.v3.model.PermissionKind;
import io.adminshell.aas.v3.model.Reference;

public interface PermissionMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public PermissionKind getKindOfPermission();

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public Reference getPermission();
}
