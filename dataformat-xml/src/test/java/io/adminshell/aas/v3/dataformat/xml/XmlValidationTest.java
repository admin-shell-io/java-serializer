package io.adminshell.aas.v3.dataformat.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import io.adminshell.aas.v3.dataformat.xml.XmlSchemaValidator;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class XmlValidationTest {
	private static Logger logger = LoggerFactory.getLogger(XmlValidationTest.class);

	private static XmlSchemaValidator validator;

	@BeforeClass
	public static void prepareValidator() throws SAXException {
		validator = new XmlSchemaValidator();
	}

	@Test
	@Parameters({ "src/test/resources/minimum.xml", "src/test/resources/Example_AAS_ServoDCMotor - Simplified V2.0.xml" })
	// import from admin-shell.io -> is actually V3
	// -> fix name, as soon as it is fixed externally
	public void validateValidXml(String file) throws IOException {
		Set<String> errors = validateXmlFile(file);
		logErrors(file, errors);
		assertTrue(errors.isEmpty());
	}

	@Test
	@Parameters({ "src/test/resources/invalidXmlExample.xml", "src/test/resources/ServoDCMotor_invalid_V2.0.xml" })
	public void validateInvalidXml(String file) throws IOException {
		Set<String> errors = validateXmlFile(file);
		logErrors(file, errors);
		assertEquals(1, errors.size());
	}

	private void logErrors(String validatedFileName, Set<String> errors) {
		if (errors.isEmpty())
			return;

		logger.info("Validate file: " + validatedFileName);
		for (String error : errors) {
			logger.info(error);
		}
	}

	private Set<String> validateXmlFile(String file) throws IOException {
		String serializedEnvironment = new String(Files.readAllBytes(Paths.get(file)));
		return validator.validateSchema(serializedEnvironment);
	}
}