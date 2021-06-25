package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.AssetKind;

import io.adminshell.aas.v3.model.File;
import io.adminshell.aas.v3.model.Submodel;

public interface AssetInformationMixin {

    @JsonProperty("thumbnail")
    public void setDefaultThumbnail(File value);

    @JsonProperty("thumbnail")
    public File getDefaultThumbnail();

    @JsonProperty("billOfMaterial")
    public List<Submodel> getBillOfMaterials();

    @JsonProperty("billOfMaterial")
    public void setBillOfMaterials(List<Submodel> billOfMaterials);

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public AssetKind getAssetKind();
}
