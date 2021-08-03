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
package io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper;

import io.adminshell.aas.v3.dataformat.aml.AmlGenerator;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.Aas2AmlMappingContext;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.model.Operation;

public class OperationMapper implements Aas2AmlElementMapper<Operation> {

    public OperationMapper() {
    }

    @Override
    public void map(Operation operation, AmlGenerator generator, Aas2AmlMappingContext context) {
        InternalElementType.Builder builder = InternalElementType.builder()
                .withName(context.getInternalElementNamingStrategy().getName(Operation.class, operation, null));
        generator.with(builder).appendReferenceTargetInterfaceIfRequired(operation, context);
        generator.addInternalElement(builder.build());
    }
}
