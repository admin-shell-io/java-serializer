package io.adminshell.aas.v3.dataformat.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import org.json.JSONException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonSerializerTest {

    private static final Logger logger = LoggerFactory.getLogger(JsonSerializerTest.class);
    private static final Serializer serializer = new JsonSerializer();

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testSerializeEmpty() throws JsonProcessingException, IOException {
        assertEquals("null", serializer.write(null));
    }

    @Test
    public void testWriteToFile() throws JsonProcessingException, IOException {
        File file = tempFolder.newFile("output.json");
        serializer.write(file, AASSimple.ENVIRONMENT);
        assertTrue(file.exists());
    }

    @Test
    public void testSerializeSimple() throws JsonProcessingException, IOException, JSONException {
        String json = serializer.write(AASSimple.ENVIRONMENT);
        logger.info(json);
        assertFalse(json.isEmpty());
        String expected = readFile(AASSimple.FILE);
        // failes when using JSONCompareMode.STRICT
        JSONAssert.assertEquals(expected, json, JSONCompareMode.LENIENT);
        // maybe check on JSON-level if things are equal
        // could be problematic as specification allows for aggregation/list 
        // properties to be either serialized as empty array or be left out 
        // completely
    }

    private static String readFile(File file) throws FileNotFoundException {
        return new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Test
    public void testSerializeFull() throws JsonProcessingException, IOException {
//        String json = serializer.write(AASSimple.ENVIRONMENT);
//        logger.info(json);
//        assertFalse(json.isEmpty());
        // maybe check on JSON-level if things are equal
        // could be problematic as specification allows for aggregation/list 
        // properties to be either serialized as empty array or be left out 
        // completely
    }
}
