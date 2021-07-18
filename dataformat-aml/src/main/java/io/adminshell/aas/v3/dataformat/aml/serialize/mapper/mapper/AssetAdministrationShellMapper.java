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

        // AssetInformation
        Attribute assetInformation = new Attribute(
                "assetInformation",
                null,
                null,
                null,
                new RefSemantic(AASNamespace.AssetAdministrationShell_AssetInformation.getRefSemantic()),
                null
        );
        assetInformation.setAttributes(new ArrayList<>());
        assetInformation.getAttributes().add(new Attribute(
                "assetKind",
                null,
                null,
                assetAdministrationShell.getAssetInformation().getAssetKind().name(),
                new RefSemantic(AASNamespace.AssetInformation_AssetKind.getRefSemantic()),
                null
        ));
        assetInformation.getAttributes().add(new Attribute(
                "globalAssetId",
                null,
                null,
                ReferenceConverterUtil.convert(assetAdministrationShell.getAssetInformation().getGlobalAssetId()),
                new RefSemantic(AASNamespace.AssetInformation_GlobalAssetId.getRefSemantic()),
                null
        ));
        internalElement.getAttributes().add(assetInformation);

        // Submodels
        assetAdministrationShell.getSubmodels().forEach(submodel -> {
            Attribute submodels = new Attribute(
                    "submodel",
                    null,
                    null,
                    null,
                    new RefSemantic(AASNamespace.AssetAdministrationShell_Submodels.getRefSemantic()),
                    null
            );
            submodels.setAttributes(new ArrayList<>());
            submodels.getAttributes().add(new Attribute(
                    "reference",
                    null,
                    null,
                    ReferenceConverterUtil.convert(submodel),
                    new RefSemantic(AASNamespace.ReferenceElement_Value.getRefSemantic()),
                    null
            ));
            internalElement.getAttributes().add(submodels);
        });

        // View
        assetAdministrationShell.getViews().forEach(view -> {
            Attribute views = new Attribute(
                    "view",
                    null,
                    null,
                    null,
                    new RefSemantic(AASNamespace.AssetAdministrationShell_View.getRefSemantic()),
                    null
            );
            views.setAttributes(new ArrayList<>());
            views.getAttributes().add(new Attribute(
                    "idShort",
                    null,
                    null,
                    view.getIdShort(),
                    new RefSemantic(AASNamespace.View_IdShort.getRefSemantic()),
                    null
            ));
            if(!view.getContainedElements().isEmpty()) {
                views.getAttributes().add(new Attribute(
                        "containedElement",
                        null,
                        null,
                        ReferenceConverterUtil.convert(view.getContainedElements().get(0)),
                        new RefSemantic(AASNamespace.View_ContainedElement.getRefSemantic()),
                        null
                ));
            }
            internalElement.getAttributes().add(views);
        });
    }
}
