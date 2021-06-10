package io.adminshell.aas.v3.dataformat.json.mixins.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.adminshell.aas.v3.model.Reference;

public interface IdentifierKeyValuePairMixin {

    @JsonProperty("subjectId")
    public void setExternalSubjectId(Reference value);

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("subjectId")
    public Reference getExternalSubjectId();

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public String getKey();

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public String getValue();
}
