package de.fraunhofer.iais.eis.json.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Before;
import org.junit.Test;

import de.fraunhofer.iais.eis.AssetAdministrationShell;
import de.fraunhofer.iais.eis.json.JsonAasFileWriter;
import de.fraunhofer.iais.eis.json.JsonAasSerializer;

public class JsonFileWriterTest {
	private static final JsonAasFileWriter fileWriter = new JsonAasFileWriter();
	private static final String TEST_FILENAME = "testJsonSerialization.json";
	private static final AssetAdministrationShell DEMO_AAS = new DemoAssetAdministrationShell();

	@Before
	public void clearFile() {
		File file2Remove = new File(TEST_FILENAME);
		if (file2Remove.exists()) {
			file2Remove.delete();
		}
	}

	@Test
	public void writeFileForAas() throws IOException {
		fileWriter.serializeToFile(DEMO_AAS, TEST_FILENAME);
		File outputFile = new File(TEST_FILENAME);
		assertTrue(outputFile.exists());
	}

	@Test
	public void serializeAas() throws IOException {
		fileWriter.serializeToFile(DEMO_AAS, TEST_FILENAME);

		JsonAasSerializer stringSerializer = new JsonAasSerializer();
		String serializedAasFromString = stringSerializer.serialize(DEMO_AAS);

		File outputFile = new File(TEST_FILENAME);
		String serializedAasFromFile = new String(Files.readAllBytes(outputFile.toPath()));

		assertEquals(serializedAasFromString, serializedAasFromFile);
	}
}
