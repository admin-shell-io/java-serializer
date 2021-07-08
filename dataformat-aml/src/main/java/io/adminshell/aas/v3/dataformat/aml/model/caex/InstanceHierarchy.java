package io.adminshell.aas.v3.dataformat.aml.model.caex;

import java.util.List;

public class InstanceHierarchy {

    public InstanceHierarchy() {
    }

    public InstanceHierarchy(String name, String version, List<InternalElement> internalElements) {
        this.name = name;
        this.version = version;
        this.internalElements = internalElements;
    }

    private String name;

    private String version;

    private List<InternalElement> internalElements;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<InternalElement> getInternalElements() {
        return internalElements;
    }

    public void setInternalElements(List<InternalElement> internalElements) {
        this.internalElements = internalElements;
    }
}
