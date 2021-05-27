package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class LangStringMixin {

    @JsonProperty("text")
    private String value;
}
