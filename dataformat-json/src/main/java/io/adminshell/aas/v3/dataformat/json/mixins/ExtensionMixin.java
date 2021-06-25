package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonInclude;

public interface ExtensionMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public String getName();
}
