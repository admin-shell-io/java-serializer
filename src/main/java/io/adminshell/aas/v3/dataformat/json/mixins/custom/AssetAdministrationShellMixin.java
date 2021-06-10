package io.adminshell.aas.v3.dataformat.json.mixins.custom;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.adminshell.aas.v3.model.AssetInformation;

public interface AssetAdministrationShellMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public AssetInformation getAssetInformation();
}
