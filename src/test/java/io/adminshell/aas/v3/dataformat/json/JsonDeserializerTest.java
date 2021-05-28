package io.adminshell.aas.v3.dataformat.json;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.fraunhofer.iais.eis.AssetAdministrationShellEnvironment;
import java.io.IOException;

public class JsonDeserializerTest {

    private static Logger logger = LoggerFactory.getLogger(JsonDeserializerTest.class);

    private static JsonFileReader deserializer = new JsonFileReader();

    @Test
    public void deserializeEnvironment() throws JsonProcessingException, IOException {
        AssetAdministrationShellEnvironment aasEnvironment = deserializer.deserializeFromFile("src/test/resources/jsonExample.json");
        logger.info("successfully deserialized simple example");
    }

    @Test
    public void deserializeEnvironmentFull() throws JsonProcessingException, IOException {
        AssetAdministrationShellEnvironment aasEnvironment = deserializer.deserializeFromFile("src/test/resources/test_demo_full_example.json");
        logger.info("successfully deserialized full example");
    }

}
