package io.adminshell.aas.v3.dataformat.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.adminshell.aas.v3.dataformat.SerializationException;
import io.adminshell.aas.v3.dataformat.Serializer;

public class JsonSerializerTest {

	private static final Logger logger = LoggerFactory.getLogger(JsonSerializerTest.class);
	private static final Serializer serializer = new JsonSerializer();

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Test
	public void testSerializeEmpty() throws JsonProcessingException, IOException, SerializationException {
		assertEquals("null", serializer.write(null));
	}

	@Test
	public void testWriteToFile() throws JsonProcessingException, IOException, SerializationException {
		File file = tempFolder.newFile("output.json");
		serializer.write(file, AASSimple.ENVIRONMENT);
		assertTrue(file.exists());
	}

	@Test
	public void testSimpleEnvValidatedByJsonLenient() throws SerializationException, JSONException, IOException {
		String json = serializer.write(AASSimple.ENVIRONMENT);
		logger.info(json);
		assertFalse(json.isEmpty());
		String expected = readFile(AASSimple.FILE);
		JSONAssert.assertEquals(expected, json, JSONCompareMode.LENIENT);
		// failes when using JSONCompareMode.STRICT
		// maybe check on JSON-level if things are equal
		// could be problematic as specification allows for aggregation/list
		// properties to be either serialized as empty array or be left out
		// completely
	}

	private static String readFile(File file) throws IOException {
		try (FileInputStream fileInputStream = new FileInputStream(file)) {
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
		}
	}

	@Test
	public void testSimpleEnvValidatedBySchema() throws SerializationException, JSONException, IOException {
		String json = serializer.write(AASSimple.ENVIRONMENT);
		JsonSchemaValidator schemaValidator = new JsonSchemaValidator();
		Set<String> foundErrors = schemaValidator.validateSchema(json);
		assertTrue(foundErrors.isEmpty());
	}
}
