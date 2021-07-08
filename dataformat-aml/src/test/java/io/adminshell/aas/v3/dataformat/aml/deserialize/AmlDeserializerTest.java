package io.adminshell.aas.v3.dataformat.aml.deserialize;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.dataformat.aml.AmlDeserializer;
import org.junit.Test;

import java.io.*;

public class AmlDeserializerTest {

    private final AmlDeserializer deserializer = new AmlDeserializer();

    private final XmlMapper xmlMapper = new XmlMapper();

    @Test
    public void testCoffeeBeanExample() throws IOException, DeserializationException {
        final String file = "src/test/resources/amlfile/coffee-bean.xml";
        String xml = inputStreamToString(new FileInputStream(file));
        deserializer.read(xml);
    }

    private String inputStreamToString(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        return stringBuilder.toString();
    }
}
