package de.fraunhofer.iais.eis.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.function.Consumer;

/**
 *
 * @author Michael Jacoby
 */
public class UnwrapTypeInfoDeserializer<T> extends JsonDeserializer<T> {

    private static final String MODEL_TYPE = "modelType";
    private static final String MODEL_TYPE_NAME = "name";

    @Override
    public T deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        throw new IllegalStateException(getClass().getName() + " can only be used with type");
    }

    @Override
    public Object deserializeWithType(JsonParser parser, DeserializationContext context, TypeDeserializer typeDeserializer)
            throws IOException {
        Object temp = parser.getCodec().readTree(parser);
        ObjectNode node = (ObjectNode) temp;
        if (node.get(MODEL_TYPE) != null) {
            node.replace(MODEL_TYPE, node.get(MODEL_TYPE).get(MODEL_TYPE_NAME));
        }
        JsonParser newParser = parser.getCodec().getFactory().getCodec().treeAsTokens(node);
        newParser.nextToken();
        return typeDeserializer.deserializeTypedFromAny(newParser, context);
    }
}
