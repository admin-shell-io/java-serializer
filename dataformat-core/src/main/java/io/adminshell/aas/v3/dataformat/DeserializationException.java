package io.adminshell.aas.v3.dataformat;

public class DeserializationException extends Exception {

    public DeserializationException(String msg) {
        super(msg);
    }

    public DeserializationException(String msg, Throwable err) {
        super(msg, err);
    }

}
