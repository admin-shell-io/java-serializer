package io.adminshell.aas.v3.dataformat.json;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import static org.junit.Assert.assertFalse;

public class JsonSerializerTest {

    private static final Logger logger = LoggerFactory.getLogger(JsonSerializerTest.class);

    private static final JsonSerializer serializer = new JsonSerializer();

    @Test
    public void serializeEmpty() throws JsonProcessingException {
        assertEquals("null", serializer.serialize(null));
    }

    @Test
    public void serialize() throws JsonProcessingException {
        String serialized = serializer.serialize(DemoAAS.ENVIRONMENT);
        logger.info("Example serialization for AAS:");
        logger.info(serialized);
        assertFalse(serialized.isEmpty());
    }
}
