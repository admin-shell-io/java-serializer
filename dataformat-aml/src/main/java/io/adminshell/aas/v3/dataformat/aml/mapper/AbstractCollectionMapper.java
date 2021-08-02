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
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

public class AbstractCollectionMapper<T> extends BaseMapper<Collection<T>> {

    @Override
    protected Class<T> getValueType(Collection<T> collection, MappingContext context) {
        if (context.getProperty() != null) {
            return (Class<T>) ((ParameterizedType) context.getProperty().getReadMethod().getGenericReturnType()).getActualTypeArguments()[0];
        }
        if (collection != null && !collection.isEmpty()) {
            return (Class<T>) ReflectionHelper.getAasInterface(collection.iterator().next().getClass());
        }
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    protected void toInternalElement(Collection<T> collection, AmlGenerator generator, MappingContext context) throws MappingException {
        for (T element : collection) {
            context.withoutProperty().map(element, generator);
        }
    }

    @Override
    protected void toAttribute(Collection<T> collection, AmlGenerator generator, MappingContext context) throws MappingException {
        Class<?> aasType = context.getProperty().getReadMethod().getDeclaringClass();
        AttributeType.Builder builder = AttributeType.builder()
                .withName(context.getMappingProvider().getAttributeNamingStrategy().getName(
                        context.getProperty().getReadMethod().getReturnType(),
                        collection,
                        context.getProperty().getName()))
                .withRefSemantic(refSemantic(
                        aasType.getSimpleName(),
                        context.getProperty().getName()));
        for (T element : collection) {
            context.map(element, generator.with(builder));
        }
        generator.addAttribute(builder.build());
    }
}
