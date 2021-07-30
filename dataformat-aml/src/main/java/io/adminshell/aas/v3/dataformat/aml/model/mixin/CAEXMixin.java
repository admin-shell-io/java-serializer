/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.adminshell.aas.v3.dataformat.aml.model.mixin;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class CAEXMixin {

    public CAEXMixin(String xmlns, String xsi, String fileName, String schemaVersion, AdditionalInformationMixin additionalInformation, InstanceHierarchyMixin instanceHierarchy) {
        this.xmlns = xmlns;
        this.xsi = xsi;
        this.fileName = fileName;
        this.schemaVersion = schemaVersion;
        this.additionalInformation = additionalInformation;
        this.instanceHierarchy = instanceHierarchy;
    }

    public CAEXMixin() {
    }

    @JacksonXmlProperty(localName = "xmlns:xsi", isAttribute = true)
    private String xmlns;

    @JacksonXmlProperty(localName = "xsi:noNamespaceSchemaLocation", isAttribute = true)
    private String xsi;

    @JacksonXmlProperty(localName = "FileName", isAttribute = true)
    private String fileName;

    @JacksonXmlProperty(localName = "SchemaVersion", isAttribute = true)
    private String schemaVersion;

    @JacksonXmlProperty(localName = "AdditionalInformation")
    private AdditionalInformationMixin additionalInformation;

    @JacksonXmlProperty(localName = "InstanceHierarchy")
    private InstanceHierarchyMixin instanceHierarchy;

    @JacksonXmlProperty(localName = "InterfaceClassLib")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> interfaceClassLibs;

    @JacksonXmlProperty(localName = "RoleClassLib")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> roleClassLibs;

    @JacksonXmlProperty(localName = "SystemUnitClassLib")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<String> systemUnitClassLibs;

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getXsi() {
        return xsi;
    }

    public void setXsi(String xsi) {
        this.xsi = xsi;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public AdditionalInformationMixin getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(AdditionalInformationMixin additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public InstanceHierarchyMixin getInstanceHierarchy() {
        return instanceHierarchy;
    }

    public void setInstanceHierarchy(InstanceHierarchyMixin instanceHierarchy) {
        this.instanceHierarchy = instanceHierarchy;
    }

    public List<String> getInterfaceClassLibs() {
        return interfaceClassLibs;
    }

    public void setInterfaceClassLibs(List<String> interfaceClassLibs) {
        this.interfaceClassLibs = interfaceClassLibs;
    }

    public List<String> getRoleClassLibs() {
        return roleClassLibs;
    }

    public void setRoleClassLibs(List<String> roleClassLibs) {
        this.roleClassLibs = roleClassLibs;
    }

    public List<String> getSystemUnitClassLibs() {
        return systemUnitClassLibs;
    }

    public void setSystemUnitClassLibs(List<String> systemUnitClassLibs) {
        this.systemUnitClassLibs = systemUnitClassLibs;
    }
}
