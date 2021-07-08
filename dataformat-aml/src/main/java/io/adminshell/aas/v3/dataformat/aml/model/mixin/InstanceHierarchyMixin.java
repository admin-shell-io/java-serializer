package io.adminshell.aas.v3.dataformat.aml.model.mixin;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class InstanceHierarchyMixin {

    public InstanceHierarchyMixin(String name, String version, List<InternalElementMixin> internalElements) {
        this.name = name;
        this.version = version;
        this.internalElements = internalElements;
    }

    public InstanceHierarchyMixin() {
    }

    @JacksonXmlProperty(localName = "Name", isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "Version")
    private String version;

    @JacksonXmlProperty(localName = "InternalElement")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<InternalElementMixin> internalElements;

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

    public List<InternalElementMixin> getInternalElements() {
        return internalElements;
    }

    public void setInternalElements(List<InternalElementMixin> internalElements) {
        this.internalElements = internalElements;
    }
}
