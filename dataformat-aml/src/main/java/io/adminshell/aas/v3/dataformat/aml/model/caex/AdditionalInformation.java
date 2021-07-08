package io.adminshell.aas.v3.dataformat.aml.model.caex;

public class AdditionalInformation {

    private String automationMLVersion;

    public AdditionalInformation() {
    }

    public AdditionalInformation(String automationMLVersion) {
        this.automationMLVersion = automationMLVersion;
    }

    public String getAutomationMLVersion() {
        return automationMLVersion;
    }

    public void setAutomationMLVersion(String automationMLVersion) {
        this.automationMLVersion = automationMLVersion;
    }
}
