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
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import io.adminshell.aas.v3.model.Referable;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseMapper<T> implements Mapper<T> {

    protected Optional<Mapper> findMapper(Class<?> type) {
        return Optional.empty();
    }

    protected Class<?> getValueType(T value, MappingContext context) {
        return value.getClass();
    }

    @Override
    public void map(T value, MappingContext context) throws MappingException {
        if (value == null || context == null) {
            return;
        }
        Class<?> aasType = getValueType(value, context);
        Class<?> aasTypeInfo = ReflectionHelper.getMostSpecificTypeWithModelType(aasType);
        if (aasTypeInfo != null) {
            toInternalElement(value, context);
        } else {
            toAttribute(value, context);
        }
    }

    protected void toInternalElement(T value, MappingContext context) throws MappingException {
        InternalElementType.Builder builder = InternalElementType.builder();
        builder = builder.withID(context.getIdentityProvider().getCachedId(value))
                .withName(context.getMappingProvider().getInternalElementNamingStrategy().getName(
                        value.getClass(),
                        value,
                        null))
                .withRoleRequirements(roleRequirement(ReflectionHelper.getModelType(value.getClass())));
        mapProperties(value, context.with(builder));
        context.with(builder).appendReferenceTargetInterfaceIfRequired(value);
        context.addInternalElement(builder.build());
    }

    protected void toAttribute(T value, MappingContext context) throws MappingException {
        Class<?> aasType = ReflectionHelper.getAasInterface(value.getClass());
        AttributeType.Builder builder = AttributeType.builder();
        if (context.getProperty() != null) {
            builder = builder
                    .withName(context.getMappingProvider().getAttributeNamingStrategy().getName(
                            context.getProperty().getReadMethod().getGenericReturnType(),
                            value,
                            context.getProperty().getName()))
                    .withRefSemantic(refSemantic(
                            context.getProperty().getReadMethod().getDeclaringClass().getSimpleName(),
                            context.getProperty().getName()));
        }
        if (aasType != null) {
            mapProperties(value, context.with(builder));
        } else {
            builder = builder.withValue(value);
        }
        context.addAttribute(builder.build());
    }

    protected void mapProperties(T value, MappingContext context, String... ignoreProperties) throws MappingException {
        List<String> ignored = Arrays.asList(ignoreProperties);
        Class<?> aasType = ReflectionHelper.getAasInterface(value.getClass());
        Set<Class<?>> types = new HashSet<>();
        if (aasType != null) {
            types.add(aasType);
            types.addAll(ReflectionHelper.getSuperTypes(aasType, true));
        }
        for (Class<?> type : types) {
            try {
                for (PropertyDescriptor property : Introspector.getBeanInfo(type).getPropertyDescriptors()) {
                    if (!ignored.contains(property.getName())) {
                        context.with(property).map(property.getReadMethod().getGenericReturnType(),
                                getPropertyValue(value, property, context));
                    }
                }
            } catch (IntrospectionException ex) {
                throw new MappingException("error listing properties of class " + type, ex);
            }
        }
    }

    protected Object getPropertyValue(T value, PropertyDescriptor property, MappingContext context) throws MappingException {
        try {
            return property.getReadMethod().invoke(value);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new MappingException("failed to get property value for property " + property.getName(), ex);
        }
    }

    protected InternalElementType.RoleRequirements roleRequirement(String value) {
        return InternalElementType.RoleRequirements.builder()
                .withRefBaseRoleClassPath("AssetAdministrationShellRoleClassLib/" + value)
                .build();
    }

    protected AttributeType.RefSemantic refSemantic(String className, String propertyName) {
        return AttributeType.RefSemantic.builder()
                .withCorrespondingAttributePath("AAS:" + className + "/" + propertyName)
                .build();
    }

}
