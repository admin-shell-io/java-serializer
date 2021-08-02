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
package io.adminshell.aas.v3.dataformat.aml.mapper;

import io.adminshell.aas.v3.dataformat.aml.AmlGenerator;
import io.adminshell.aas.v3.dataformat.aml.MappingContext;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.model.DataSpecificationContent;
import io.adminshell.aas.v3.model.DataSpecificationIEC61360;
import java.beans.PropertyDescriptor;

public class DataSpecificationIEC61360Mapper extends BaseMapper<DataSpecificationIEC61360> {

    @Override
    public void map(DataSpecificationIEC61360 content, AmlGenerator generator, MappingContext context) throws MappingException {
        toInternalElement(content, generator, context);
    }

    @Override
    protected void toInternalElement(DataSpecificationIEC61360 value, AmlGenerator generator, MappingContext context) throws MappingException {
        InternalElementType.Builder builder = InternalElementType.builder();
        builder = builder.withID(context.generateId())
                .withName(context.getMappingProvider().getInternalElementNamingStrategy().getName(
                        value.getClass(),
                        value,
                        null))
                .withRoleRequirements(roleRequirement(DataSpecificationContent.class.getSimpleName()));
        mapProperties(value, generator.with(builder), context);
        generator.with(builder).appendReferenceTargetInterfaceIfRequired(value, context);
        generator.addInternalElement(builder.build());
    }

    @Override
    protected Object getPropertyValue(DataSpecificationIEC61360 value, PropertyDescriptor property, MappingContext context) throws MappingException {
        if (property.getName().equals("levelTypes") || property.getName().equals("valueList")) {
            return null;
        }
        return super.getPropertyValue(value, property, context);
    }
}
