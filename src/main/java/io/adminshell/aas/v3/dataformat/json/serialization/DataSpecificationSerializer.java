package io.adminshell.aas.v3.dataformat.json.serialization;

import static io.adminshell.aas.v3.dataformat.json.DataSpecificationManager.PROP_DATA_SPECIFICATION;
import static io.adminshell.aas.v3.dataformat.json.DataSpecificationManager.PROP_DATA_SPECIFICATION_CONTENT;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import io.adminshell.aas.v3.dataformat.json.DataSpecificationManager;
import io.adminshell.aas.v3.model.DataSpecification;
import io.adminshell.aas.v3.model.Reference;

/**
 * Custom Serializer for class DataSpecification. Adds type information in form
 * of a reference. Uses DataSpecificationManager to resolve java type to
 * reference.
 */
public class DataSpecificationSerializer extends JsonSerializer<DataSpecification> {

    @Override
    public void serialize(DataSpecification data, JsonGenerator generator, SerializerProvider provider)
            throws IOException {
        Reference reference = DataSpecificationManager.getReference(data.getDataSpecificationContent().getClass());
        generator.writeStartObject();
        generator.writeObjectField(PROP_DATA_SPECIFICATION, reference);
        generator.writeObjectField(PROP_DATA_SPECIFICATION_CONTENT, data.getDataSpecificationContent());
        generator.writeEndObject();
    }

    @Override
    public void serializeWithType(DataSpecification data, JsonGenerator generator, SerializerProvider provider, TypeSerializer typedSerializer)
            throws IOException, JsonProcessingException {

        serialize(data, generator, provider);
    }

}
