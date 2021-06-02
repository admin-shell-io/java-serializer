package io.adminshell.aas.v3.dataformat.json.custommixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.adminshell.aas.v3.model.Reference;

public interface ConceptDescriptionMixin {

    @JsonProperty("isCaseOf")
    public List<Reference> getIsCaseOfs();

    @JsonProperty("isCaseOf")
    public void setIsCaseOfs(List<Reference> isCaseOfs);
}
