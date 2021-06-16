package io.adminshell.aas.v3.dataformat.json.mixins;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.adminshell.aas.v3.model.Asset;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.Submodel;

public interface AssetAdministrationShellEnvironmentMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public Set<AssetAdministrationShell> getAssetAdministrationShells();

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public List<Submodel> getSubmodels();

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public List<ConceptDescription> getConceptDescriptions();

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public List<Asset> getAssets();
}
