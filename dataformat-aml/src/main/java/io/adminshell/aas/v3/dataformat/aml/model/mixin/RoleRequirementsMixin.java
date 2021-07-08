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
