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
package io.adminshell.aas.v3.dataformat.aml.model.caex;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class CAEXFile {

    public CAEXFile() {
    }

    public CAEXFile(String xmlns, String xsi, String schemaVersion, AdditionalInformation additionalInformation, InstanceHierarchy instanceHierarchy) {
        this.xmlns = xmlns;
        this.xsi = xsi;
        this.schemaVersion = schemaVersion;
        this.additionalInformation = additionalInformation;
        this.instanceHierarchy = instanceHierarchy;
    }

    private String xmlns;

    private String xsi;

    private String fileName;

    private String schemaVersion;

    private AdditionalInformation additionalInformation;

    private InstanceHierarchy instanceHierarchy;

    private List<String> interfaceClassLibs;

    private List<String> roleClassLibs;

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

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public AdditionalInformation getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(AdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public InstanceHierarchy getInstanceHierarchy() {
        return instanceHierarchy;
    }

    public void setInstanceHierarchy(InstanceHierarchy instanceHierarchy) {
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
