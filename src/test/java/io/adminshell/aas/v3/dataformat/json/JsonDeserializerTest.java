package io.adminshell.aas.v3.dataformat.json;

import de.fraunhofer.iais.eis.Submodel;
import de.fraunhofer.iais.eis.SubmodelElement;
import de.fraunhofer.iais.eis.SubmodelElementCollection;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;

import de.fraunhofer.iais.eis.AssetAdministrationShellEnvironment;

import javax.validation.constraints.AssertTrue;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonDeserializerTest {

    private static Logger logger = LoggerFactory.getLogger(JsonDeserializerTest.class);

    private static JsonFileReader deserializer = new JsonFileReader();

    @Test
    public void deserializeEnvironment() throws JsonProcessingException, IOException {
        AssetAdministrationShellEnvironment aasEnvironment = deserializer.deserializeFromFile("src/test/resources/jsonExample.json");
        logger.info("successfully deserialized simple example");
    }

    @Test
    public void deserializeEnvironmentFull() throws JsonProcessingException, IOException {
        AssetAdministrationShellEnvironment aasEnvironment = deserializer.deserializeFromFile("src/test/resources/test_demo_full_example.json");
        logger.info("successfully deserialized full example");
    }

    @Test
    public void deserializeEnvironmentAndCheckSubmodels() throws JsonProcessingException, IOException {
        AssetAdministrationShellEnvironment deserializedAasEnvironment = deserializer.deserializeFromFile("src/test/resources/jsonExample.json");
        AssetAdministrationShellEnvironment sourceAasEnvironment = DemoAAS_SimpleExample.ENVIRONMENT;

        assertTrue(deserializedAasEnvironment.getSubmodels().size() == sourceAasEnvironment.getSubmodels().size());

        assertEquals(deserializedAasEnvironment.getSubmodels().get(0).getIdShort(), sourceAasEnvironment.getSubmodels().get(0).getIdShort());
        assertEquals(deserializedAasEnvironment.getSubmodels().get(1).getIdShort(), sourceAasEnvironment.getSubmodels().get(1).getIdShort());
        assertEquals(deserializedAasEnvironment.getSubmodels().get(2).getIdShort(), sourceAasEnvironment.getSubmodels().get(2).getIdShort());


        for(int i = 0; i<deserializedAasEnvironment.getSubmodels().size(); i++){
            Submodel submodel = deserializedAasEnvironment.getSubmodels().get(i);
            for(int y = 0; y<submodel.getSubmodelElements().size(); y++){
                SubmodelElement submodelElement = submodel.getSubmodelElements().get(y);
                assertEquals(submodelElement, sourceAasEnvironment.getSubmodels().get(i).getSubmodelElements().get(y));
                logger.info("SubmodelElement: " + submodelElement.getIdShort() + " == " + sourceAasEnvironment.getSubmodels().get(i).getSubmodelElements().get(y).getIdShort());
            }
        }
    }

}
