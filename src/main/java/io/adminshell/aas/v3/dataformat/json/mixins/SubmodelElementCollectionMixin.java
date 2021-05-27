package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.fraunhofer.iais.eis.SubmodelElement;
import java.util.Collection;

public interface SubmodelElementCollectionMixin {

    @JsonProperty("value")
    public void setValues(Collection<SubmodelElement> values);
}
