package io.adminshell.aas.v3.dataformat.json.mixins.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.EntityType;
import io.adminshell.aas.v3.model.IdentifierKeyValuePair;

public interface EntityMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public EntityType getEntityType();

    @JsonProperty("specificAssetIds")
    public IdentifierKeyValuePair getExternalAssetId();

    @JsonProperty("specificAssetIds")
    public void setExternalAssetId(IdentifierKeyValuePair externalAssetId);
}
