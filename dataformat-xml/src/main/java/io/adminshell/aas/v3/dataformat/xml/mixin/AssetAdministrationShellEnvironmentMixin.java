package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.adminshell.aas.v3.model.Asset;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.Submodel;

@JacksonXmlRootElement(localName = "aas:aasenv")
@JsonPropertyOrder({ "assetAdministrationShells", "assets", "conceptDescriptions", "submodels" })
public interface AssetAdministrationShellEnvironmentMixin {
	
	@JacksonXmlProperty(localName = "aas:assetAdministrationShell")
    @JacksonXmlElementWrapper(localName = "aas:assetAdministrationShells")
	@JsonInclude(JsonInclude.Include.ALWAYS)
    public List<AssetAdministrationShell> getAssetAdministrationShells();

    @JacksonXmlProperty(localName = "aas:asset")
    @JacksonXmlElementWrapper(localName = "aas:assets")
    public List<Asset> getAssets();

	@JacksonXmlProperty(localName = "aas:conceptDescription")
    @JacksonXmlElementWrapper(localName = "aas:conceptDescriptions")
    public List<ConceptDescription> getConceptDescriptions();

    @JacksonXmlProperty(localName = "aas:submodel")
    @JacksonXmlElementWrapper(localName = "aas:submodels")
    public List<Submodel> getSubmodels();
}
