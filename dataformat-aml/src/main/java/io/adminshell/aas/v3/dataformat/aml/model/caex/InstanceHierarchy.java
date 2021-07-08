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

public class InstanceHierarchy {

    public InstanceHierarchy() {
    }

    public InstanceHierarchy(String name, String version, List<InternalElement> internalElements) {
        this.name = name;
        this.version = version;
        this.internalElements = internalElements;
    }

    private String name;

    private String version;

    private List<InternalElement> internalElements;

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

    public List<InternalElement> getInternalElements() {
        return internalElements;
    }

    public void setInternalElements(List<InternalElement> internalElements) {
        this.internalElements = internalElements;
    }
}
