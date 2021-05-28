package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import de.fraunhofer.iais.eis.Reference;

@JsonTypeName("IdentifierKeyValuePair")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
public interface IdentifierKeyValuePairMixin {

    @JsonProperty("subjectId")
    public void setExternalSubjectId(Reference value);

    @JsonProperty("subjectId")
    public Reference getExternalSubjectId();
}
