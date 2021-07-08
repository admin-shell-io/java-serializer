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
package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AASNamespace;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellRoleClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.model.Range;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class RangeMapper extends CustomMapper<Range, InternalElement> {

    @Override
    public void mapAtoB(Range range, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.Range.getRefBaseRoleClassPath()));
        internalElement.getAttributes().add(new Attribute(
                "max",
                "xs:" + range.getValueType(),
                null,
                range.getMax(),
                new RefSemantic(AASNamespace.Range_Max.getRefSemantic()),
                null
        ));
        internalElement.getAttributes().add(new Attribute(
                "min",
                "xs:" + range.getValueType(),
                null,
                range.getMin(),
                new RefSemantic(AASNamespace.Range_Min.getRefSemantic()),
                null
        ));
    }
}
