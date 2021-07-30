package io.adminshell.aas.v3.model.validator;

import java.io.IOException;

public interface Validator {

    void validate(Object obj) throws ValidationException;

    void initialize();

}
