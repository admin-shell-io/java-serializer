package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import de.fraunhofer.iais.eis.Asset;
import de.fraunhofer.iais.eis.AssetAdministrationShell;
import de.fraunhofer.iais.eis.ConceptDescription;
import de.fraunhofer.iais.eis.Submodel;
import java.util.List;
import java.util.Set;

@JsonTypeName("AssetAdministrationShellEnvironment")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
public interface AssetAdministrationShellEnvironmentMixin {

    // TODO maybe include always, would require default classes to create empty list in constructor
    // @JsonInclude(JsonInclude.Include.ALWAYS)
    public Set<AssetAdministrationShell> getAssetAdministrationShells();

    // TODO maybe include always, would require default classes to create empty list in constructor
    // @JsonInclude(JsonInclude.Include.ALWAYS)
    public List<Submodel> getSubmodels();

    // TODO maybe include always, would require default classes to create empty list in constructor
    // @JsonInclude(JsonInclude.Include.ALWAYS)
    public List<ConceptDescription> getConceptDescriptions();

    // TODO maybe include always, would require default classes to create empty list in constructor
    // @JsonInclude(JsonInclude.Include.ALWAYS)
    public List<Asset> getAssets();
}
