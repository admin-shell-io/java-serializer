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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class JsonSchemaValidator implements SchemaValidator {

    private static final String SCHEMA = "aas.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public JsonSchemaValidator() {
    }

    @Override
    public Set<String> validateSchema(String serialized) {
        try{
            return validateSchema(serialized, loadDefaultSchema());
        }
        catch (IOException e) {
            return Set.of(e.getMessage());
        }
    }

    public Set<String> validateSchema(String serialized, String serializedSchema){
        try {
            JsonNode schemaRootNode = mapper.readTree(serializedSchema);
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersionDetector.detect(schemaRootNode));
            JsonSchema schema = factory.getSchema(schemaRootNode);
            JsonNode node = mapper.readTree(serialized);
            Set<ValidationMessage> validationMessages = schema.validate(node);

            return generalizeValidationMessagesAsStringSet(validationMessages);
        } catch (JsonProcessingException e) {
            return Set.of(e.getMessage());
        }
    }

    private String loadDefaultSchema() throws IOException {
        String defaultPath = Objects.requireNonNull(getClass().getClassLoader().getResource(SCHEMA)).getPath();
        return new String(Files.readAllBytes(Paths.get(defaultPath)));
    }

    private Set<String> generalizeValidationMessagesAsStringSet(Set<ValidationMessage> messages) {
        return messages.stream()
                .map(ValidationMessage::getMessage)
                .collect(Collectors.toSet());
    }
}
