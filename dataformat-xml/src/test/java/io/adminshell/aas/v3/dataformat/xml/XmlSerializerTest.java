/*
Copyright (c) 2021 Fraunhofer IOSB-INA Lemgo,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IOSB-ILT Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IAIS,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IESE,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IWU Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

This source code is licensed under the Apache License 2.0 (see LICENSE.txt).

This source code may use other Open Source software components (see LICENSE.txt).
*/

package io.adminshell.aas.v3.dataformat.xml;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.adminshell.aas.v3.dataformat.SerializationException;
import io.adminshell.aas.v3.dataformat.Serializer;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShellEnvironment;

public class XmlSerializerTest {
	private static final Logger logger = LoggerFactory.getLogger(XmlSerializerTest.class);

	private static final Serializer serializer = new XmlSerializer();
	private static XmlSchemaValidator validator;

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@BeforeClass
	public static void prepareValidator() throws SAXException {
		validator = new XmlSchemaValidator();
	}

	@Test
	public void testWriteToFile() throws JsonProcessingException, IOException, SerializationException {
		File file = tempFolder.newFile("output.xml");
		new XmlSerializer().write(file, AASSimple.ENVIRONMENT);
		assertTrue(file.exists());
	}

	@Test
	public void testSerializeMinimal() throws IOException, SerializationException, SAXException {
		File minimalFile = new File("src/test/resources/minimum.xml");
		AssetAdministrationShellEnvironment environment2Serialize = new DefaultAssetAdministrationShellEnvironment.Builder()
				.build();
		validateXmlSerializer(minimalFile, environment2Serialize);
	}

	@Test
	public void testSerializeSimple() throws IOException, SerializationException, SAXException {
		File simpleAASFile = new File("src/test/resources/minimum.xml");
		// TODO: Create expected file for simple example
		validateXmlSerializer(simpleAASFile, AASSimple.ENVIRONMENT);
	}

	@Test
	public void testSerializeFull() throws IOException, SerializationException, SAXException {
		File fullAASFile = new File("src/test/resources/minimum.xml");
		// TODO: Create expected file for full example
		validateXmlSerializer(fullAASFile, AASFull.ENVIRONMENT);
	}

	private void validateXmlSerializer(File expectedFile, AssetAdministrationShellEnvironment environment)
			throws IOException, SerializationException, SAXException {
		String expected = Files.readString(expectedFile.toPath());
		String actual = new XmlSerializer().write(environment);
		Set<String> errors = new XmlSchemaValidator().validateSchema(actual);

		logger.info(actual);
		logErrors(expectedFile.getName(), errors);
		assertTrue(errors.isEmpty());
		// TODO: AssertEquals with expected file
	}

	private void logErrors(String validatedFileName, Set<String> errors) {
		if (errors.isEmpty())
			return;

		logger.info("Validate file: " + validatedFileName);
		for (String error : errors) {
			logger.info(error);
		}
	}
}
