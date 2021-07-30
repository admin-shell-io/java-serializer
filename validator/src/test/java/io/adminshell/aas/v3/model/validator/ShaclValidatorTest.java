/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.adminshell.aas.v3.model.validator;

import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetKind;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShell;
import io.adminshell.aas.v3.model.impl.DefaultAssetInformation;
import org.junit.Assert;
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
        ValidationException exception = null;
        try {
            validator.validate(aas);
        }
        catch (ValidationException e)
        {
            exception = e;
        }
        logger.info("Validation complete");
        Assert.assertNotNull(exception);
        Assert.assertTrue(exception.getMessage().contains("Exactly one <id>idShort</id> is required"));

        aas.setIdShort("test");

        aas.setAssetInformation(new DefaultAssetInformation.Builder()
                .assetKind(AssetKind.INSTANCE)
                .build());
        try {
            validator.validate(aas);
        }
        catch (ValidationException e)
        {
            e.printStackTrace();
            //TODO: The AssetKind.INSTANCE is not yet recognized properly. Either the serialization is not correct, or the shape has an error
        }
    }
}
