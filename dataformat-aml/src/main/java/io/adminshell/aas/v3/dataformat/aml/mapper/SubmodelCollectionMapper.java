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
import io.adminshell.aas.v3.model.ModelingKind;
import io.adminshell.aas.v3.model.Submodel;
import java.util.Collection;
import java.util.stream.Collectors;

public class SubmodelCollectionMapper extends BaseMapper<Collection<Submodel>> {

    @Override
    public void map(Collection<Submodel> value, MappingContext context) throws MappingException {
        if (value == null || context == null) {
            return;
        }
        super.map(value, context);
//        super.map(value.stream()
//                .filter(x -> x.getKind() == ModelingKind.INSTANCE)
//                .collect(Collectors.toList()),
//                context);
    }
}
