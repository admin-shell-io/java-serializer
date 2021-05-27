package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.fraunhofer.iais.eis.Reference;

public interface IdentifierKeyValuePairMixin {

    @JsonProperty("subjectId")
    public void setExternalSubjectId(Reference value);
}
