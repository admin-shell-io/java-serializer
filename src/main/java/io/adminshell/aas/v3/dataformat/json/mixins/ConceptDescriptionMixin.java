package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import de.fraunhofer.iais.eis.DataSpecification;
import de.fraunhofer.iais.eis.Reference;
import java.util.List;

@JsonTypeName("ConceptDescription")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
public interface ConceptDescriptionMixin {

    @JsonProperty("isCaseOf")
    public List<Reference> getIsCaseOfs();

    @JsonProperty("isCaseOf")
    public void setIsCaseOfs(List<Reference> isCaseOfs);
}
