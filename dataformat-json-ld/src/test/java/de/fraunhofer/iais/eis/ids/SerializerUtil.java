package de.fraunhofer.iais.eis.ids;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

/**
 * Helper class for Serializer Tests
 * 
 * @author sbader
 *
 */
public class SerializerUtil {

	

    public static String readResourceToString(String resourceName) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(resourceName);
        StringWriter writer = new StringWriter();
        IOUtils.copy(is, writer, "UTF-8");
        return writer.toString();
    }

    public static String stripWhitespaces(String input) {
        return input.replaceAll("\\s+", "");
    }
    
}
