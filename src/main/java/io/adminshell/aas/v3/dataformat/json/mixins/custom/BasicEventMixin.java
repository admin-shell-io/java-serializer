package io.adminshell.aas.v3.dataformat.json.mixins.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.adminshell.aas.v3.model.Reference;

public interface BasicEventMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public Reference getObserved();
}
