package io.adminshell.aas.v3.dataformat.aml.model.caex;

public class RefSemantic {

    public RefSemantic(String correspondingAttributePath) {
        this.correspondingAttributePath = correspondingAttributePath;
    }

    private String correspondingAttributePath;

    public String getCorrespondingAttributePath() {
        return correspondingAttributePath;
    }

    public void setCorrespondingAttributePath(String correspondingAttributePath) {
        this.correspondingAttributePath = correspondingAttributePath;
    }
}
