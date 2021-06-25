package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.AssetInformation;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.View;

@JsonPropertyOrder({ "extensions", "idShort", "displayNames", "category", "descriptions", "administration",
	"identification", "dataSpecifications", "security", "derivedFrom", "submodels",
	"assetInformation", "views" })
public interface AssetAdministrationShellMixin {
	
	@JacksonXmlProperty(localName = "aas:derivedFrom")
	public Reference getDerivedFrom();

    @JacksonXmlProperty(localName = "aas:submodelRef")
    @JacksonXmlElementWrapper(localName = "aas:submodelRefs")
    public List<Reference> getSubmodels();
    
    @JacksonXmlProperty(localName = "aas:view")
    @JacksonXmlElementWrapper(localName = "aas:views")
    public List<View> getViews();

    @JacksonXmlProperty(localName = "aas:assetInformation")
    public AssetInformation getAssetInformation();
}
