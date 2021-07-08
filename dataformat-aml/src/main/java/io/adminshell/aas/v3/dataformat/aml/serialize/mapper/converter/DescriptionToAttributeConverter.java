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
package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AASNamespace;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import io.adminshell.aas.v3.model.LangString;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

import java.util.ArrayList;
import java.util.List;

public class DescriptionToAttributeConverter extends CustomConverter<List<LangString>, Attribute> {

    @Override
    public Attribute convert(List<LangString> source, Type<? extends Attribute> destinationType, MappingContext mappingContext) {
        if(source.isEmpty()) {
            return null;
        }

        // Root attribute
        Attribute attribute = new Attribute(
                "description",
                null,
                null,
                null,
                new RefSemantic(AASNamespace.Referable_Description.getRefSemantic()),
                null
        );

        // Nested attributes
        List<Attribute> nestedAttributes = new ArrayList<>();
        attribute.setAttributes(nestedAttributes);

        source.forEach(langString -> {
            nestedAttributes.add(new Attribute(
                    "aml-lang=" + langString.getLanguage(),
                    null,
                    null,
                    langString.getValue(),
                   null,
                    null
            ));;
        });

        return attribute;
    }
}
