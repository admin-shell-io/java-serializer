package io.adminshell.aas.v3.model.dataformat.jsonld;

import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.dataformat.jsonld.Serializer;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ParserTest {

    @Test
    public void parseAASEnvironmentTest() throws IOException, DeserializationException {
        String aasEnvAsString = SerializerUtil.readResourceToString("example.jsonld");
        AssetAdministrationShellEnvironment aasEnv = new Serializer().read(aasEnvAsString);
        Assert.assertEquals(1, aasEnv.getSubmodels().size());
        Assert.assertEquals(1, aasEnv.getAssetAdministrationShells().get(0).getDescriptions().size());
        Assert.assertEquals(2, aasEnv.getAssetAdministrationShells().get(0).getDisplayNames().size());

        Assert.assertEquals("de", aasEnv.getAssetAdministrationShells().get(0).getDisplayNames().get(0).getLanguage());
        Assert.assertEquals("en", aasEnv.getAssetAdministrationShells().get(0).getDisplayNames().get(1).getLanguage());
        Assert.assertNull(aasEnv.getAssetAdministrationShells().get(0).getDescriptions().get(0).getLanguage());


    }

}
