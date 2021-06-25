package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.Reference;
import java.util.List;

public interface BlobCertificateMixin {

    @JsonProperty("containedExtension")
    public List<Reference> getContainedExtensions();

    @JsonProperty("containedExtension")
    public void setContainedExtensions(List<Reference> containedExtensions);
}
