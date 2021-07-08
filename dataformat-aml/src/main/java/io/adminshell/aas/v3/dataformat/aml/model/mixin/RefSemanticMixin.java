package io.adminshell.aas.v3.dataformat.aml.model.mixin;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class RefSemanticMixin {

    public RefSemanticMixin(String correspondingAttributePath) {
        this.correspondingAttributePath = correspondingAttributePath;
    }

    public RefSemanticMixin() {
    }

    @JacksonXmlProperty(localName = "CorrespondingAttributePath", isAttribute = true)
    private String correspondingAttributePath;

    public String getCorrespondingAttributePath() {
        return correspondingAttributePath;
    }

    public void setCorrespondingAttributePath(String correspondingAttributePath) {
        this.correspondingAttributePath = correspondingAttributePath;
    }
}
