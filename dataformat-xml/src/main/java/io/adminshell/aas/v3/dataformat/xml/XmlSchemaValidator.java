package io.adminshell.aas.v3.dataformat.xml;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import io.adminshell.aas.v3.dataformat.SchemaValidator;

public class XmlSchemaValidator implements SchemaValidator {
    private static final String SCHEMA = "/AAS.xsd";
    protected Schema schema;

    public XmlSchemaValidator() throws SAXException {
        loadSchemaFromResource();
    }

    private void loadSchemaFromResource() throws SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        schema = factory.newSchema(getClass().getResource(SCHEMA));
    }

    @Override
    public Set<String> validateSchema(String serializedAASEnvironment) {
        Set<String> errorMessages = new HashSet<>();
        try {
            schema.newValidator().validate(new StreamSource(new java.io.StringReader(serializedAASEnvironment)));
        } catch (SAXException | IOException se) {
            errorMessages.add(se.getMessage());
            return errorMessages;
        }
        return errorMessages;
    }
}
