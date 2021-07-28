package io.adminshell.aas.v3.model.validator;

import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShell;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShaclValidatorTest {

    Logger logger = LoggerFactory.getLogger(getClass());


    @Test
    public void validationTest() throws ValidationException {
        logger.info("Starting initialization of validator");
        ShaclValidator validator = ShaclValidator.getInstance();
        logger.info("Initialization complete. Building AAS Java object");

        AssetAdministrationShell aas = new DefaultAssetAdministrationShell.Builder()
                .description(new LangString("TestDescription"))
                .build();

        logger.info("Done. Starting validation.");
        validator.validate(aas);
        logger.info("Validation complete");
    }
}
