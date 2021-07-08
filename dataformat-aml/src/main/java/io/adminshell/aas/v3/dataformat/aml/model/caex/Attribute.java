package io.adminshell.aas.v3.dataformat.aml.model.caex;

import java.util.List;

public class Attribute {

    public Attribute(String name, String attributeDataType, String description, String value, RefSemantic refSemantic, List<Attribute> attributes) {
        this.name = name;
        this.attributeDataType = attributeDataType;
        this.description = description;
        this.value = value;
        this.refSemantic = refSemantic;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttributeDataType() {
        return attributeDataType;
    }

    public void setAttributeDataType(String attributeDataType) {
        this.attributeDataType = attributeDataType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RefSemantic getRefSemantic() {
        return refSemantic;
    }

    public void setRefSemantic(RefSemantic refSemantic) {
        this.refSemantic = refSemantic;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    private String name;

    private String attributeDataType;

    private String description;

    private String value;

    private RefSemantic refSemantic;

    private List<Attribute> attributes;
}
