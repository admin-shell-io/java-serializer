package de.fraunhofer.iais.eis.json.serializer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.fraunhofer.iais.eis.AssetAdministrationShellEnvironment;
import de.fraunhofer.iais.eis.json.JsonAasDeserializer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JsonAasDeserializerTest {

    private static Logger logger = LoggerFactory.getLogger(JsonAasDeserializerTest.class);

    private static JsonAasDeserializer deserializer = new JsonAasDeserializer();

    @Test
    public void deserializeEnvironment() throws JsonProcessingException, IOException {
//        AssetAdministrationShellEnvironment env = new AssetAdministrationShellEnvironmentBuilder()
//                .assetAdministrationShells(Arrays.asList(new AssetAdministrationShellBuilder()
//                        .build()))
//                .build();
//        AssetAdministrationShell sm = new DemoAssetAdministrationShell();
        String json = new String(Files.readAllBytes(new File("src/test/resources/jsonExample.json").toPath()));
        AssetAdministrationShellEnvironment aasEnvironment = deserializer.deserialize(json);

        logger.info("Example serialization for AAS:");
//        logger.info(serialized);
//
//        assertFalse(serialized.isEmpty());
    }

    private static void assertModelTypeEquals(String expectedModelType, String serialized)
            throws JsonProcessingException, JsonMappingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonTree = mapper.readTree(serialized);
        String modelType = jsonTree.get("modelType").asText();
        assertEquals(expectedModelType, modelType);
    }
}
