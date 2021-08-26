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
package io.adminshell.aas.v3.dataformat.aml.deserialization.mappers;

import io.adminshell.aas.v3.dataformat.aml.AmlDocumentInfo;
import io.adminshell.aas.v3.dataformat.aml.deserialization.AmlParser;
import io.adminshell.aas.v3.dataformat.aml.deserialization.Mapper;
import io.adminshell.aas.v3.dataformat.aml.deserialization.MappingContext;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShellEnvironment;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AssetAdministrationShellEnvironmentMapper implements Mapper<AssetAdministrationShellEnvironment> {

    @Override
    public AssetAdministrationShellEnvironment map(AmlParser parser, MappingContext context) throws MappingException {
        // TODO use typeFactory instead of explicitly using Default... classes
        AssetAdministrationShellEnvironment result = new DefaultAssetAdministrationShellEnvironment.Builder().build();
        List<InternalElementType> shells = parser.getContent().getInstanceHierarchy().stream()
                .flatMap(x -> x.getInternalElement().stream().filter(filterByRole(AssetAdministrationShell.class)))
                .collect(Collectors.toList());
        shells.forEach(x -> {
            parser.setCurrent(x);
            try {
                AssetAdministrationShell aas = context.with(result).map(AssetAdministrationShell.class, parser);
                result.getAssetAdministrationShells().add(aas);
            } catch (MappingException ex) {
                Logger.getLogger(AssetAdministrationShellEnvironmentMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        List<InternalElementType> conceptDescriptions = parser.getContent().getInstanceHierarchy().stream()
                .flatMap(x -> x.getInternalElement().stream().filter(filterByRole(ConceptDescription.class)))
                .collect(Collectors.toList());
        conceptDescriptions.forEach(x-> {
            parser.setCurrent(x);
            try {
                ConceptDescription conceptDescription = context.with(result).map(ConceptDescription.class, parser);
                result.getConceptDescriptions().add(conceptDescription);
            } catch (MappingException ex){
                Logger.getLogger(AssetAdministrationShellEnvironmentMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // TODO parse SystemUnitClasses
        // add template AAS and Submodels (only if not already present)
        return result;
    }

    private Predicate<InternalElementType> filterByRole(Class<?> type) {
        return x -> x.getRoleRequirements() != null
                && x.getRoleRequirements().getRefBaseRoleClassPath().equals(
                        AmlDocumentInfo.DEFAULT_ASSET_ADMINISTRATION_SHELL_ROLE_CLASS_LIB + "/" + type.getSimpleName());
    }

}
