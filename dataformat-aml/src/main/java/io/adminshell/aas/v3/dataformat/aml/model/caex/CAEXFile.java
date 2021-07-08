package io.adminshell.aas.v3.dataformat.aml.model.caex;

public class CAEXFile {

    public CAEXFile() {
    }

    public CAEXFile(String xmlns, String xsi, String schemaVersion, AdditionalInformation additionalInformation, InstanceHierarchy instanceHierarchy) {
        this.xmlns = xmlns;
        this.xsi = xsi;
        this.schemaVersion = schemaVersion;
        this.additionalInformation = additionalInformation;
        this.instanceHierarchy = instanceHierarchy;
    }

    private String xmlns;

    private String xsi;

    private String fileName;

    private String schemaVersion;

    private AdditionalInformation additionalInformation;

    private InstanceHierarchy instanceHierarchy;

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

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public AdditionalInformation getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(AdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public InstanceHierarchy getInstanceHierarchy() {
        return instanceHierarchy;
    }

    public void setInstanceHierarchy(InstanceHierarchy instanceHierarchy) {
        this.instanceHierarchy = instanceHierarchy;
    }
}
