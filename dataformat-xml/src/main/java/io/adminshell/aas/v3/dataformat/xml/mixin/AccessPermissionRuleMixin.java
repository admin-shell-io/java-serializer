package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.model.PermissionsPerObject;

public interface AccessPermissionRuleMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.ABAC_URI, localName = "permissionsPerObject")
    public List<PermissionsPerObject> getPermissionsPerObjects();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.ABAC_URI, localName = "permissionsPerObject")
    public void setPermissionsPerObjects(List<PermissionsPerObject> permissionsPerObjects);
}
