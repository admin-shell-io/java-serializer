package de.fraunhofer.iais.eis.ids.jsonld;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Stream;


public class JsonLDSerializer extends BeanSerializer {

	Logger logger = LoggerFactory.getLogger(JsonLDSerializer.class);
	
    private static int currentRecursionDepth = 0;

    static final Map<String, String> contextItems;

    static {
        contextItems = new HashMap<>();
        contextItems.put("ids", "https://w3id.org/idsa/core/");
        contextItems.put("idsc", "https://w3id.org/idsa/code/");
        contextItems.put("info", "http://www.fraunhofer.de/fraunhofer-digital/infomodell/");
        contextItems.put("kdsf", "http://kerndatensatz-forschung.de/version1/technisches_datenmodell/owl/Basis#");
        //TODO: We should probably add some other common namespaces, such as foaf or xsd
    }


    JsonLDSerializer(BeanSerializerBase src) {
        super(src);
    }

    @Override
    public void serializeWithType(Object bean, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        gen.setCurrentValue(bean);

        currentRecursionDepth++;
        gen.writeStartObject();

        if (currentRecursionDepth == 1) {
            Map<String, String> filteredContext = new HashMap<>();
            filterContextWrtBean(bean, filteredContext);
            addJwtFieldsToContext(bean, filteredContext);
            gen.writeObjectField("@context", filteredContext);
            //gen.writeStringField("@context", "https://jira.iais.fraunhofer.de/stash/projects/ICTSL/repos/ids-infomodel-commons/raw/jsonld-context/3.0.0/context.jsonld"); // only add @context on top level

        }
        WritableTypeId typeIdDef = _typeIdDef(typeSer, bean, JsonToken.START_OBJECT);
        String resolvedTypeId = typeIdDef.id != null ? typeIdDef.id.toString() : typeSer.getTypeIdResolver().idFromValue(bean);
        if (resolvedTypeId != null) {
            gen.writeStringField(typeIdDef.asProperty, resolvedTypeId);
        }
        if (_propertyFilterId != null) {
            serializeFieldsFiltered(bean, gen, provider);
        } else {
            serializeFields(bean, gen, provider);
        }
        gen.writeEndObject();
        currentRecursionDepth--;
    }

    /**
     * We need to add the fields of DatPayload to the context manually (if DatPayload present)
     * as RFC 7519 requires the exact field names specified below without any prefix for JWTs.
     * @param bean The object to be serialized
     * @param context The context map (with key: prefix, value: URI) to be filled
     */
    private void addJwtFieldsToContext(Object bean, Map<String, String> context) {
        if(bean == null || bean.getClass().getName().equals("com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl") || bean.getClass().getName().equals("org.apache.jena.ext.xerces.jaxp.datatype.XMLGregorianCalendarImpl") || bean.getClass() == BigInteger.class) return;
        if(bean.getClass().getSimpleName().contains("DatPayload")) {
            Stream.of("referringConnector", "aud", "iss", "sub", "nbf", "exp", "iat")
                    .forEach(k -> context.put(k, "ids:".concat(k)));
        } else {
            Stream.of(bean.getClass().getDeclaredFields()).forEach(f -> {
            	
                if(f.isSynthetic() || f.getType().isPrimitive() || f.getType().isEnum()
                		|| f.getType().toString().contains("java.") 
                		|| f.getType().toString().contains("javax.")) return;
                
                boolean wasAccessible = f.canAccess(bean);
                f.setAccessible(true);
                try {
                    addJwtFieldsToContext(f.get(bean), context);
                } catch (IllegalAccessException e) {
                    logger.error("setting accessible failed"); //TODO can we really simply catch it here?
                }
                f.setAccessible(wasAccessible);
            });
        }
    }

    private void filterContextWrtBean(Object bean, Map<String, String> filteredContext) {
        if(bean == null || bean.getClass().getName().equals("com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl") || bean.getClass().getName().equals("org.apache.jena.ext.xerces.jaxp.datatype.XMLGregorianCalendarImpl") || bean.getClass() == BigInteger.class) return; // XMLGregorianCalendarImpl causes infinite recursion
        contextItems.forEach((p, u) -> {
            JsonTypeName typeNameAnnotation = bean.getClass().getAnnotation(JsonTypeName.class);
            if(typeNameAnnotation != null && typeNameAnnotation.value().contains(p)) {
                filteredContext.put(p, u);
            }
            //TODO: Dirty hard coded stuff...
            if(typeNameAnnotation != null && typeNameAnnotation.value().toLowerCase().contains("catalog"))
            {
                filteredContext.put("idsc", "https://w3id.org/idsa/code/");
            }
            Stream.of(bean.getClass().getMethods()).forEach(m -> {
                JsonProperty propertyAnnotation = m.getAnnotation(JsonProperty.class);
                if(propertyAnnotation != null && propertyAnnotation.value().contains(p)) {
                    filteredContext.put(p, u);
                }
            });
        });
        Stream.of(bean.getClass().getMethods()).forEach(m -> {
            // run though all properties and check annotations. These annotations should contain the prefixes
            JsonProperty prop = m.getAnnotation(JsonProperty.class);
            if(prop != null)
            {
                for(Map.Entry<String, String> entry : contextItems.entrySet())
                {
                    if(prop.value().startsWith(entry.getKey()))
                    {
                        filteredContext.put(entry.getKey(), entry.getValue());
                        break;
                    }
                }
            }
            if(m.getReturnType().isEnum()) {
                //Is there any enum constant starting with the IDSC namespace?
                if (Arrays.stream(m.getReturnType().getEnumConstants()).anyMatch(constant -> constant.toString().startsWith(contextItems.get("idsc")))) {
                    filteredContext.put("idsc", contextItems.get("idsc"));
                }
            }
        });
        // run through fields recursively
        for(Field f : getAllFields(new HashSet<>(), bean.getClass())) {

            if(Collection.class.isAssignableFrom(f.getType()))
            {
                try {
                    if(f.getType().getName().startsWith("java.") && !f.getType().getName().startsWith("java.util")) continue;
                    boolean accessible = f.canAccess(bean);
                    f.setAccessible(true);
                    Collection<?> c = (Collection<?>) f.get(bean);
                    if(c == null) {
                        continue;
                    }
                    for(Object o : c)
                    {
                        filterContextWrtBean(o, filteredContext);
                    }
                    f.setAccessible(accessible);
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (f.getType().isPrimitive() || f.getType().isEnum() || f.getType().isArray()
                    || f.getType().getName().contains("java.")
                    || f.getType().getName().contains("javax.")) continue;

            try {
                boolean wasAccessible = f.canAccess(bean);
                f.setAccessible(true);
                filterContextWrtBean(f.get(bean), filteredContext);
                f.setAccessible(wasAccessible);
            } catch (IllegalAccessException ignored) {
                //logger.error("setting accessible failed"); //We can catch that here, as IllegalReflectiveAccess cannot occur on our own packages
            }

            //f.trySetAccessible(wasAccessible);

        }

    }

    /**
     * This function retrieves a set of all available fields of a class, including inherited fields
     * @param fields Set to which discovered fields will be added. An empty HashSet should do the trick
     * @param type The class for which fields should be discovered
     * @return set of all available fields
     */
    private static Set<Field> getAllFields(Set<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }
}
