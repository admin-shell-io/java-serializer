package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.AasXmlNamespaceContext;
import io.adminshell.aas.v3.model.AssetInformation;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.View;

@JsonPropertyOrder({"extensions", "idShort", "displayNames", "category", "descriptions", "administration",
    "identification", "dataSpecifications", "security", "derivedFrom", "submodels", "assetInformation", "views"})
public interface AssetAdministrationShellMixin {

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "derivedFrom")
    public Reference getDerivedFrom();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "submodelRef")
    @JacksonXmlElementWrapper(namespace = AasXmlNamespaceContext.AAS_URI, localName = "submodelRefs")
    public List<Reference> getSubmodels();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "view")
    @JacksonXmlElementWrapper(namespace = AasXmlNamespaceContext.AAS_URI, localName = "views")
    public List<View> getViews();

    @JacksonXmlProperty(namespace = AasXmlNamespaceContext.AAS_URI, localName = "assetInformation")
    public AssetInformation getAssetInformation();
}
