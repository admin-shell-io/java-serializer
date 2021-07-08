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

public class InstanceHierarchyMixin {

    public InstanceHierarchyMixin(String name, String version, List<InternalElementMixin> internalElements) {
        this.name = name;
        this.version = version;
        this.internalElements = internalElements;
    }

    public InstanceHierarchyMixin() {
    }

    @JacksonXmlProperty(localName = "Name", isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "Version")
    private String version;

    @JacksonXmlProperty(localName = "InternalElement")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<InternalElementMixin> internalElements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<InternalElementMixin> getInternalElements() {
        return internalElements;
    }

    public void setInternalElements(List<InternalElementMixin> internalElements) {
        this.internalElements = internalElements;
    }
}
