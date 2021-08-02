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
import io.adminshell.aas.v3.dataformat.aml.model.caex.AttributeType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleClassType;
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import io.adminshell.aas.v3.model.File;

public class FileMapper extends BaseMapper<File> {

    public FileMapper() {
    }

    @Override
    public void map(File file, AmlGenerator generator, MappingContext context) throws MappingException {
        if (file == null) {
            return;
        }
        InternalElementType.Builder builder = InternalElementType.builder();
        builder = builder.withID(context.getCachedId(file))
                .withName(context.getMappingProvider().getInternalElementNamingStrategy().getName(
                        file.getClass(),
                        file,
                        null))
                .withRoleRequirements(roleRequirement(ReflectionHelper.getModelType(file.getClass())))
                .withExternalInterface(RoleClassType.ExternalInterface.builder()
                        .withName("FileDataReference")
                        .withID(context.generateId())
                        .withRefBaseClassPath("AssetAdministrationShellInterfaceClassLib/FileDataReference")
                        .addAttribute(AttributeType.builder()
                                .withName("MIMEType")
                                .withAttributeDataType("xs:string")
                                .withValue(file.getMimeType())
                                .build())
                        .addAttribute(AttributeType.builder()
                                .withName("refURI")
                                .withValue(file.getValue())
                                .withAttributeDataType("xs:anyURI")
                                .build())
                        .build());
        mapProperties(file, generator.with(builder), context);
        generator.with(builder).appendReferenceTargetInterfaceIfRequired(file, context);
        generator.addInternalElement(builder.build());
    }
}
