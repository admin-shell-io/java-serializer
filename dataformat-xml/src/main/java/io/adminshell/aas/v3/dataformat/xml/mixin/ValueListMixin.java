package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.model.ValueReferencePair;

public interface ValueListMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.IEC61360_URI, localName = "valueReferencePair")
    List<ValueReferencePair> getValueReferencePairTypes();
}
