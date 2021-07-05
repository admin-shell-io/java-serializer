package io.adminshell.aas.v3.dataformat.xml;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;

public class XMLDeserializerTest {

    @Test
    public void deserializeAASSimple() throws Exception {
        AssetAdministrationShellEnvironment env = new XmlDeserializer().read(AASSimple.FILE);
        assertEquals(AASSimple.ENVIRONMENT, env);
    }

    @Test
    public void deserializeAASFull() throws FileNotFoundException, DeserializationException {
        AssetAdministrationShellEnvironment env = new XmlDeserializer().read(AASFull.FILE);
        assertEquals(AASFull.ENVIRONMENT, env);
    }
}
