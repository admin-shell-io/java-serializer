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
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util.ReferenceConverterUtil;
import io.adminshell.aas.v3.model.*;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

import java.util.ArrayList;

public class AssetAdministrationShellMapper extends CustomMapper<AssetAdministrationShell, InternalElement> {

    @Override
    public void mapAtoB(AssetAdministrationShell assetAdministrationShell, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.AssetAdministrationShell.getRefBaseRoleClassPath()));

        // asset information
        // submodel references
        Attribute submodels = new Attribute(
                "submodels",
                null,
                null,
                null,
                new RefSemantic(AASNamespace.AssetAdministrationShell_Submodels.getRefSemantic()),
                null
        );
        submodels.setAttributes(new ArrayList<>());
        assetAdministrationShell.getSubmodels().forEach(submodel -> {
            submodels.getAttributes().add(new Attribute(
                    "reference",
                    null,
                    null,
                    ReferenceConverterUtil.convert(submodel),
                    new RefSemantic(AASNamespace.ReferenceElement_Value.getRefSemantic()),
                    null
            ));
        });
        internalElement.getAttributes().add(submodels);
    }
}
