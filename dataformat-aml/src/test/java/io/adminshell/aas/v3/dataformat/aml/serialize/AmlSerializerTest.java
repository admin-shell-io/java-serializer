package io.adminshell.aas.v3.dataformat.aml.serialize;

import io.adminshell.aas.v3.dataformat.aml.fixtures.FullExample;
import io.adminshell.aas.v3.dataformat.SerializationException;
import io.adminshell.aas.v3.dataformat.aml.AmlSerializer;
import org.junit.Test;

public class AmlSerializerTest {

    private final AmlSerializer serializer = new AmlSerializer();

    @Test
    public void testSAPFullExample() throws SerializationException {
        String actual = serializer.write(FullExample.ENVIRONMENT);
        System.err.println(actual);
    }
}
