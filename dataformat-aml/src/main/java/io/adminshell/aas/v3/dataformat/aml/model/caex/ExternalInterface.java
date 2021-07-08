package io.adminshell.aas.v3.dataformat.aml.model.caex;

import java.util.List;
import java.util.UUID;

public class ExternalInterface {

    private String id;

    public ExternalInterface(String id, String name, String refBaseClassPath, List<Attribute> attributes) {
        this.id = id;
        this.name = name;
        this.refBaseClassPath = refBaseClassPath;
        this.attributes = attributes;
    }

    private String name;

    private String refBaseClassPath;

    private List<Attribute> attributes;

    public ExternalInterface() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRefBaseClassPath() {
        return refBaseClassPath;
    }

    public void setRefBaseClassPath(String refBaseClassPath) {
        this.refBaseClassPath = refBaseClassPath;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
