package io.adminshell.aas.v3.dataformat.json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersionDetector;
import com.networknt.schema.ValidationMessage;

import io.adminshell.aas.v3.dataformat.SchemaValidator;

public class JsonSchemaValidator implements SchemaValidator {
	private static final String SCHEMA = "src/main/resources/aas.json";
	private ObjectMapper mapper = new ObjectMapper();

	protected JsonSchema schema;

	public JsonSchemaValidator() throws IOException {
		loadSchemaFromResource();
	}

	public void loadSchemaFromResource() throws IOException {
		JsonNode schemaRootNode = mapper.readTree(new String(Files.readAllBytes(Paths.get(SCHEMA))));
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
			return convertProcessingExceptionToErrorMessages(e);
		}
	}

	private Set<String> convertProcessingExceptionToErrorMessages(JsonProcessingException e) {
		Set<String> errorMessages = new HashSet<>();
		errorMessages.add(e.getMessage());
		return errorMessages;
	}

	private Set<String> generalizeValidationMessagesAsStringSet(Set<ValidationMessage> messages) {
		Set<String> errorMessages = new HashSet<>();
		for (ValidationMessage message : messages) {
			errorMessages.add(message.getMessage());
		}
		return errorMessages;
	}
}