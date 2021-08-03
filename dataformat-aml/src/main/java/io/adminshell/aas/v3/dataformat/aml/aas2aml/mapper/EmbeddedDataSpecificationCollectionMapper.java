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
import io.adminshell.aas.v3.dataformat.aml.util.AASUtils;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.DataSpecificationContent;
import io.adminshell.aas.v3.model.EmbeddedDataSpecification;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmbeddedDataSpecificationCollectionMapper extends DefaultMapper<Collection<EmbeddedDataSpecification>> {

    @Override
    public void map(Collection<EmbeddedDataSpecification> value, AmlGenerator generator, Aas2AmlMappingContext context) throws MappingException {
        if (value == null || context == null || value.isEmpty()) {
            return;
        }
        long countAttributes = value.stream().filter(x -> x.getDataSpecificationContent() == null).count();
        long countInternalElement = value.size() - countAttributes;
        List<AttributeType> attributes = new ArrayList<>();
        List<InternalElementType> internalElements = new ArrayList<>();
        for (EmbeddedDataSpecification element : value) {
            if (element.getDataSpecificationContent() == null) {
                AttributeType.Builder builder = AttributeType.builder()
                        .withName("hasDataSpecification" + (countAttributes > 1 ? "_" + (attributes.size() + 1) : ""))
                        .withValue(AASUtils.asString(element.getDataSpecification()))
                        .withRefSemantic(generator.refSemantic(ConceptDescription.class, "dataSpecification"));
                attributes.add(builder.build());
            } else {
                // need to map all properties but with specific attribute path
                // idea: get current attribute path via context?
                // for now do not care about correct attribute path
                InternalElementType.Builder temp = InternalElementType.builder();
                AmlGenerator subGenerator = generator.with(temp);
                context.map(element.getDataSpecificationContent(), subGenerator);
                InternalElementType.Builder builder = InternalElementType.copyOf(temp.build().getInternalElement().get(0))
                        .withName("EmbeddedDataSpecification" + (countInternalElement > 1 ? "_" + (internalElements.size() + 1) : ""))
                        .withID(context.generateId())
                        .withRefBaseSystemUnitPath(generator.getDocumentInfo().getDataSpecificationTemplatesSystemUnitClassLib()
                                + "/" + getDataSpecificationContentType(element.getDataSpecificationContent().getClass()) + "Template/"
                                + getDataSpecificationContentType(element.getDataSpecificationContent().getClass()));
                internalElements.add(builder.build());
            }
        }
        attributes.forEach(x -> generator.addAttribute(x));
        internalElements.forEach(x -> generator.addInternalElement(x));
    }

    private String getDataSpecificationContentType(Class<?> type) {
        // TODO should be resolved using DataSpecificationManager but this requires fundamental changes to 
        // DataSpecificationManager as it currently is based on Reference instead of name
        // workaround: go up superclasses/interfaces and find most-specific interface that extends DataSpecificationContent
        if (type == null) {
            return null;
        }
        Class[] interfaces = type.getInterfaces();
        for (Class temp : interfaces) {
            if (DataSpecificationContent.class.isAssignableFrom(temp)) {
                return temp.getSimpleName();
            }
        }
        return getDataSpecificationContentType(type.getSuperclass());
    }
}
