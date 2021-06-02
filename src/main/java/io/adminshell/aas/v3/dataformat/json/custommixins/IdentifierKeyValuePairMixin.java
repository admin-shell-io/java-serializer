package io.adminshell.aas.v3.dataformat.json.custommixins;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.adminshell.aas.v3.model.Reference;

public interface IdentifierKeyValuePairMixin {

    @JsonProperty("subjectId")
    public void setExternalSubjectId(Reference value);

    @JsonProperty("subjectId")
    public Reference getExternalSubjectId();
}
