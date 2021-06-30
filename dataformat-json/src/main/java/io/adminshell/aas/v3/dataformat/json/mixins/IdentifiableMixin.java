package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.adminshell.aas.v3.model.Identifier;

public interface IdentifiableMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public Identifier getIdentification();
}
