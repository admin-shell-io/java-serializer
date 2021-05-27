package io.adminshell.aas.v3.dataformat.json.deserialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import de.fraunhofer.iais.eis.DataSpecification;
import de.fraunhofer.iais.eis.DataSpecificationContent;
import de.fraunhofer.iais.eis.DataSpecificationIEC61360;
import de.fraunhofer.iais.eis.DefaultDataSpecificationBuilder;
import de.fraunhofer.iais.eis.Key;
import de.fraunhofer.iais.eis.KeyElements;
import de.fraunhofer.iais.eis.KeyType;
import de.fraunhofer.iais.eis.Reference;
import java.util.Map;

public class DataSpecificationDeserializer extends JsonDeserializer<DataSpecification> {

    private static final String PROP_DATA_SPECIFICATION = "dataSpecification";
    private static final String PROP_DATA_SPECIFICATION_CONTENT = "dataSpecificationContent";

    private static final Map<String, Class<? extends DataSpecificationContent>> KNOWN_SUBTYPES = Map.of(
            "http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0", DataSpecificationIEC61360.class);

    public static void registerSubtype(String iri, Class<? extends DataSpecificationContent> implementation) {
        KNOWN_SUBTYPES.put(iri, implementation);
    }

    @Override
    public DataSpecification deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        Object temp = parser.getCodec().readTree(parser);
        ObjectNode node = (ObjectNode) temp;
        if (node == null) {
            throwInvalidType(parser);
        }
        JsonNode nodeDataSpecification = node.get(PROP_DATA_SPECIFICATION);
        if (nodeDataSpecification == null) {
            throwInvalidType(parser);
        }
        JsonParser parserReference = parser.getCodec().getFactory().getCodec().treeAsTokens(nodeDataSpecification);
        parserReference.nextToken();
        Reference reference = parserReference.readValueAs(Reference.class);
        if (reference.getKeys().size() != 1) {
            throwInvalidType(parser);
        }
        Key key = reference.getKeys().get(0);
        if (key.getType() != KeyElements.GLOBAL_REFERENCE
                || key.getIdType() != KeyType.IRI
                || !KNOWN_SUBTYPES.containsKey(key.getValue())) {
            throwInvalidType(parser);
        }
        Class<? extends DataSpecificationContent> targetClass = KNOWN_SUBTYPES.get(key.getValue());
        JsonNode nodeContent = node.get(PROP_DATA_SPECIFICATION_CONTENT);
        if (nodeContent == null) {
            context.reportInputMismatch(targetClass, "property {} must not be empty", PROP_DATA_SPECIFICATION_CONTENT);
        }
        JsonParser parserContent = parser.getCodec().getFactory().getCodec().treeAsTokens(nodeContent);
        parserContent.nextToken();
        DataSpecificationContent content = parserContent.readValueAs(targetClass);
        return new DefaultDataSpecificationBuilder()
                .dataSpecificationContent(content)
                .build();
    }

    private void throwInvalidType(JsonParser parser) throws JsonMappingException {
        throw new JsonMappingException(parser,
                String.format(
                        "property '%s' must contain a reference with exactly 1 key with following properties: type=GlobalReference, idType=IRI, value= ",
                        PROP_DATA_SPECIFICATION,
                        String.join("|", KNOWN_SUBTYPES.keySet())));
    }

}
