package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface LangStringMixin {

    @JsonProperty("text")
    public String getValue();

    @JsonProperty("text")
    public void setValue(String value);
}
