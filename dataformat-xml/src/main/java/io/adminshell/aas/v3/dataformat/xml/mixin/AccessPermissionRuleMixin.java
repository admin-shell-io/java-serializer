package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.adminshell.aas.v3.model.PermissionsPerObject;

@JsonTypeName("AccessPermissionRule")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
public interface AccessPermissionRuleMixin {
    @JsonProperty("permissionsPerObject")
    public List<PermissionsPerObject> getPermissionsPerObjects();

    @JsonProperty("permissionsPerObject")
    public void setPermissionsPerObjects(List<PermissionsPerObject> permissionsPerObjects);

}
