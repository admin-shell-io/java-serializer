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

import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellRoleClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util.QualifierConverterUtil;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util.UrlEncoderUtil;
import io.adminshell.aas.v3.model.ModelingKind;
import io.adminshell.aas.v3.model.Submodel;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

import java.util.Objects;

public class SubmodelMapper extends CustomMapper<Submodel, InternalElement> {

    @Override
    public void mapAtoB(Submodel submodel, InternalElement internalElement, MappingContext context) {
        if(submodel.getKind() == ModelingKind.TEMPLATE) {
            return;
        }

        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.Submodel.getRefBaseRoleClassPath()));

        // Set name and idShort by identification if idShort is not given
        if (submodel.getIdShort().equals("") || submodel.getIdShort() == null) {
            String idShort = UrlEncoderUtil.encode(submodel.getIdentification().getIdentifier());
            // Set AssetAdministrationShell name
            internalElement.setName(idShort);
            // Set idShort
            internalElement.getAttributes().stream()
                    .filter(Objects::nonNull)
                    .filter(attribute -> attribute.getName().equals("idShort")).findAny().get().setValue(idShort);
        }

        QualifierConverterUtil.createQualifierAttributesForSubmodel(submodel, internalElement);
    }
}
