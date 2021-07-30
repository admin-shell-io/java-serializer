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
package io.adminshell.aas.v3.dataformat.aml;

public class ValueMapping {

    private Object value;
    private MappingMode mappingMode;

    public ValueMapping(Object result) {
        this.value = result;
        this.mappingMode = MappingMode.VALUE;
    }

    public ValueMapping(Object result, MappingMode mappingMode) {
        this.value = result;
        this.mappingMode = mappingMode;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public MappingMode getMappingMode() {
        return mappingMode;
    }

    public void setMappingMode(MappingMode mappingMode) {
        this.mappingMode = mappingMode;
    }

    public static enum MappingMode {
        VALUE,
        RECURSIVE
    }
}
