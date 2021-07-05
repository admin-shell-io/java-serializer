package io.adminshell.aas.v3.dataformat.core.deserialization;

import static io.adminshell.aas.v3.dataformat.core.DataSpecificationManager.PROP_DATA_SPECIFICATION;
import static io.adminshell.aas.v3.dataformat.core.DataSpecificationManager.PROP_DATA_SPECIFICATION_CONTENT;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.adminshell.aas.v3.dataformat.core.DataSpecificationManager;
import io.adminshell.aas.v3.model.DataSpecificationContent;
import io.adminshell.aas.v3.model.EmbeddedDataSpecification;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.impl.DefaultEmbeddedDataSpecification;

/**
 * Custom Deserializer for class DataSpecification. First reads property
 * PROP_DATA_SPECIFICATION and tries to resolve which Java class to use for
 * deserialization based on the found value with the help of
 * DataSpecificationManager.
 */
public class EmbeddedDataSpecificationDeserializer extends JsonDeserializer<EmbeddedDataSpecification> {

    @Override
    public EmbeddedDataSpecification deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        Object temp = parser.getCodec().readTree(parser);
        ObjectNode node = (ObjectNode) temp;
        if (node == null) {
            return null;
        }
        JsonNode nodeDataSpecification = node.get(PROP_DATA_SPECIFICATION);
        if (nodeDataSpecification == null) {
            throw new JsonMappingException(parser,
                    String.format("data specification must contain node '%s'", PROP_DATA_SPECIFICATION));
        }
        JsonParser parserReference = parser.getCodec().getFactory().getCodec().treeAsTokens(nodeDataSpecification);
        parserReference.nextToken();
        Reference reference = parserReference.readValueAs(Reference.class);
        JsonNode nodeContent = node.get(PROP_DATA_SPECIFICATION_CONTENT);
        if (nodeContent != null) {
            Class<? extends DataSpecificationContent> targetClass = DataSpecificationManager.getImplementation(reference);
            JsonParser parserContent = parser.getCodec().getFactory().getCodec().treeAsTokens(nodeContent);
            parserContent.nextToken();
            DataSpecificationContent content = parserContent.readValueAs(targetClass);
            return new DefaultEmbeddedDataSpecification.Builder().dataSpecificationContent(content).build();
        }
        return new DefaultEmbeddedDataSpecification.Builder().dataSpecification(reference).build();
    }
}
