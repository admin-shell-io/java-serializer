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
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXObject;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import io.adminshell.aas.v3.dataformat.mapping.util.TypeUtils;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public class DefaultMapper<T> implements Aas2AmlElementMapper<T> {

    protected Class<?> getType(T value, Aas2AmlMappingContext context) {
        return value.getClass();
    }

    @Override
    public void map(T value, AmlGenerator generator, Aas2AmlMappingContext context) throws MappingException {
        if (value == null || context == null) {
            return;
        }
        Class<?> aasType = getType(value, context);
        Class<?> aasTypeInfo = ReflectionHelper.getMostSpecificTypeWithModelType(aasType);
        if (aasTypeInfo != null) {
            asInternalElement(value, generator, context);
        } else {
            asAttribute(value, generator, context);
        }
    }

    protected void asInternalElement(T value, AmlGenerator generator, Aas2AmlMappingContext context) throws MappingException {
        CAEXObject.Builder builder = toInternalElement(value, generator, context);
        if (builder != null) {
            generator.with(builder).appendReferenceTargetInterfaceIfRequired(value, context);
            generator.add(builder.build());
        }
    }

    protected void asAttribute(T value, AmlGenerator generator, Aas2AmlMappingContext context) throws MappingException {
        AttributeType.Builder builder = toAttribute(value, generator, context);
        if (builder != null) {
            generator.add(builder.build());
        }
    }

    protected InternalElementType.Builder toInternalElement(T value, AmlGenerator generator, Aas2AmlMappingContext context) throws MappingException {
        InternalElementType.Builder builder = InternalElementType.builder();
        builder = builder
                .withID(getId(value, context))
                .withName(getInternalElementName(value, context))
                .withRoleRequirements(getRoleRequirementClass(value, generator, context));
        mapProperties(value, generator.with(builder), context);
        return builder;
    }

    protected InternalElementType.RoleRequirements getRoleRequirementClass(T value, AmlGenerator generator, Aas2AmlMappingContext context) {
        return generator.roleRequirement(ReflectionHelper.getModelType(value.getClass()));
    }

    protected AttributeType.RefSemantic getRefSemantic(T value, AmlGenerator generator, Aas2AmlMappingContext context) {
        return generator.refSemantic(context.getProperty());
    }

    protected String getInternalElementName(T value, Aas2AmlMappingContext context) {
        return context.getInternalElementNamingStrategy().getName(
                value.getClass(),
                value,
                null);
    }

    protected String getId(T value, Aas2AmlMappingContext context) {
        return context.getCachedId(value);
    }

    protected String getAttributeName(T value, Aas2AmlMappingContext context) {
        return context.getAttributeNamingStrategy().getName(
                context.getProperty().getReadMethod().getGenericReturnType(),
                value,
                context.getProperty().getName());
    }

    protected AttributeType.Builder toAttribute(T value, AmlGenerator generator, Aas2AmlMappingContext context) throws MappingException {
        Class<?> aasType = ReflectionHelper.getAasInterface(value.getClass());
        AttributeType.Builder builder = AttributeType.builder();
        if (context.getProperty() != null) {
            builder = builder
                    .withName(getAttributeName(value, context))
                    .withRefSemantic(getRefSemantic(value, generator, context));
        }
        if (aasType != null) {
            mapProperties(value, generator.with(builder), context);
        } else {
            builder = builder.withValue(value);
        }
        return builder;
    }

    protected boolean skipProperty(PropertyDescriptor property) {
        return false;
    }

    protected void mapProperties(T value, AmlGenerator generator, Aas2AmlMappingContext context) throws MappingException {
        for (PropertyDescriptor property : TypeUtils.getAASProperties(value.getClass())) {
            if (!skipProperty(property)) {
                context.with(property)
                        .map(property.getReadMethod().getGenericReturnType(),
                                getPropertyValue(value, property, context),
                                generator);
            }
        }
    }

    protected Object getPropertyValue(T value, PropertyDescriptor property, Aas2AmlMappingContext context) throws MappingException {
        try {
            return property.getReadMethod().invoke(value);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new MappingException("failed to get property value for property " + property.getName(), ex);
        }
    }

}
