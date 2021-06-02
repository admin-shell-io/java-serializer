package io.adminshell.aas.v3.dataformat.json.custommixins;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.adminshell.aas.v3.model.Asset;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.Submodel;

@JsonTypeName("AssetAdministrationShellEnvironment")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
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
