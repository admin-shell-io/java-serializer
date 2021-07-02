package de.fraunhofer.iais.eis.ids.jsonld.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class XMLGregorianCalendarSerializer extends StdSerializer<XMLGregorianCalendar> {

    public XMLGregorianCalendarSerializer() {
        this(null);
    }

    public XMLGregorianCalendarSerializer(Class clazz) {
        super(clazz);
    }

    @Override
    public void serialize(XMLGregorianCalendar value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        SimpleDateFormat xsdDateTimeStampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        xsdDateTimeStampFormat.setCalendar(value.toGregorianCalendar());
        String xsdDateTimeStampFormatted = xsdDateTimeStampFormat.format(value.toGregorianCalendar().getTime());
        //String serializedCalendar = value.toGregorianCalendar().toZonedDateTime().toString();
        //serializedCalendar = serializedCalendar.substring(0, serializedCalendar.indexOf("[")); // remove [GMT+...] appendix
        gen.writeStartObject();
        gen.writeStringField("@value", xsdDateTimeStampFormatted);
        gen.writeStringField("@type", "http://www.w3.org/2001/XMLSchema#dateTimeStamp");
        gen.writeEndObject();

    }
}
