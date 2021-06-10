package io.adminshell.aas.v3.dataformat.json.mixins.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface LangStringMixin {

    @JsonProperty("text")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    public String getValue();

    @JsonProperty("text")
    public void setValue(String value);

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public String getLanguage();
}
