package io.adminshell.aas.v3.dataformat.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Before;
import org.junit.Test;

public class JsonFileWriterTest {

    private static final JsonFileWriter fileWriter = new JsonFileWriter();
    private static final String TEST_FILENAME = "testJsonSerialization.json";

    @Before
    public void clearFile() {
        File file2Remove = new File(TEST_FILENAME);
        if (file2Remove.exists()) {
            file2Remove.delete();
        }
    }

    @Test
    public void writeFileForAas() throws IOException {
        fileWriter.serializeToFile(DemoAAS.ENVIRONMENT, TEST_FILENAME);
        File outputFile = new File(TEST_FILENAME);
        assertTrue(outputFile.exists());
    }

    @Test
    public void serializeAas() throws IOException {
        fileWriter.serializeToFile(DemoAAS.ENVIRONMENT, TEST_FILENAME);

        JsonSerializer stringSerializer = new JsonSerializer();
        String serializedAasFromString = stringSerializer.serialize(DemoAAS.ENVIRONMENT);

        File outputFile = new File(TEST_FILENAME);
        String serializedAasFromFile = new String(Files.readAllBytes(outputFile.toPath()));

        assertEquals(serializedAasFromString, serializedAasFromFile);
    }
}
