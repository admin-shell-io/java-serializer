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
package io.adminshell.aas.v3.dataformat.aml.mapper;

import io.adminshell.aas.v3.dataformat.aml.MappingContext;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AttributeType;
import io.adminshell.aas.v3.model.LangString;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.stream.Collectors;

public class LangStringCollectionMapper extends BaseMapper<Collection<LangString>> {

    @Override
    public void map(Collection<LangString> value, MappingContext context) {
        if (context == null || context.getProperty() == null) {
            throw new IllegalArgumentException("context.property must be non-null");
        }
        if (value == null || value.isEmpty()) {
            return;
        }
        Object t = (Class<?>) ((ParameterizedType) context.getProperty().getReadMethod().getGenericReturnType()).getActualTypeArguments()[0];
        context.addAttribute(AttributeType.builder()
                // für collections funktioniert getName so nicht weil value.class == Collection + type erasure
                .withName(context.getMappingProvider().getAttributeNamingStrategy().getName(
                        context.getProperty().getReadMethod().getDeclaringClass(),
                        value,
                        context.getProperty().getName()))
                .withRefSemantic(AttributeType.RefSemantic.builder()
                        .withCorrespondingAttributePath("AAS:" + context.getProperty().getReadMethod().getDeclaringClass().getSimpleName() + "/" + context.getProperty().getName())
                        .build())
                .addAttribute(value.stream()
                        .map(x -> AttributeType.builder()
                        .withName("aml-lang=" + x.getLanguage())
                        .withValue(x.getValue())
                        .build())
                        .collect(Collectors.toList()))
                .build());
    }

}
