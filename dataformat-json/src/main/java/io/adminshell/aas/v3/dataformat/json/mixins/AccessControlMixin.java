package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.AccessPermissionRule;
import java.util.List;

public interface AccessControlMixin {

    @JsonProperty("accessPermissionRule")
    public List<AccessPermissionRule> getAccessPermissionRules();

    @JsonProperty("accessPermissionRule")
    public void setAccessPermissionRules(List<AccessPermissionRule> accessPermissionRules);
}
