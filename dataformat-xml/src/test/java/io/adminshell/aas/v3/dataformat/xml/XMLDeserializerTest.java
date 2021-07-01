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

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.adminshell.aas.v3.dataformat.Deserializer;
import io.adminshell.aas.v3.dataformat.xml.XmlDeserializer;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;

public class XMLDeserializerTest {

	private static final Logger logger = LoggerFactory.getLogger(XMLDeserializerTest.class);
	private static final Deserializer deserializer = new XmlDeserializer();

	@Test
	public void testReadFromFile() throws Exception {
		String xml = new String(Files.readAllBytes(Paths.get("src/test/resources/Example_AAS_ServoDCMotor - Simplified V2.0.xml")));
		XmlDeserializer deserializer = new XmlDeserializer();
		try {
			
			AssetAdministrationShellEnvironment env = deserializer.read(xml);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));

	}
}
