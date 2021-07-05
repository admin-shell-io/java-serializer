package io.adminshell.aas.v3.dataformat.xml.mixin;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.model.ModelingKind;

public interface HasKindMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "kind")
    public ModelingKind getKind();
}
