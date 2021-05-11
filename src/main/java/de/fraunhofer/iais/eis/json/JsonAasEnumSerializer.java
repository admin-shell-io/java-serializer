package de.fraunhofer.iais.eis.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@SuppressWarnings("serial")
public class JsonAasEnumSerializer extends StdSerializer<Object> {

	protected JsonAasEnumSerializer() {
		super(Object.class);
	}

	@Override
	public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		String enumId = getEnumIdFromObject(value);
		if (isExceptionFromCamelCase(enumId)) {
			gen.writeString(generateEnumExceptionString(enumId));
			return;
		}

		String lowerCaseEnumName = enumId.toLowerCase();
		String firstLetterUpperCase = handleFirstLetter(lowerCaseEnumName);
		String camelCaseEnumString = handleUnderscores(firstLetterUpperCase);
		gen.writeString(camelCaseEnumString);
	}

	private String getEnumIdFromObject(Object value) {
		String[] enumIdParts = value.toString().split("/");
		return enumIdParts[enumIdParts.length - 1];
	}

	private String generateEnumExceptionString(String enumName) {
		return enumName;
	}

	private boolean isExceptionFromCamelCase(String enumName) {
		return enumName.equals("IRDI") || enumName.equals("IRI");
	}

	private String handleUnderscores(String withUnderscores) {
		String withCamelCase = "";

		boolean charToUpperCase = false;
		for (int i = 0; i < withUnderscores.length(); i++) {
			char currentChar = withUnderscores.charAt(i);
			if (charToUpperCase) {
				withCamelCase += Character.toUpperCase(currentChar);
				charToUpperCase = false;
			} else if (currentChar == '_') {
				charToUpperCase = true;
			} else {
				withCamelCase += currentChar;
			}
		}
		return withCamelCase;
	}

	private String handleFirstLetter(String lowerCase) {
		return lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
	}
}
