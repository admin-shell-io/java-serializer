package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.LangString;
import java.util.List;

public interface MultiLanguagePropertyMixin {

    @JsonProperty("value")
    public List<LangString> getValues();

    @JsonProperty("value")
    public void setValues(List<LangString> values);
}
