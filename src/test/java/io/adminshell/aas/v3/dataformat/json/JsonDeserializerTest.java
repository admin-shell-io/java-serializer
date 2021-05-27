package io.adminshell.aas.v3.dataformat.json;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import de.fraunhofer.iais.eis.AssetAdministrationShellEnvironment;
import de.fraunhofer.iais.eis.DataSpecificationIEC61360;
import de.fraunhofer.iais.eis.DefaultProperty;
import de.fraunhofer.iais.eis.SubmodelElement;
import de.fraunhofer.iais.eis.SubmodelElementCollection;
import io.adminshell.aas.v3.dataformat.json.JsonDeserializer;
import io.adminshell.aas.v3.dataformat.json.modeltype.ModelTypeProcessor;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

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
