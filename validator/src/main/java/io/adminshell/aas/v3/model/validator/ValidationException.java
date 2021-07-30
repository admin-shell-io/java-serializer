package io.adminshell.aas.v3.model.validator;

public class ValidationException extends Exception {

    public ValidationException(String msg) {
        super(msg);
    }

    public ValidationException(String msg, Throwable err) {
        super(msg, err);
    }

}
