package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.Permission;
import java.util.List;

public interface PermissionsPerObjectMixin {

    @JsonProperty("permission")
    public List<Permission> getPermissions();

    @JsonProperty("permission")
    public void setPermissions(List<Permission> permissions);
}
