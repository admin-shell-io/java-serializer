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
package io.adminshell.aas.v3.dataformat.aml.aas2aml.custom;

import io.adminshell.aas.v3.dataformat.aml.aas2aml.AmlGenerator;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.DefaultMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.MappingContext;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.dataformat.core.util.AasUtils;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.Submodel;
import java.beans.PropertyDescriptor;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssetAdministrationShellMapper extends DefaultMapper<AssetAdministrationShell> {

    private static final Logger log = LoggerFactory.getLogger(AssetAdministrationShellMapper.class);

    @Override
    protected boolean skipProperty(PropertyDescriptor property) {
        return "submodels".equals(property.getName());
    }

    @Override
    public void map(AssetAdministrationShell aas, AmlGenerator generator, MappingContext context) throws MappingException {
        if (aas == null) {
            return;
        }
        InternalElementType.Builder builder = toInternalElement(aas, generator, context);
        for (Reference reference : aas.getSubmodels()) {
            Optional<Submodel> resolvedSubmodel = AasUtils.resolveSubmodelReference(reference, context.getEnvironment());
            if (resolvedSubmodel.isPresent()) {
                context.map(resolvedSubmodel.get(), generator.with(builder));
            } else {
                log.warn("unresolvable submodel reference '{}' found in AssetAdministrationShell '{}'",
                        AasUtils.asString(reference),
                        aas.getIdentification().getIdentifier());
                context.map(AasUtils.asString(reference), generator);
            }
        }
        generator.with(builder).appendReferenceTargetInterfaceIfRequired(aas, context);
        generator.add(builder.build());
    }
}
