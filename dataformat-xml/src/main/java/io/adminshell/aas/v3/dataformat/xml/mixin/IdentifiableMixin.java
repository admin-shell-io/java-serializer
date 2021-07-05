package io.adminshell.aas.v3.dataformat.xml.mixin;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.model.AdministrativeInformation;
import io.adminshell.aas.v3.model.Identifier;

public interface IdentifiableMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "administration")
    public AdministrativeInformation getAdministration();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "identification")
    public Identifier getIdentification();
}
