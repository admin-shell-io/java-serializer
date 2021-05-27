package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.fraunhofer.iais.eis.util.LangString;
import java.util.List;

public interface DataSpecificationIEC61360Mixin {

    @JsonProperty("shortName")
    public void setShortNames(List<LangString> values);

    @JsonProperty("preferredName")
    public void setPreferredNames(List<LangString> values);

    @JsonProperty("definition")
    public void setDefinitions(List<LangString> values);
}
