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

import io.adminshell.aas.v3.dataformat.aml.MappingContext;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AttributeType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleClassType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.SystemUnitClassType.InternalLink;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util.ReferenceConverterUtil;
import io.adminshell.aas.v3.dataformat.aml.util.AASUtils;
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import io.adminshell.aas.v3.model.Referable;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.RelationshipElement;

public class RelationshipElementMapper extends BaseMapper<RelationshipElement> {

    public RelationshipElementMapper() {
    }

    @Override
    public void map(RelationshipElement element, MappingContext context) throws MappingException {
        if (element == null) {
            return;
        }
        // regular mapping        
        InternalElementType.Builder builder = InternalElementType.builder()
                .withID(context.getIdentityProvider().getCachedId(element))
                .withName(context.getMappingProvider().getInternalElementNamingStrategy().getName(
                        element.getClass(),
                        element,
                        null))
                .withRoleRequirements(roleRequirement(ReflectionHelper.getModelType(element.getClass())));
        mapProperties(element, context.with(builder), "first", "second");
        // add special mapping for first, second
        mapProperty(element, element.getFirst(), "first", context.with(builder));
        mapProperty(element, element.getSecond(), "second", context.with(builder));
        context.with(builder).appendReferenceTargetInterfaceIfRequired(element);
        context.addInternalElement(builder.build());
    }

    private void mapProperty(RelationshipElement element, Reference reference, String name, MappingContext context) {
        RoleClassType.ExternalInterface.Builder builder = RoleClassType.ExternalInterface.builder()
                .withName(name)
                .withRefBaseClassPath("AssetAdministrationShellInterfaceClassLib/ReferableReference");
        Referable resolvedReference = AASUtils.resolve(reference, context.getEnvironment());
        if (resolvedReference != null) {
            // TODO internalLink must be added on appropriate level which contains both elements
            // check if it can also be added on higher levels as this would make things easier
            // !!! neither CAEXFile nor InstanceHierarchy allow definition of internalLinks which 
            // !!! contradicts CAEX meta model as depicted on slide 13 https://www.plt.rwth-aachen.de/global/show_document.asp?id=aaaaaaaaaatmalk
            context.getInternalElementBuilder()
                    .addInternalLink(InternalLink.builder()
                            .withName(name)
                            .withID(context.getIdentityProvider().generateId())
                            .withRefPartnerSideA(context.getIdentityProvider().getCachedId(element) + ":name")
                            .withRefPartnerSideB(context.getInternalLinkTargetId(resolvedReference) + ":externalReferenceTarget")
                            .build());
        } else {
            builder = builder.addAttribute(AttributeType.builder()
                    .withName("value")
                    .withAttributeDataType("xs:string")
                    .withValue(ReferenceConverterUtil.convert(reference))
                    .build());
        }
        context.getInternalElementBuilder().addExternalInterface(builder.build());
    }
}
