package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonInclude;

public interface RangeMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public String getValueType();
}
