package io.adminshell.aas.v3.dataformat.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersionDetector;
import com.networknt.schema.ValidationMessage;
import io.adminshell.aas.v3.dataformat.SchemaValidator;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class JsonSchemaValidator implements SchemaValidator {

    private static final String SCHEMA = "aas.json";
    private final ObjectMapper mapper = new ObjectMapper();

    protected JsonSchema schema;

    public JsonSchemaValidator() throws IOException {
        loadSchema();
    }

    public void loadSchema() throws IOException {
        JsonNode schemaRootNode = mapper.readTree(getClass().getClassLoader().getResource(SCHEMA));
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersionDetector.detect(schemaRootNode));
        schema = factory.getSchema(schemaRootNode);
    }

    public Set<ValidationMessage> validateJsonSpecific(String serialized) throws JsonProcessingException {
        JsonNode node = mapper.readTree(serialized);
        return schema.validate(node);
    }

    @Override
    public Set<String> validateSchema(String serialized) {
        try {
            Set<ValidationMessage> validationMessages = validateJsonSpecific(serialized);
            return generalizeValidationMessagesAsStringSet(validationMessages);
        } catch (JsonProcessingException e) {
            return Set.of(e.getMessage());
        }
    }

    private Set<String> generalizeValidationMessagesAsStringSet(Set<ValidationMessage> messages) {
        return messages.stream()
                .map(ValidationMessage::getMessage)
                .collect(Collectors.toSet());
    }
}
