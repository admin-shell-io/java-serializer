package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.fraunhofer.iais.eis.File;

public interface AssetInformationMixin {

    @JsonProperty("thumbnail")
    public void setDefaultThumbnail(File value);
}
