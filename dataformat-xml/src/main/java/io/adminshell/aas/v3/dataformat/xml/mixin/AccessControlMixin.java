package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.adminshell.aas.v3.model.AccessPermissionRule;

@JsonTypeName("AccessControl")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
public interface AccessControlMixin {
    @JsonProperty("accessPermissionRule")
    public List<AccessPermissionRule> getAccessPermissionRules();

    @JsonProperty("accessPermissionRule")
    public void setAccessPermissionRules(List<AccessPermissionRule> accessPermissionRules);

}
