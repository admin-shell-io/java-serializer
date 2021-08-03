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
import io.adminshell.aas.v3.dataformat.aml.model.caex.AttributeType;

public abstract class ToAttributeMapper<T> implements Aas2AmlElementMapper<T> {

    protected void mapAsAttribute(Object value, AmlGenerator generator, Aas2AmlMappingContext context) {
        if (value != null && context.getProperty() != null) {
            generator.addAttribute(AttributeType.builder()
                    .withName(context.getProperty().getName())
                    .withValue(value)
                    .withRefSemantic(AttributeType.RefSemantic.builder()
                            .withCorrespondingAttributePath("AAS:" + context.getProperty().getReadMethod().getDeclaringClass().getSimpleName() + "/" + context.getProperty().getName())
                            .build())
                    .build());
        }
    }
}
