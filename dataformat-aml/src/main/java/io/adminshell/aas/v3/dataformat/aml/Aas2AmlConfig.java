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

import io.adminshell.aas.v3.dataformat.aml.id.UuidGenerator;
import io.adminshell.aas.v3.dataformat.aml.id.IdGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Aas2AmlConfig {

    private final boolean includeLibraries;
    private final IdGenerator idGenerator;
    private final List<Object> additionalInformation;
    public static final Aas2AmlConfig DEFAULT = new Builder().build();

    public static Builder builder() {
        return new Builder();
    }

    private Aas2AmlConfig(boolean includeLibraries, IdGenerator idGenerator, List<Object> additionalInformation) {
        this.includeLibraries = includeLibraries;
        this.idGenerator = idGenerator;
        this.additionalInformation = additionalInformation;
    }

    public boolean isIncludeLibraries() {
        return includeLibraries;
    }

    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    public List<Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public static class Builder {

        private boolean includeLibraries = true;
        private IdGenerator idGenerator = new UuidGenerator();
        private List<Object> additionalInformation = new ArrayList<>();

        public Aas2AmlConfig build() {
            return new Aas2AmlConfig(includeLibraries, idGenerator, additionalInformation);
        }

        public Builder includeLibraries() {
            this.includeLibraries = true;
            return this;
        }

        public Builder excludeLibraries() {
            this.includeLibraries = true;
            return this;
        }

        public Builder idGenerator(IdGenerator idGenerator) {
            this.idGenerator = idGenerator;
            return this;
        }

        public Builder additionalInformation(Object... values) {
            this.additionalInformation.addAll(Arrays.asList(values));
            return this;
        }
    }
}
