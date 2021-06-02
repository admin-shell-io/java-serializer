package io.adminshell.aas.v3.dataformat.json.custommixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.adminshell.aas.v3.model.Extension;

@JsonTypeName("HasExtensions")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
public interface HasExtensionsMixin {

	@JsonProperty("extension")
	public List<Extension> getExtensions();

	@JsonProperty("extension")
	public void setExtensions(List<Extension> extensions);
}
