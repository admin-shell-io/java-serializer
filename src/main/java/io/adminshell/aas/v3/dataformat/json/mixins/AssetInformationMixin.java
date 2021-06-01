package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import de.fraunhofer.iais.eis.File;
import de.fraunhofer.iais.eis.Submodel;
import java.util.List;

@JsonTypeName("AssetInformation")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
public interface AssetInformationMixin {

    @JsonProperty("thumbnail")
    public void setDefaultThumbnail(File value);

    @JsonProperty("thumbnail")
    public File getDefaultThumbnail();

    @JsonProperty("billOfMaterial")
    public List<Submodel> getBillOfMaterials();

    @JsonProperty("billOfMaterial")
    public void setBillOfMaterials(List<Submodel> billOfMaterials);
}
