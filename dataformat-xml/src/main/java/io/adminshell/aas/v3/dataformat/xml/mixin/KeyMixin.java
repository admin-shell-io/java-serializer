package io.adminshell.aas.v3.dataformat.xml.mixin;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.KeyType;

public interface KeyMixin {

    @JacksonXmlText
    public String getValue();

    @JacksonXmlProperty(localName = "idType", isAttribute = true)
    public KeyType getIdType();

    @JacksonXmlProperty(localName = "type", isAttribute = true)
    public KeyElements getType();
}
