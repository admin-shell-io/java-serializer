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
package io.adminshell.aas.v3.dataformat.aml.aml2aas;

import io.adminshell.aas.v3.dataformat.aml.AmlDocumentInfo;
import io.adminshell.aas.v3.dataformat.mapping.MappingProvider;
import io.adminshell.aas.v3.dataformat.mapping.TargetBasedMappingContext;

public class MappingContext extends TargetBasedMappingContext {

    private AmlDocumentInfo documentInfo;
    private AasTypeFactory typeFactory;
    private Class<?> type;

    public MappingContext(MappingProvider mappingProvider, AasTypeFactory typeFactory) {
        super(mappingProvider);
        this.typeFactory = typeFactory;
        this.type = null;
    }

    private MappingContext(MappingProvider mappingProvider, AasTypeFactory typeFactory, Class<?> type) {
        super(mappingProvider);
        this.typeFactory = typeFactory;
        this.type = type;
    }

    public MappingContext with(Class<?> type) {
        return new MappingContext(mappingProvider, typeFactory, type);
    }

    public AmlDocumentInfo getDocumentInfo() {
        return documentInfo;
    }

    public void setDocumentInfo(AmlDocumentInfo documentInfo) {
        this.documentInfo = documentInfo;
    }

    public Class<?> getType() {
        return type;
    }

    public AasTypeFactory getTypeFactory() {
        return typeFactory;
    }

}
