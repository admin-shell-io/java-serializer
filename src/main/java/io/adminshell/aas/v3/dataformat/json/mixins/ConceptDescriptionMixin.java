package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.DataSpecification;
import io.adminshell.aas.v3.model.Reference;
import java.util.List;

public interface ConceptDescriptionMixin {

    @JsonProperty("isCaseOf")
    public List<Reference> getIsCaseOfs();

    @JsonProperty("isCaseOf")
    public void setIsCaseOfs(List<Reference> isCaseOfs);
//
//    @JsonProperty("embeddedDataSpecifications")
//    public List<DataSpecification> getEmbeddedDataSpecifications();
//
//    @JsonProperty("embeddedDataSpecifications")
//    @JsonDeserialize(using = DataSpecificationDeserializer.class)

    public void setEmbeddedDataSpecifications(List<DataSpecification> embeddedDataSpecifications);
}
