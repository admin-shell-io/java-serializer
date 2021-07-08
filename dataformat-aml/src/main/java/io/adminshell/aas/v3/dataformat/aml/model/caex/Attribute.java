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

public class Attribute {

    public Attribute(String name, String attributeDataType, String description, String value, RefSemantic refSemantic, List<Attribute> attributes) {
        this.name = name;
        this.attributeDataType = attributeDataType;
        this.description = description;
        this.value = value;
        this.refSemantic = refSemantic;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttributeDataType() {
        return attributeDataType;
    }

    public void setAttributeDataType(String attributeDataType) {
        this.attributeDataType = attributeDataType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RefSemantic getRefSemantic() {
        return refSemantic;
    }

    public void setRefSemantic(RefSemantic refSemantic) {
        this.refSemantic = refSemantic;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    private String name;

    private String attributeDataType;

    private String description;

    private String value;

    private RefSemantic refSemantic;

    private List<Attribute> attributes;
}
