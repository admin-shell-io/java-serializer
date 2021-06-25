package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.Reference;
import java.util.List;

public interface ObjectAttributesMixin {

    @JsonProperty("objectAttribute")
    public List<Reference> getObjectAttributes();

    @JsonProperty("objectAttribute")
    public void setObjectAttributes(List<Reference> objectAttributes);
}
