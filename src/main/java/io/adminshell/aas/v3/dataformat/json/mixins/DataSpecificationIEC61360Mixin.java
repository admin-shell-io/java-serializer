package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import de.fraunhofer.iais.eis.util.LangString;
import java.util.List;

@JsonTypeName("DataSpecificationIEC61360")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
public interface DataSpecificationIEC61360Mixin {

    @JsonProperty("shortName")
    public void setShortNames(List<LangString> values);

    @JsonProperty("shortName")
    public List<LangString> getShortNames();

    @JsonProperty("preferredName")
    public void setPreferredNames(List<LangString> values);

    @JsonProperty("preferredName")
    public List<LangString> getPreferredNames();

    @JsonProperty("definition")
    public void setDefinitions(List<LangString> values);

    @JsonProperty("definition")
    public List<LangString> getDefinitions();
}
