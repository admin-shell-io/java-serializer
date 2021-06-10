package io.adminshell.aas.v3.dataformat.json.mixins.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.LevelType;
import java.util.List;

public interface DataSpecificationIEC61360Mixin {

    @JsonProperty("definition")
    public List<LangString> getDefinitions();

    @JsonProperty("definition")
    public void setDefinitions(List<LangString> definitions);

    @JsonProperty("levelType")
    public List<LevelType> getLevelTypes();

    @JsonProperty("levelType")
    public void setLevelTypes(List<LevelType> levelTypes);

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("preferredName")
    public List<LangString> getPreferredNames();

    @JsonProperty("preferredName")
    public void setPreferredNames(List<LangString> preferredNames);

    @JsonProperty("shortName")
    public List<LangString> getShortNames();

    @JsonProperty("shortName")
    public void setShortNames(List<LangString> shortNames);
}
