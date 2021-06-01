package io.adminshell.aas.v3.dataformat.json;

public class DeserializationException extends Exception {

    public DeserializationException(String msg) {
        super(msg);
    }

    public DeserializationException(String msg, Throwable err) {
        super(msg, err);
    }
}
