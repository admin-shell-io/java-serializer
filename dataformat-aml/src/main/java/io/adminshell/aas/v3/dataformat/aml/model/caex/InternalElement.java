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

import java.util.List;
import java.util.UUID;

public class InternalElement {

    public InternalElement(String id, String name, RoleRequirements roleRequirements, List<InternalElement> internalElements, List<Attribute> attributes, List<ExternalInterface> externalInterfaces) {
        this.id = id;
        this.name = name;
        this.roleRequirements = roleRequirements;
        this.internalElements = internalElements;
        this.attributes = attributes;
        this.externalInterfaces = externalInterfaces;
    }

    private String id;

    private String name;

    private RoleRequirements roleRequirements;

    private List<InternalElement> internalElements;

    private List<Attribute> attributes;

    private List<ExternalInterface> externalInterfaces;

    public InternalElement() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleRequirements getRoleRequirements() {
        return roleRequirements;
    }

    public void setRoleRequirements(RoleRequirements roleRequirements) {
        this.roleRequirements = roleRequirements;
    }

    public List<InternalElement> getInternalElements() {
        return internalElements;
    }

    public void setInternalElements(List<InternalElement> internalElements) {
        this.internalElements = internalElements;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<ExternalInterface> getExternalInterfaces() {
        return externalInterfaces;
    }

    public void setExternalInterfaces(List<ExternalInterface> externalInterfaces) {
        this.externalInterfaces = externalInterfaces;
    }
}
