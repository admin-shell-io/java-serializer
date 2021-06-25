package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.DataElement;
import java.util.List;

public interface AnnotatedRelationshipElementMixin {

    @JsonProperty("annotation")
    public List<DataElement> getAnnotations();

    @JsonProperty("annotation")
    public void setAnnotations(List<DataElement> annotations);
}
