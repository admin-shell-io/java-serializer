package io.adminshell.aas.v3.dataformat.json.mixins.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.PermissionsPerObject;
import io.adminshell.aas.v3.model.SubjectAttributes;
import java.util.List;

public interface AccessPermissionRuleMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public SubjectAttributes getTargetSubjectAttributes();

    @JsonProperty("permissionsPerObject")
    public List<PermissionsPerObject> getPermissionsPerObjects();

    @JsonProperty("permissionsPerObject")
    public void setPermissionsPerObjects(List<PermissionsPerObject> permissionsPerObjects);
}
