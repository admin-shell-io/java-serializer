package io.adminshell.aas.v3.dataformat.json.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import de.fraunhofer.iais.eis.DataSpecification;
import de.fraunhofer.iais.eis.Reference;
import io.adminshell.aas.v3.dataformat.json.DataSpecificationManager;
import static io.adminshell.aas.v3.dataformat.json.DataSpecificationManager.PROP_DATA_SPECIFICATION;
import static io.adminshell.aas.v3.dataformat.json.DataSpecificationManager.PROP_DATA_SPECIFICATION_CONTENT;
import java.io.IOException;

public class DataSpecificationSerializer extends JsonSerializer<DataSpecification> {

    @Override
    public void serialize(DataSpecification data, JsonGenerator generator, SerializerProvider provider) throws IOException {
        Reference reference = DataSpecificationManager.getReference(data.getDataSpecificationContent().getClass());
        generator.writeStartObject();
        generator.writeObjectField(PROP_DATA_SPECIFICATION, reference);
        generator.writeObjectField(PROP_DATA_SPECIFICATION_CONTENT, data.getDataSpecificationContent());
        generator.writeEndObject();
    }
}
