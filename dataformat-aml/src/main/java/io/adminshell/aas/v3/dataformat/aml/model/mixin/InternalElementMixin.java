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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InternalElementMixin {

    public InternalElementMixin(String name, String id, RoleRequirementsMixin roleRequirements, List<InternalElementMixin> internalElements, List<AttributeMixin> attributes, List<ExternalInterfaceMixin> externalInterfaces) {
        this.name = name;
        this.id = id;
        this.roleRequirements = roleRequirements;
        this.internalElements = internalElements;
        this.attributes = attributes;
        this.externalInterfaces = externalInterfaces;
    }

    public InternalElementMixin() {
    }

    @JacksonXmlProperty(localName = "Name", isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "Id", isAttribute = true)
    private String id;

    @JacksonXmlProperty(localName = "RoleRequirements")
    private RoleRequirementsMixin roleRequirements;

    @JacksonXmlProperty(localName = "InternalElement")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<InternalElementMixin> internalElements;

    @JacksonXmlProperty(localName = "Attribute")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AttributeMixin> attributes;

    @JacksonXmlProperty(localName = "ExternalInterface")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ExternalInterfaceMixin> externalInterfaces;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoleRequirementsMixin getRoleRequirements() {
        return roleRequirements;
    }

    public void setRoleRequirements(RoleRequirementsMixin roleRequirements) {
        this.roleRequirements = roleRequirements;
    }

    public List<InternalElementMixin> getInternalElements() {
        return internalElements;
    }

    public void setInternalElements(List<InternalElementMixin> internalElements) {
        this.internalElements = internalElements;
    }

    public List<AttributeMixin> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeMixin> attributes) {
        this.attributes = attributes;
    }

    public List<ExternalInterfaceMixin> getExternalInterfaces() {
        return externalInterfaces;
    }

    public void setExternalInterfaces(List<ExternalInterfaceMixin> externalInterfaces) {
        this.externalInterfaces = externalInterfaces;
    }
}
