package io.adminshell.aas.v3.dataformat.json.mixins.custom;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.adminshell.aas.v3.model.Extension;

public interface HasExtensionsMixin {

    @JsonProperty("extension")
    public List<Extension> getExtensions();

    @JsonProperty("extension")
    public void setExtensions(List<Extension> extensions);
}
