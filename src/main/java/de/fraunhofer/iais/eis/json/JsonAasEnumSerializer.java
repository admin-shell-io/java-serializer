package de.fraunhofer.iais.eis.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonAasEnumSerializer extends JsonSerializer<Enum> {

    private static final PropertyNamingStrategyBase DEFAULT_NAMING_STRATEGY = new UpperSnakeCaseToUpperCamelCaseStrategy();

    @Override
    public void serialize(Enum value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String enumId = getEnumIdFromObject(value);
        if (isExceptionFromCamelCase(enumId)) {
            gen.writeString(enumId);
            return;
        }
        gen.writeString(DEFAULT_NAMING_STRATEGY.translate(enumId));
    }

    private String getEnumIdFromObject(Object value) {
        String[] enumIdParts = value.toString().split("/");
        return enumIdParts[enumIdParts.length - 1];
    }

    private boolean isExceptionFromCamelCase(String enumName) {
        return enumName.equals("IRDI") || enumName.equals("IRI");
    }

}
