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
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util.ReferenceConverterUtil;
import io.adminshell.aas.v3.dataformat.aml.util.AASUtils;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.Submodel;
import java.util.Optional;

public class ReferenceMapper extends ToAttributeMapper<Reference> {

    @Override
    public void map(Reference reference, MappingContext context) throws MappingException {
        if (reference != null && !reference.getKeys().isEmpty()) {
            KeyElements referencedType = reference.getKeys().get(reference.getKeys().size() - 1).getType();
            if (referencedType == KeyElements.SUBMODEL) {
                Optional<Submodel> resolvedSubmodel = AASUtils.resolveSubmodelReference(reference, context.getEnvironment());
                if (resolvedSubmodel.isPresent()) {
                    context.withoutIdentidyProvider().map(resolvedSubmodel.get());
                    return;
                }
            }
        }
        mapAsAttribute(ReferenceConverterUtil.convert(reference), context);
    }
}
