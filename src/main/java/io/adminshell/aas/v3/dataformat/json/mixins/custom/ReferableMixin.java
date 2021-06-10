package io.adminshell.aas.v3.dataformat.json.mixins.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.LangString;
import java.util.List;
import java.util.Set;

public interface ReferableMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public Set<AssetAdministrationShell> getIdShort();

    @JsonProperty("description")
    public List<LangString> getDescriptions();

    @JsonProperty("description")
    public void setDescriptions(List<LangString> descriptions);

    @JsonProperty("displayName")
    public List<LangString> getDisplayNames();

    @JsonProperty("displayName")
    public void setDisplayNames(List<LangString> displayNames);
}
