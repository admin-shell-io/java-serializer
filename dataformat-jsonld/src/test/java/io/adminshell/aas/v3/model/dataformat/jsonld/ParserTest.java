package io.adminshell.aas.v3.model.dataformat.jsonld;

import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.dataformat.jsonld.Serializer;
import io.adminshell.aas.v3.model.*;
import org.apache.jena.riot.RDFLanguages;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

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
        //These work


        serializer.deserialize(SerializerUtil.readResourceToString("AAS_Reference_shortExample.ttl"), AssetAdministrationShell.class, RDFLanguages.TURTLE);
        serializer.deserialize(SerializerUtil.readResourceToString("AAS_Reference_shortExample.nt"), AssetAdministrationShell.class, RDFLanguages.NTRIPLES);
        serializer.deserialize(SerializerUtil.readResourceToString("Asset_Example.nt"), Asset.class, RDFLanguages.NTRIPLES);
        serializer.deserialize(SerializerUtil.readResourceToString("Asset_Example.ttl"), Asset.class, RDFLanguages.TURTLE);
        serializer.deserialize(SerializerUtil.readResourceToString("AssetAdministrationShell_Example.ttl"), AssetAdministrationShell.class, RDFLanguages.TURTLE);
        //serializer.deserialize(SerializerUtil.readResourceToString("Complete_Example.ttl"), AssetAdministrationShell.class, RDFLanguages.TURTLE);
        serializer.deserialize(SerializerUtil.readResourceToString("KapitalVerwaltungsschaleExample.ttl"), Property.class, RDFLanguages.TURTLE);
        serializer.deserialize(SerializerUtil.readResourceToString("Overall-Example.nt"), AssetAdministrationShell.class, RDFLanguages.NTRIPLES);
        serializer.deserialize(SerializerUtil.readResourceToString("ReferenceExample.ttl"), AssetAdministrationShell.class, RDFLanguages.TURTLE);
        serializer.deserialize(SerializerUtil.readResourceToString("Submodel_SubmodelElement_Example.ttl"), Submodel.class, RDFLanguages.TURTLE);



        //The following examples do not work yet, as they are semantically problematic
        //serializer.deserialize(SerializerUtil.readResourceToString("Submodel_SubmodelElement_shortExample.ttl"), Reference.class, RDFLanguages.TURTLE);
        //serializer.deserialize(SerializerUtil.readResourceToString("Submodel_SubmodelElement_shortExample.nt"), Reference.class, RDFLanguages.NTRIPLES);
    }

}
