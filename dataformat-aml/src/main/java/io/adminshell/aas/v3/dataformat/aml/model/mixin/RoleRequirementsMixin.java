package io.adminshell.aas.v3.dataformat.aml.model.mixin;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class RoleRequirementsMixin {

    public RoleRequirementsMixin(String refBaseRoleClassPath, List<AttributeMixin> attributes) {
        this.refBaseRoleClassPath = refBaseRoleClassPath;
        this.attributes = attributes;
    }

    public RoleRequirementsMixin() {
    }

    @JacksonXmlProperty(localName = "RefBaseRoleClassPath", isAttribute = true)
    private String refBaseRoleClassPath;

    @JacksonXmlProperty(localName = "Attribute")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AttributeMixin> attributes;

    public String getRefBaseRoleClassPath() {
        return refBaseRoleClassPath;
    }

    public void setRefBaseRoleClassPath(String refBaseRoleClassPath) {
        this.refBaseRoleClassPath = refBaseRoleClassPath;
    }

    public List<AttributeMixin> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeMixin> attributes) {
        this.attributes = attributes;
    }
}
