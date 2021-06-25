package io.adminshell.aas.v3.dataformat.xml.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ByteArrayDeserializer extends JsonDeserializer<byte[]> {

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    
    private final Charset charset;
    
    public ByteArrayDeserializer() {
        this.charset = DEFAULT_CHARSET;
    }
    
    public ByteArrayDeserializer(Charset charset) {
        this.charset = charset;
    }

    @Override
    public byte[] deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = parser.getCodec().readTree(parser);
        if (node == null) {
            return null;
        }
        return node.asText().getBytes(charset);
    }
}
