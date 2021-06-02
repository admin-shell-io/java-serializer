package io.adminshell.aas.v3.dataformat.json.custommixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.adminshell.aas.v3.model.Reference;

@JsonTypeName("ConceptDescription")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
public interface ConceptDescriptionMixin {

	@JsonProperty("isCaseOf")
	public List<Reference> getIsCaseOfs();

	@JsonProperty("isCaseOf")
	public void setIsCaseOfs(List<Reference> isCaseOfs);
}
