package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.AssetKind;
import io.adminshell.aas.v3.model.File;
import io.adminshell.aas.v3.model.IdentifierKeyValuePair;
import io.adminshell.aas.v3.model.Reference;

@JsonPropertyOrder({ "defaultThumbnail", "globalAssetId", "assetKind", "billOfMaterials", "specificAssetIds" })
public interface AssetInformationMixin {
	
    @JacksonXmlProperty(localName = "aas:assetKind")
    public AssetKind getAssetKind();
    
    @JacksonXmlProperty(localName = "aas:globalAssetId")
    public Reference getGlobalAssetId();
    
    @JacksonXmlProperty(localName = "aas:specificAssetId")
    @JacksonXmlElementWrapper(localName = "aas:specificAssetIds")
    public List<IdentifierKeyValuePair> getSpecificAssetIds();

    @JacksonXmlProperty(localName = "aas:billOfMaterials")
    public List<Reference> getBillOfMaterials();
    
    @JacksonXmlProperty(localName = "aas:defaultThumbNail")
    public File getDefaultThumbnail();
    
}
