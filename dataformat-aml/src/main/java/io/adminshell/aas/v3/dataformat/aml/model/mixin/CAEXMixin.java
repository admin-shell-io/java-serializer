package io.adminshell.aas.v3.dataformat.aml.model.mixin;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CAEXMixin {

    public CAEXMixin(String xmlns, String xsi, String fileName, String schemaVersion, AdditionalInformationMixin additionalInformation, InstanceHierarchyMixin instanceHierarchy) {
        this.xmlns = xmlns;
        this.xsi = xsi;
        this.fileName = fileName;
        this.schemaVersion = schemaVersion;
        this.additionalInformation = additionalInformation;
        this.instanceHierarchy = instanceHierarchy;
    }

    public CAEXMixin() {
    }

    @JacksonXmlProperty(localName = "xmlns:xsi", isAttribute = true)
    private String xmlns;

    @JacksonXmlProperty(localName = "xsi:noNamespaceSchemaLocation", isAttribute = true)
    private String xsi;

    @JacksonXmlProperty(localName = "FileName", isAttribute = true)
    private String fileName;

    @JacksonXmlProperty(localName = "SchemaVersion", isAttribute = true)
    private String schemaVersion;

    @JacksonXmlProperty(localName = "AdditionalInformation")
    private AdditionalInformationMixin additionalInformation;

    @JacksonXmlProperty(localName = "InstanceHierarchy")
    private InstanceHierarchyMixin instanceHierarchy;

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getXsi() {
        return xsi;
    }

    public void setXsi(String xsi) {
        this.xsi = xsi;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public AdditionalInformationMixin getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(AdditionalInformationMixin additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public InstanceHierarchyMixin getInstanceHierarchy() {
        return instanceHierarchy;
    }

    public void setInstanceHierarchy(InstanceHierarchyMixin instanceHierarchy) {
        this.instanceHierarchy = instanceHierarchy;
    }
}
