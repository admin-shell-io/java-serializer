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
import io.adminshell.aas.v3.model.AdministrativeInformation;
import java.util.ArrayList;
import java.util.List;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class AdministrativeInformationToAttributeConverter extends CustomConverter<AdministrativeInformation, Attribute> {

    @Override
    public Attribute convert(AdministrativeInformation source, Type<? extends Attribute> destinationType, MappingContext mappingContext) {
        // Root attribute
        Attribute attribute = new Attribute(
                "administration",
                null,
                null,
                null,
                new RefSemantic(AASNamespace.Identifiable_Administration.getRefSemantic()),
                null
        );

        // Nested attributes
        List<Attribute> nestedAttributes = new ArrayList<>();
        nestedAttributes.add(new Attribute(
                "version",
                null,
                null,
                source.getVersion(),
                new RefSemantic(AASNamespace.AdministrationInformation_Version.getRefSemantic()),
                null
        ));
        nestedAttributes.add(new Attribute(
                "revision",
                null,
                null,
                source.getRevision(),
                new RefSemantic(AASNamespace.AdministrationInformation_Revision.getRefSemantic()),
                null)
        );
        attribute.setAttributes(nestedAttributes);

        return attribute;
    }
}
