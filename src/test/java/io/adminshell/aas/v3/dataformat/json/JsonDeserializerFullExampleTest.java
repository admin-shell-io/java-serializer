package io.adminshell.aas.v3.dataformat.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.fraunhofer.iais.eis.*;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonDeserializerFullExampleTest {

    private static Logger logger = LoggerFactory.getLogger(JsonDeserializerTest.class);

    private static JsonFileReader deserializer = new JsonFileReader();

    private AssetAdministrationShellEnvironment deserializedAASEnv;
    private AssetAdministrationShellEnvironment sourceAASEnv;


    @Before
    public void init() throws IOException {
        deserializedAASEnv =  deserializer.deserializeFromFile("src/test/resources/test_demo_full_example.json");
        sourceAASEnv = DemoAAS_FullExample.ENVIRONMENT;
    }

    @Test
    public void deserializeEnvironment() throws JsonProcessingException, IOException {
        AssetAdministrationShellEnvironment aasEnvironment = deserializer.deserializeFromFile("src/test/resources/jsonExample.json");
        logger.info("successfully deserialized simple example");
    }


    @Test
    public void deserializeEnvironmentAndCheckSubmodels() throws JsonProcessingException, IOException {

        assertTrue(deserializedAASEnv.getSubmodels().size() == sourceAASEnv.getSubmodels().size());

        assertEquals(deserializedAASEnv.getSubmodels().get(0).getIdShort(), sourceAASEnv.getSubmodels().get(0).getIdShort());
        assertEquals(deserializedAASEnv.getSubmodels().get(1).getIdShort(), sourceAASEnv.getSubmodels().get(1).getIdShort());
        assertEquals(deserializedAASEnv.getSubmodels().get(2).getIdShort(), sourceAASEnv.getSubmodels().get(2).getIdShort());

        for(int i = 0; i<deserializedAASEnv.getSubmodels().size(); i++){
            Submodel des_submodel = deserializedAASEnv.getSubmodels().get(i);
            Submodel src_submodel = sourceAASEnv.getSubmodels().get(i);

            //logger.info("Check " + des_submodel.getIdShort());
            assertEquals(des_submodel, src_submodel);
        }
    }

    @Test
    public void deserializeEnvironmentAndCheckAssets() throws JsonProcessingException, IOException {

        assertTrue(deserializedAASEnv.getAssets().size() == sourceAASEnv.getAssets().size());

        assertEquals(deserializedAASEnv.getAssets().get(0).getIdShort(), sourceAASEnv.getAssets().get(0).getIdShort());

        for(int i = 0; i<deserializedAASEnv.getAssets().size(); i++){
            Asset des_asset = deserializedAASEnv.getAssets().get(i);
            Asset src_asset = sourceAASEnv.getAssets().get(i);

            logger.info("Check " + des_asset.getIdShort());
            assertEquals(des_asset, src_asset);
        }
    }

    @Test
    public void deserializeEnvironmentAndCheckConceptDescriptions() throws JsonProcessingException, IOException {

        assertTrue(deserializedAASEnv.getConceptDescriptions().size() == sourceAASEnv.getConceptDescriptions().size());

        assertEquals(deserializedAASEnv.getConceptDescriptions().get(0).getIdShort(), sourceAASEnv.getConceptDescriptions().get(0).getIdShort());
        assertEquals(deserializedAASEnv.getConceptDescriptions().get(1).getIdShort(), sourceAASEnv.getConceptDescriptions().get(1).getIdShort());
        assertEquals(deserializedAASEnv.getConceptDescriptions().get(2).getIdShort(), sourceAASEnv.getConceptDescriptions().get(2).getIdShort());
        assertEquals(deserializedAASEnv.getConceptDescriptions().get(3).getIdShort(), sourceAASEnv.getConceptDescriptions().get(3).getIdShort());
        assertEquals(deserializedAASEnv.getConceptDescriptions().get(4).getIdShort(), sourceAASEnv.getConceptDescriptions().get(4).getIdShort());

        for(int i = 0; i<deserializedAASEnv.getConceptDescriptions().size(); i++){
            ConceptDescription des_conceptDescription = deserializedAASEnv.getConceptDescriptions().get(i);
            ConceptDescription src_conceptDescription = sourceAASEnv.getConceptDescriptions().get(i);

            logger.info("Check " + des_conceptDescription.getIdShort());
            //TODO: Add dataSpecification in DemoAAS_SimpleExample and fix in JSON example?
            assertEquals(des_conceptDescription, src_conceptDescription);


        }
    }

    @Test
    public void deserializeEnvironmentAndCheckAAS() throws JsonProcessingException, IOException {

        assertTrue(deserializedAASEnv.getAssetAdministrationShells().size() == sourceAASEnv.getAssetAdministrationShells().size());

        for(int i = 0; i<deserializedAASEnv.getAssetAdministrationShells().size(); i++){
            AssetAdministrationShell des_assetAdministrationShell = deserializedAASEnv.getAssetAdministrationShells().get(i);
            AssetAdministrationShell src_assetAdministrationShell = sourceAASEnv.getAssetAdministrationShells().get(i);

            logger.info("Check " + des_assetAdministrationShell.getIdShort());
            //TODO: Add dataSpecification in DemoAAS_SimpleExample
            assertEquals(deserializedAASEnv, src_assetAdministrationShell);


        }
    }


}
