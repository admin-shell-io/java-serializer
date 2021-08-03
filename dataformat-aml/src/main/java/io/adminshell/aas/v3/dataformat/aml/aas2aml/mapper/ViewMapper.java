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

import io.adminshell.aas.v3.dataformat.aml.AmlGenerator;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.Aas2AmlMappingContext;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import io.adminshell.aas.v3.model.View;

public class ViewMapper extends DefaultMapper<View> {

    @Override
    public void map(View view, AmlGenerator generator, Aas2AmlMappingContext context) throws MappingException {
        if (view == null || view.getContainedElements() == null || view.getContainedElements().isEmpty()) {
            return;
        }
        InternalElementType.Builder builder = InternalElementType.builder();
        builder = builder.withID(context.getCachedId(view))
                .withName(context.getInternalElementNamingStrategy().getName(
                        view.getClass(),
                        view,
                        null))
                //                .withRefBaseSystemUnitPath(context.getIdentityProvider().getId(
                //                        AASUtils.resolve(
                //                                view.getContainedElements().get(0), 
                //                                context.getEnvironment())))
                .withRoleRequirements(generator.roleRequirement(ReflectionHelper.getModelType(view.getClass())));
        generator.with(builder).appendReferenceTargetInterfaceIfRequired(view, context);
        generator.addInternalElement(builder.build());
    }
}
