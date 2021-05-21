package de.fraunhofer.iais.eis.json.serializer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.fraunhofer.iais.eis.Asset;
import de.fraunhofer.iais.eis.AssetAdministrationShell;
import de.fraunhofer.iais.eis.AssetBuilder;
import de.fraunhofer.iais.eis.IdentifierType;
import de.fraunhofer.iais.eis.KeyElements;
import de.fraunhofer.iais.eis.Submodel;
import de.fraunhofer.iais.eis.json.JsonAasSerializer;

public class JsonAasSerializerTest {

    private static Logger logger = LoggerFactory.getLogger(JsonAasSerializerTest.class);

    private static JsonAasSerializer serializer = new JsonAasSerializer();

    @Test
    public void serializeEmpty() throws JsonProcessingException {
        assertEquals("null", serializer.serialize(null));
    }

    @Test
    public void serializeModelType() throws JsonProcessingException {
        Asset asset = new AssetBuilder().build();
        String serialized = serializer.serialize(asset);
        assertModelTypeEquals("Asset", serialized);
    }

    @Test
    public void serializeSimpleEnumWithCamelCase() throws JsonProcessingException {
        IdentifierType simpleEnum = IdentifierType.CUSTOM;
        String serializedEnum = serializer.serialize(simpleEnum);
        assertEquals("\"Custom\"", serializedEnum);
    }

    @Test
    public void serializeIRIEnumWithUpperCase() throws JsonProcessingException {
        IdentifierType simpleEnum = IdentifierType.IRI;
        String serializedEnum = serializer.serialize(simpleEnum);
        assertEquals("\"IRI\"", serializedEnum);
    }

    @Test
    public void serializeIRDIEnumWithUpperCase() throws JsonProcessingException {
        IdentifierType simpleEnum = IdentifierType.IRDI;
        String serializedEnum = serializer.serialize(simpleEnum);
        assertEquals("\"IRDI\"", serializedEnum);
    }

    @Test
    public void serializeEnumWithCamelCase() throws JsonProcessingException {
        KeyElements simpleEnum = KeyElements.ANNOTATED_RELATIONSHIP_ELEMENT;
        String serializedEnum = serializer.serialize(simpleEnum);
        assertEquals("\"AnnotatedRelationshipElement\"", serializedEnum);
    }

    @Test
    public void serializeSubmodel() throws JsonProcessingException {
        Submodel sm = new DemoSubmodel();

        String serialized = serializer.serialize(sm);

        logger.info("Example serialization for Submodel:");
        logger.info(serialized);

        assertFalse(serialized.isEmpty());
    }

    @Test
    public void serializeAAS() throws JsonProcessingException {
        AssetAdministrationShell sm = new DemoAssetAdministrationShell();

        String serialized = serializer.serialize(sm);

        logger.info("Example serialization for AAS:");
        logger.info(serialized);

        assertFalse(serialized.isEmpty());
    }

    private static void assertModelTypeEquals(String expectedModelType, String serialized)
            throws JsonProcessingException, JsonMappingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonTree = mapper.readTree(serialized);
        String modelType = jsonTree.get("modelType").asText();
        assertEquals(expectedModelType, modelType);
    }
}
