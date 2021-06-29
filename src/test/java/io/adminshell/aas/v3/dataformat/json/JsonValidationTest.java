package io.adminshell.aas.v3.dataformat.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class JsonValidationTest {

    private static JsonSchemaValidator validator;

    @BeforeClass
    public static void prepareValidator() throws IOException {
        validator = new JsonSchemaValidator();
    }

    @Test
    @Parameters({"src/test/resources/jsonExample.json", "src/test/resources/test_demo_full_example.json",
        "src/test/resources/MotorAAS.json", "src/test/resources/MotorAAS_reduced.json"})
    public void validateValidJson(String file) throws IOException {
        String serializedEnvironment = new String(Files.readAllBytes(Paths.get(file)));
        Set<String> errors = validator.validateSchema(serializedEnvironment);
        System.out.println("Validating: " + file);
        assertTrue(errors.isEmpty());
    }

    @Test
    @Parameters({"src/test/resources/invalidJsonExample.json"})
    public void validateInvalidJson(String file) throws IOException {
        String serializedEnvironment = new String(Files.readAllBytes(Paths.get(file)));
        Set<String> errors = validator.validateSchema(serializedEnvironment);
        System.out.println("Validating: " + file);
        for (String s : errors) {
            System.out.println(s);
        }
        assertEquals(2, errors.size());
    }
}
