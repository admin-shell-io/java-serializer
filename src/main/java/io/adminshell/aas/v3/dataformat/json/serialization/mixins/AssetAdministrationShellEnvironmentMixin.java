package io.adminshell.aas.v3.dataformat.json.serialization.mixins;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.fraunhofer.iais.eis.Asset;
import de.fraunhofer.iais.eis.AssetAdministrationShell;
import de.fraunhofer.iais.eis.ConceptDescription;
import de.fraunhofer.iais.eis.Submodel;
import java.util.List;
import java.util.Set;

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
