package de.fraunhofer.iais.eis.ids.jsonld.custom;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

public class XMLGregorianCalendarDeserializer extends StdDeserializer<XMLGregorianCalendar> {

    public XMLGregorianCalendarDeserializer() {
        this(XMLGregorianCalendar.class);
    }

    public XMLGregorianCalendarDeserializer(Class clazz) {
        super(clazz);
    }

    @Override
    public XMLGregorianCalendar deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        XMLGregorianCalendar xgc = null;
        try {
            xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar.from(ZonedDateTime.parse(p.getValueAsString())));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return xgc;
    }
}
