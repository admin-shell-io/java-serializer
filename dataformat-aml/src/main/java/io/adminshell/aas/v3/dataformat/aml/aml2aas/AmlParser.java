package io.adminshell.aas.v3.dataformat.aml.aml2aas;

import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXFile;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXObject;

public class AmlParser {

    private final CAEXFile content;
    private CAEXObject current;

    public AmlParser(CAEXFile content) {
        this.content = content;
    }

    public CAEXFile getContent() {
        return content;
    }

    public CAEXObject getCurrent() {
        return current;
    }

    public void setCurrent(CAEXObject current) {
        this.current = current;
    }

}
