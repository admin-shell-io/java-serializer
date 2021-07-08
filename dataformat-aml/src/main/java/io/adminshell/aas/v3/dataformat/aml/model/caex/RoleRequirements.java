package io.adminshell.aas.v3.dataformat.aml.model.caex;

import java.util.List;

public class RoleRequirements {

    private String refBaseRoleClassPath;

    private List<Attribute> attributes;

    public RoleRequirements() {
    }

    public RoleRequirements(String refBaseRoleClassPath) {
        this.refBaseRoleClassPath = refBaseRoleClassPath;
    }

    public RoleRequirements(String refBaseRoleClassPath, List<Attribute> attributes) {
        this.refBaseRoleClassPath = refBaseRoleClassPath;
        this.attributes = attributes;
    }

    public String getRefBaseRoleClassPath() {
        return refBaseRoleClassPath;
    }

    public void setRefBaseRoleClassPath(String refBaseRoleClassPath) {
        this.refBaseRoleClassPath = refBaseRoleClassPath;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
