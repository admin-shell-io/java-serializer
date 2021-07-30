package io.adminshell.aas.v3.dataformat.jsonld;

import com.fasterxml.jackson.databind.module.SimpleModule;

import io.adminshell.aas.v3.dataformat.jsonld.custom.BigDecimalSerializer;
import io.adminshell.aas.v3.dataformat.jsonld.custom.XMLGregorianCalendarDeserializer;
import io.adminshell.aas.v3.dataformat.jsonld.custom.XMLGregorianCalendarSerializer;
import io.adminshell.aas.v3.model.LangString;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Map;


import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Jackson module which provides support for JSON-LD serialization
 */
public class JsonLDModule extends SimpleModule {


    public JsonLDModule(Map<Object, String> idMap) {
        super();

        setSerializerModifier(new JsonLDSerializerModifier(idMap));
        
        addSerializer(XMLGregorianCalendar.class, new XMLGregorianCalendarSerializer());
        addDeserializer(XMLGregorianCalendar.class, new XMLGregorianCalendarDeserializer());
        addSerializer(BigDecimal.class, new BigDecimalSerializer());
        
        addSerializer(URI.class, new UriSerializer());
        addSerializer(LangString.class, new LangStringSerializer());
    }

}
