package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import de.fraunhofer.iais.eis.SubmodelElement;
import java.util.Collection;

@JsonTypeName("SubmodelElementCollection")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
public interface SubmodelElementCollectionMixin {

    @JsonProperty("value")
    public void setValues(Collection<SubmodelElement> values);

    @JsonProperty("value")
    public Collection<SubmodelElement> getValues();
}
