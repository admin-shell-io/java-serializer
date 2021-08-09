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
import io.adminshell.aas.v3.dataformat.aml.model.caex.AttributeType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleClassType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.SystemUnitClassType.InternalLink;
import io.adminshell.aas.v3.dataformat.aml.util.AASUtils;
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import io.adminshell.aas.v3.model.Referable;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.RelationshipElement;
import java.beans.PropertyDescriptor;

public class RelationshipElementMapper extends DefaultMapper<RelationshipElement> {

    public RelationshipElementMapper() {
    }

    @Override
    public void map(RelationshipElement element, AmlGenerator generator, Aas2AmlMappingContext context) throws MappingException {
        if (element == null) {
            return;
        }
        // regular mapping        
        InternalElementType.Builder builder = InternalElementType.builder()
                .withID(getId(element, generator, context))
                .withName(context.getInternalElementNamingStrategy().getName(
                        element.getClass(),
                        element,
                        null))
                .withRoleRequirements(generator.roleRequirement(ReflectionHelper.getModelType(element.getClass())));
        AmlGenerator subGenerator = generator.with(builder);
        mapProperties(element, subGenerator, context);
        // add special mapping for first, second
        mapProperty(element, element.getFirst(), "first", subGenerator, context);
        mapProperty(element, element.getSecond(), "second", subGenerator, context);
        generator.with(builder).appendReferenceTargetInterfaceIfRequired(element, context);
        generator.addInternalElement(builder.build());
    }

    private void mapProperty(RelationshipElement element, Reference reference, String name, AmlGenerator generator, Aas2AmlMappingContext context) {
        RoleClassType.ExternalInterface.Builder builder = RoleClassType.ExternalInterface.builder()
                .withName(name)
                .withRefBaseClassPath("AssetAdministrationShellInterfaceClassLib/ReferableReference");
        Referable resolvedReference = AASUtils.resolve(reference, context.getEnvironment());
        if (resolvedReference != null) {
            // TODO internalLink must be added on appropriate level which contains both elements
            // check if it can also be added on higher levels as this would make things easier
            // !!! neither CAEXFile nor InstanceHierarchy allow definition of internalLinks which 
            // !!! contradicts CAEX meta model as depicted on slide 13 https://www.plt.rwth-aachen.de/global/show_document.asp?id=aaaaaaaaaatmalk
            generator.addInternalLink(InternalLink.builder()
                    .withName(name)
                    .withID(context.newId())
                    .withRefPartnerSideA(getId(element, generator, context) + ":name")
                    .withRefPartnerSideB(context.getId(reference) + ":ReferableReference")
                    .build());
        } else {
            builder = builder.addAttribute(AttributeType.builder()
                    .withName("value")
                    .withAttributeDataType("xs:string")
                    .withValue(AASUtils.asString(reference))
                    .build());
        }
        generator.addExternalInterface(builder.build());
    }

    @Override
    protected boolean skipProperty(PropertyDescriptor property) {
        return property.getName().equals("first") || property.getName().equals("second");
    }
}
