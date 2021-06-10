package io.adminshell.aas.v3.dataformat.json.mixins.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.IdentifierType;

public interface IdentifierMixin {

    @JsonProperty("id")
    public void setIdentifier(String identifier);

    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    public String getIdentifier();

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public IdentifierType getIdType();
}
