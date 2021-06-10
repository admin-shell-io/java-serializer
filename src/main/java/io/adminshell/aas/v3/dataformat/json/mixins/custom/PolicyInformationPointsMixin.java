package io.adminshell.aas.v3.dataformat.json.mixins.custom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.Submodel;
import java.util.List;

public interface PolicyInformationPointsMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    @JsonProperty("externalInformationPoint")
    public boolean getExternalInformationPoints();

    @JsonProperty("externalInformationPoint")
    public void setExternalInformationPoints(boolean externalInformationPoints);

    @JsonProperty("internalInformationPoint")
    public List<Submodel> getInternalInformationPoints();

    @JsonProperty("internalInformationPoint")
    public void setInternalInformationPoints(List<Submodel> internalInformationPoints);
}
