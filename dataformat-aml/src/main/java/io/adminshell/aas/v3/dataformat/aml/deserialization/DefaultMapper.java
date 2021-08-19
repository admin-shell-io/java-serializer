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
package io.adminshell.aas.v3.dataformat.aml.deserialization;

import com.google.common.reflect.TypeToken;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AttributeType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import io.adminshell.aas.v3.dataformat.core.util.AasUtils;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Default mapper for mapping AML to AAS. This mapper will be used when there no
 * more specific one is available.
 */
public class DefaultMapper implements Mapper<Object> {

    @Override
    public Object map(AmlParser parser, MappingContext context) throws MappingException {
        if (parser.getCurrent() == null) {
            return null;
        }
        if (InternalElementType.class.isAssignableFrom(parser.getCurrent().getClass())) {
            return handleInternalElement(parser, context);
        } else if (AttributeType.class.isAssignableFrom(parser.getCurrent().getClass())) {
            return handleAttribute(parser, context);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called whenever an InternalElement is encountered.
     *
     * @param parser the AML parser
     * @param context the mapping context
     * @return an AAS object representing the current object of the parser
     */
    protected Object handleInternalElement(AmlParser parser, MappingContext context) {
        Object result = null;
        InternalElementType current = (InternalElementType) parser.getCurrent();
        String role = current.getRoleRequirements().getRefBaseRoleClassPath();
        if (role.startsWith(context.getDocumentInfo().getAssetAdministrationShellRoleClassLib())) {
            String aasClassName = role.substring(context.getDocumentInfo().getAssetAdministrationShellRoleClassLib().length() + 1);
            Optional<ReflectionHelper.ImplementationInfo> implementationInfo = ReflectionHelper.DEFAULT_IMPLEMENTATIONS.stream()
                    .filter(x -> x.getInterfaceType().getSimpleName().equals(aasClassName))
                    .findFirst();
            final Class<?> aasClass;
            // check if custom implementation should be used
            if (implementationInfo.isPresent()) {
                aasClass = implementationInfo.get().getImplementationType();
            } else {
                aasClass = null;
            }
            if (aasClass != null) {
                try {
                    result = context.getTypeFactory().newInstance(aasClass);
                    List<PropertyDescriptor> properties = AasUtils.getAasProperties(aasClass);
                    List<PropertyDescriptor> propertiesGivenAsAttribute = new ArrayList<>();
                    for (AttributeType attribute : current.getAttribute()) {
                        Optional<PropertyDescriptor> property = properties.stream()
                                .filter(x -> x.getName().equals(attribute.getName()))
                                .findFirst();
                        if (property.isPresent()) {
                            parser.setCurrent(attribute);
                            Object attributeValue = context.getMappingProvider()
                                    .getMapper(property.get().getReadMethod().getReturnType())
                                    .map(parser, context.with(property.get().getReadMethod().getReturnType()));
                            property.get().getWriteMethod().invoke(result, attributeValue);
                            propertiesGivenAsAttribute.add(property.get());
                        }
                    }

                    Map<PropertyDescriptor, String> singleValueProperties = properties.stream()
                            .filter(x -> !Collection.class.isAssignableFrom(x.getReadMethod().getReturnType()))
                            .map(x -> new Object() {
                        PropertyDescriptor type = x;
                        String aasType = ReflectionHelper.getModelType(x.getReadMethod().getReturnType());
                    })
                            .filter(x -> x.aasType != null)
                            .collect(Collectors.toMap(
                                    x -> x.type,
                                    x -> x.aasType));

                    Map<PropertyDescriptor, String> collectionValueProperties = properties.stream()
                            .filter(x -> Collection.class.isAssignableFrom(x.getReadMethod().getReturnType()))
                            .map(x -> new Object() {
                        PropertyDescriptor type = x;
                        String aasType = ReflectionHelper.getModelType(TypeToken.of(x.getReadMethod().getGenericReturnType()).resolveType(Collection.class.getTypeParameters()[0]).getRawType());
                    })
                            .filter(x -> x.aasType != null)
                            .collect(Collectors.toMap(
                                    x -> x.type,
                                    x -> x.aasType));

                    List<String> propertyTypes = new ArrayList<>();
                    propertyTypes.addAll(singleValueProperties.values());
                    propertyTypes.addAll(collectionValueProperties.values());
                    List<String> duplicateTypes = propertyTypes.stream()
                            .filter(e -> Collections.frequency(propertyTypes, e) > 1)
                            .distinct()
                            .collect(Collectors.toList());
                    if (!duplicateTypes.isEmpty()) {
                        throw new MappingException(duplicateTypes.stream().map(x
                                -> String.format("found multiple properties of type '%s' on type '%s'", x, aasClass))
                                .collect(Collectors.joining(System.lineSeparator()))
                        );
                    }
                    // single values
                    // find matching internal element, deserialize, assign
                    for (Map.Entry<PropertyDescriptor, String> property : singleValueProperties.entrySet()) {
                        String propertyRole = context.getDocumentInfo().getAssetAdministrationShellRoleClassLib() + "/" + property.getValue();
                        List<InternalElementType> valueElements = current.getInternalElement().stream()
                                .filter(y -> y.getRoleRequirements().getRefBaseRoleClassPath().equals(propertyRole))
                                .collect(Collectors.toList());
                        if (!valueElements.isEmpty()) {
                            if (valueElements.size() > 1) {
                                throw new MappingException(String.format(
                                        "found multiple InternalElement with role '%s' but only 1 allowed (ids: %s)",
                                        propertyRole,
                                        valueElements.stream()
                                                .map(x -> x.getID())
                                                .collect(Collectors.joining(", "))));
                            }
                            parser.setCurrent(valueElements.get(0));
                            Object propertyValue = context.getMappingProvider()
                                    .getMapper(property.getKey().getReadMethod().getReturnType())
                                    .map(parser, context);
                            property.getKey().getWriteMethod().invoke(result, propertyValue);
                        }
                    }

                    // collection values
                    // 1. find all internal elements
                    // 2. deserialize
                    // 3. build collection (based on actual type, might be collection, list, set,...)
                    // 4. add to collection
                    // 5. assign to property
                    for (Map.Entry<PropertyDescriptor, String> property : collectionValueProperties.entrySet()) {
                        String propertyRole = context.getDocumentInfo().getAssetAdministrationShellRoleClassLib() + "/" + property.getValue();
                        List<InternalElementType> valueElements = current.getInternalElement().stream()
                                .filter(y -> y.getRoleRequirements().getRefBaseRoleClassPath().equals(propertyRole))
                                .collect(Collectors.toList());
                        if (!valueElements.isEmpty()) {
                            Collection propertyValue = (Collection) property.getKey().getReadMethod().getReturnType().getConstructor().newInstance();
                            for (InternalElementType element : valueElements) {
                                parser.setCurrent(element);
                                Object elementValue = context.getMappingProvider()
                                        .getMapper(property.getKey().getReadMethod().getReturnType())
                                        .map(parser, context);
                                propertyValue.add(elementValue);
                            }
                            property.getKey().getWriteMethod().invoke(result, propertyValue);
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(DefaultMapper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return result;
    }

    /**
     * This method is called whenever an AttributeType is encountered.
     *
     * @param parser the AML parser
     * @param context the mapping context
     * @return an object representing the current object of the parser
     */
    protected Object handleAttribute(AmlParser parser, MappingContext context) throws MappingException {
        AttributeType current = (AttributeType) parser.getCurrent();
        if (context.getType() == null) {
            throw new MappingException(String.format("error processing Attribute '%s', missing type information in context", current.getName()));
        }
        Object result = null;
        Class<?> aasType = ReflectionHelper.getAasInterface(context.getType());
        if (aasType != null) {
            try {
                result = context.getTypeFactory().newInstance(aasType);
                List<PropertyDescriptor> properties = AasUtils.getAasProperties(aasType);
                for (AttributeType attribute : current.getAttribute()) {
                    Optional<PropertyDescriptor> property = properties.stream()
                            .filter(x -> x.getName().equals(attribute.getName()))
                            .findFirst();
                    if (property.isPresent()) {
                        parser.setCurrent(attribute);
                        Object attributeValue = context.getMappingProvider()
                                .getMapper(property.get().getReadMethod().getReturnType())
                                .map(parser, context.with(property.get().getReadMethod().getReturnType()));
                        property.get().getWriteMethod().invoke(result, attributeValue);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(DefaultMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            result = current.getValue();
        }
        return result;
    }

//    private void <T> T instanceFromRoleRequirements(Inter)
}
