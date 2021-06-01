package io.adminshell.aas.v3.dataformat.json;

public class SerializationException extends Exception {

    public SerializationException(String msg) {
        super(msg);
    }

    public SerializationException(String msg, Throwable err) {
        super(msg, err);
    }
}
