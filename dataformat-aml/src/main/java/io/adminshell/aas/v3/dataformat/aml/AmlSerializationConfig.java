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

import io.adminshell.aas.v3.dataformat.aml.header.WriterInfo;
import io.adminshell.aas.v3.dataformat.aml.id.UuidGenerator;
import io.adminshell.aas.v3.dataformat.aml.id.IdGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AmlSerializationConfig {

    public static final String DEFAULT_SCHEMA_VERSION = "2.15";
    public static final String DEFAULT_AML_VERSION = "2.0";
    public static final String DEFAULT_FILENAME = "AssetAdministrationShellEnvironment.aml";
    public static final AmlSerializationConfig DEFAULT = new Builder().build();

    public static Builder builder() {
        return new Builder();
    }

    private final IdGenerator idGenerator;
    private final String schemaVersion;
    private final String amlVersion;
    private final String filename;
    private final boolean includeLibraries;
    private final WriterInfo writerInfo;
    private final List<Object> additionalInformation;

    private AmlSerializationConfig(
            IdGenerator idGenerator,
            String schemaVersion,
            String amlVersion,
            String filename,
            boolean includeLibraries,
            WriterInfo writerInfo,
            List<Object> additionalInformation) {
        this.idGenerator = idGenerator;
        this.schemaVersion = schemaVersion;
        this.amlVersion = amlVersion;
        this.filename = filename;
        this.includeLibraries = includeLibraries;
        this.writerInfo = writerInfo;
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

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public String getAmlVersion() {
        return amlVersion;
    }

    public WriterInfo getWriterInfo() {
        return writerInfo;
    }

    public static class Builder {

        private IdGenerator idGenerator = new UuidGenerator();
        private String schemaVersion = DEFAULT_SCHEMA_VERSION;
        private String amlVersion = DEFAULT_AML_VERSION;
        private String filename = DEFAULT_FILENAME;
        private boolean includeLibraries = true;
        private WriterInfo writerInfo = null;
        private List<Object> additionalInformation = new ArrayList<>();

        public AmlSerializationConfig build() {
            return new AmlSerializationConfig(
                    idGenerator,
                    schemaVersion,
                    amlVersion,
                    filename,
                    includeLibraries,
                    writerInfo,
                    additionalInformation);
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

        public Builder schemaVersion(String value) {
            this.schemaVersion = value;
            return this;
        }

        public Builder amlVersion(String value) {
            this.amlVersion = value;
            return this;
        }

        public Builder filename(String value) {
            this.filename = value;
            return this;
        }

        public Builder writerInfo(WriterInfo value) {
            this.writerInfo = value;
            return this;
        }

        public Builder additionalInformation(Object... values) {
            this.additionalInformation.addAll(Arrays.asList(values));
            return this;
        }
    }

    public String getFilename() {
        return filename;
    }
}
