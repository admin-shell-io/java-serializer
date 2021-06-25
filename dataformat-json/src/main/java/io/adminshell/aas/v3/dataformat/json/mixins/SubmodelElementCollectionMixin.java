package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.SubmodelElement;
import java.util.Collection;

public interface SubmodelElementCollectionMixin {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public boolean getOrdered();

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    public boolean getAllowDuplicates();

    @JsonProperty("value")
    public Collection<SubmodelElement> getValues();

    @JsonProperty("value")
    public void setValues(Collection<SubmodelElement> values);
}
