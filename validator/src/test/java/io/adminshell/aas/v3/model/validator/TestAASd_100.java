package io.adminshell.aas.v3.model.validator;

import io.adminshell.aas.v3.model.Referable;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the following constraint:
 * <p>
 * <i> An attribute with data type "string" is not allowed to be empty. </i>
 * </p>
 *
 * @author bader, chang
 *
 */

public class TestAASd_100 {
    @Test
    public void idShortWithNotAllowedCharacters() throws ValidationException {
        Referable wrongReferable = ConstraintTestHelper.createSubmodel(new ArrayList<>());


        wrongReferable.setIdShort("");
        try {
            ShaclValidator.getInstance().validate(wrongReferable);
            fail();
        } catch (ValidationException e) {
            assertTrue(e.getMessage().endsWith("starting mandatory with a letter. I.e. [a-zA-Z][a-zA-Z0-9_]+."));
        }


    }

    @Test
    public void idShortWithAllowedCharacters() throws ValidationException {
        Referable referable = ConstraintTestHelper.createSubmodel(new ArrayList<>());
        referable.setIdShort("id_Short");
        ShaclValidator.getInstance().validate(referable);
    }
}
