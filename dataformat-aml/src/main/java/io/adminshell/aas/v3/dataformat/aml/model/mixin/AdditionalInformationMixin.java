package io.adminshell.aas.v3.dataformat.aml.model.mixin;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AdditionalInformationMixin {

    public AdditionalInformationMixin() {
    }

    public AdditionalInformationMixin(String automationMLVersion) {
        this.automationMLVersion = automationMLVersion;
    }

    @JacksonXmlProperty(localName = "AutomationMLVersion", isAttribute = true)
    private String automationMLVersion;

    public String getAutomationMLVersion() {
        return automationMLVersion;
    }

    public void setAutomationMLVersion(String automationMLVersion) {
        this.automationMLVersion = automationMLVersion;
    }

}
