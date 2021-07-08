package io.adminshell.aas.v3.dataformat.aml.model.mixin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttributeMixin {

    public AttributeMixin(String name, String attributeDataType, String description, String value, RefSemantic refSemantic, List<AttributeMixin> attributes) {
        this.name = name;
        this.attributeDataType = attributeDataType;
        this.description = description;
        this.value = value;
        this.refSemantic = refSemantic;
        this.attributes = attributes;
    }

    public AttributeMixin() {
    }

    @JacksonXmlProperty(localName = "Name", isAttribute = true)
    private String name;

    @JacksonXmlProperty(localName = "AttributeDataType", isAttribute = true)
    private String attributeDataType;

    @JacksonXmlProperty(localName = "Description")
    private String description;

    @JacksonXmlProperty(localName = "Value")
    private String value;

    @JacksonXmlProperty(localName = "RefSemantic")
    private RefSemantic refSemantic;

    @JacksonXmlProperty(localName = "Attribute")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<AttributeMixin> attributes;

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

    public List<AttributeMixin> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeMixin> attributes) {
        this.attributes = attributes;
    }
}
