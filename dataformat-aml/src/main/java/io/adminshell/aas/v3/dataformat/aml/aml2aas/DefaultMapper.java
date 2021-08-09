package io.adminshell.aas.v3.dataformat.aml.aml2aas;

import com.google.common.reflect.TypeToken;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AttributeType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import io.adminshell.aas.v3.dataformat.mapping.util.TypeUtils;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DefaultMapper implements Aml2AasMapper<Object> {

    @Override
    public Object map(AmlParser parser, Aml2AasMappingContext context) throws MappingException {
        if (parser.getCurrent() == null) {
            return null;
        }
        if (InternalElementType.class.isAssignableFrom(parser.getCurrent().getClass())) {
            handleInternalElement(parser, context);
        } else if (AttributeType.class.isAssignableFrom(parser.getCurrent().getClass())) {
            handleAttribute(parser, context);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected Object handleInternalElement(AmlParser parser, Aml2AasMappingContext context) {
        Object result = null;
        InternalElementType current = (InternalElementType) parser.getCurrent();
        String role = current.getRoleRequirements().getRefBaseRoleClassPath();
        if (role.startsWith(context.getDocumentInfo().getAssetAdministrationShellRoleClassLib())) {
            String aasClassName = role.substring(context.getDocumentInfo().getAssetAdministrationShellRoleClassLib().length());
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
                    List<PropertyDescriptor> properties = TypeUtils.getAASProperties(aasClass);
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
                        String aasType = ReflectionHelper.getModelType(TypeToken.of(x.getReadMethod().getGenericReturnType()).resolveType(Collection.class).getRawType());
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

    protected Object handleAttribute(AmlParser parser, Aml2AasMappingContext context) throws MappingException {
        AttributeType current = (AttributeType) parser.getCurrent();
        if (context.getType() == null) {
            throw new MappingException(String.format("error processing Attribute '%s', missing type information in context", current.getName()));
        }
        Object result = null;
//        result = context.getTypeFactory().newInstance(context.getType());
        // distinguish between AASType and other
        // AASType needs reflection
        return result;
    }

//    private void <T> T instanceFromRoleRequirements(Inter)
}
