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

import com.google.inject.util.Types;
import io.adminshell.aas.v3.dataformat.aml.AmlGenerator;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.Aas2AmlMappingContext;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXFile;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXFile.SystemUnitClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.SystemUnitClassType.SupportedRoleClass;
import io.adminshell.aas.v3.dataformat.aml.model.caex.SystemUnitFamilyType;
import io.adminshell.aas.v3.dataformat.aml.util.AASUtils;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.ModelingKind;
import io.adminshell.aas.v3.model.Submodel;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AssetAdministrationShellEnvironmentMapper extends DefaultMapper<AssetAdministrationShellEnvironment> {

    @Override
    public void map(AssetAdministrationShellEnvironment value, AmlGenerator generator, Aas2AmlMappingContext context) throws MappingException {
        List<AssetAdministrationShell> assetAdministrationShells = value.getAssetAdministrationShells().stream()
                .filter(x -> x.getSubmodels().stream()
                .anyMatch(sm -> AASUtils.resolveSubmodelReference(sm, value).get().getKind() == ModelingKind.INSTANCE))
                .collect(Collectors.toList());
        toHierarchy(generator.getDocumentInfo().getAssetAdministrationShellInstanceHierarchy(),
                AssetAdministrationShell.class,
                assetAdministrationShells,
                generator,
                context);
        toHierarchy(
                generator.getDocumentInfo().getConceptDescriptionInstanceHierarchy(),
                ConceptDescription.class,
                value.getConceptDescriptions(),
                generator,
                context);
        mapTemplates(value, generator, context);
    }

    protected <T> void toHierarchy(
            String hierarchyName,
            Class<T> type,
            Collection<T> value,
            AmlGenerator generator,
            Aas2AmlMappingContext context) throws MappingException {
        CAEXFile.InstanceHierarchy.Builder builder = CAEXFile.InstanceHierarchy.builder()
                .withName(hierarchyName);
        context.map(Types.newParameterizedType(Collection.class, type), value, generator.with(builder));
        generator.addInstanceHierarchy(builder.build());
    }

    protected void mapTemplates(AssetAdministrationShellEnvironment env, AmlGenerator generator, Aas2AmlMappingContext context) throws MappingException {
        boolean empty = true;
        SystemUnitClassLib.Builder builder = SystemUnitClassLib.builder()
                .withName(generator.getDocumentInfo().getAssetAdministrationShellSystemUnitClassLib());
        // generate SystemUnitClass for each AAS with at least 1 Submodel with kind == TEMPLATE
        // generate SystemUnitClass for each Submodel with king == TEMPLATE
        for (AssetAdministrationShell aas : env.getAssetAdministrationShells()) {
            List<Submodel> submodelTemplates = AASUtils.getSubmodelTemplates(aas, env);
            if (!submodelTemplates.isEmpty()) {
                empty = false;
                InternalElementType.Builder temp = InternalElementType.builder();
                context.withoutIdCache().withoutProperty().map(aas, generator.with(temp));
                builder.addSystemUnitClass(internalElementToSystemUnitClass(temp.build().getInternalElement().get(0)));
            }
            for (Submodel submodel : submodelTemplates) {
                InternalElementType.Builder temp = InternalElementType.builder();
                context.withoutIdCache().withoutProperty().map(submodel, generator.with(temp));
                builder.addSystemUnitClass(internalElementToSystemUnitClass(temp.build().getInternalElement().get(0)));
            }
        }
        if (!empty) {
            generator.addSystemUnitClassLib(builder.build());
        }
    }

    protected SystemUnitFamilyType internalElementToSystemUnitClass(InternalElementType internalElement) {
        return SystemUnitFamilyType.builder()
                .withName(internalElement.getName())
                .withID(internalElement.getID())
                .addAttribute(internalElement.getAttribute())
                .addInternalElement(internalElement.getInternalElement())
                .withSupportedRoleClass(SupportedRoleClass.builder()
                        .withRefRoleClassPath(internalElement.getRoleRequirements().getRefBaseRoleClassPath())
                        .build())
                .build();
    }
}
