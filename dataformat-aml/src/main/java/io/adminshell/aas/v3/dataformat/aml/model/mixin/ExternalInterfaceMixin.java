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

public class ExternalInterfaceMixin {

    public ExternalInterfaceMixin(String name, String id, String refBaseClassPath, List<AttributeMixin> attributes) {
        this.name = name;
        this.id = id;
        this.refBaseClassPath = refBaseClassPath;
        this.attributes = attributes;
    }

    public ExternalInterfaceMixin() {
    }

    @JacksonXmlProperty(localName = "Name", isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "Id", isAttribute = true)
    private String id;

    @JacksonXmlProperty(localName = "RefBaseClassPath", isAttribute = true)
    private String refBaseClassPath;

    @JacksonXmlProperty(localName = "Attribute")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AttributeMixin> attributes;

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

    public String getRefBaseClassPath() {
        return refBaseClassPath;
    }

    public void setRefBaseClassPath(String refBaseClassPath) {
        this.refBaseClassPath = refBaseClassPath;
    }

    public List<AttributeMixin> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeMixin> attributes) {
        this.attributes = attributes;
    }
}
