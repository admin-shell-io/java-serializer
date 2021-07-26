package io.adminshell.aas.v3.model.dataformat.jsonld;

import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.dataformat.jsonld.Serializer;
import io.adminshell.aas.v3.model.Asset;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.Reference;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class ParserTest {

    @Test
    public void parseAASEnvironmentTest() throws IOException, DeserializationException {
        String aasEnvAsString = SerializerUtil.readResourceToString("example-from-serializer.jsonld");
        AssetAdministrationShellEnvironment aasEnv = new Serializer().read(aasEnvAsString);
        Assert.assertEquals(1, aasEnv.getSubmodels().size());
        Assert.assertEquals(1, aasEnv.getAssetAdministrationShells().get(0).getDescriptions().size());
        Assert.assertEquals(2, aasEnv.getAssetAdministrationShells().get(0).getDisplayNames().size());

        Assert.assertEquals("de", aasEnv.getAssetAdministrationShells().get(0).getDisplayNames().get(0).getLanguage());
        Assert.assertEquals("en", aasEnv.getAssetAdministrationShells().get(0).getDisplayNames().get(1).getLanguage());
        Assert.assertNull(aasEnv.getAssetAdministrationShells().get(0).getDescriptions().get(0).getLanguage());


    }

    @Test
    public void parseAllSchemaExamplesTest() throws IOException, DeserializationException {
        Serializer serializer = new Serializer();
        int errorCtr = 0;

        Map<String, Class<?>> toBeParsed = new HashMap<>();
        toBeParsed.put(SerializerUtil.readResourceToString("AAS_Reference_shortExample.ttl"), AssetAdministrationShell.class);
        /*
        toBeParsed.put(SerializerUtil.readResourceToString("AAS_Reference_shortExample.nt"), Reference.class);
        toBeParsed.put(SerializerUtil.readResourceToString("Asset_Example.nt"), Asset.class);
        toBeParsed.put(SerializerUtil.readResourceToString("Asset_Example.ttl"), Asset.class);
        toBeParsed.put(SerializerUtil.readResourceToString("AssetAdministrationShell_Example.ttl"), AssetAdministrationShell.class);
        toBeParsed.put(SerializerUtil.readResourceToString("Complete_Example.ttl"), AssetAdministrationShell.class);
        toBeParsed.put(SerializerUtil.readResourceToString("KapitalVerwaltungsschaleExample.ttl"), Reference.class);
        toBeParsed.put(SerializerUtil.readResourceToString("Overall-Example.nt"), Reference.class);
        toBeParsed.put(SerializerUtil.readResourceToString("ReferenceExample.ttl"), Reference.class);
        toBeParsed.put(SerializerUtil.readResourceToString("Submodel_SubmodelElement_Example.ttl"), Reference.class);
        toBeParsed.put(SerializerUtil.readResourceToString("Submodel_SubmodelElement_shortExample.ttl"), Reference.class);
        toBeParsed.put(SerializerUtil.readResourceToString("Submodel_SubmodelElement_shortExample.nt"), Reference.class);
        */

        for(Map.Entry<String, Class<?>> elementToBeParsed : toBeParsed.entrySet())
        {
            try {
                serializer.deserialize(elementToBeParsed.getKey(), elementToBeParsed.getValue());
            }
            catch (IOException e)
            {
                errorCtr++;
                e.printStackTrace();
            }
        }
        Assert.assertEquals(0, errorCtr);


        AssetAdministrationShell aas = serializer.deserialize(SerializerUtil.readResourceToString("AAS_Reference_shortExample.ttl"), AssetAdministrationShell.class);
        System.out.println(serializer.serialize(aas));
    }

}
