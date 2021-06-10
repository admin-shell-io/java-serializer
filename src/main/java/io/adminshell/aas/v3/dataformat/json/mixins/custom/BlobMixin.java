package io.adminshell.aas.v3.dataformat.json.mixins.custom;

import com.fasterxml.jackson.annotation.JsonInclude;

public interface BlobMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public String getMimeType();
}
