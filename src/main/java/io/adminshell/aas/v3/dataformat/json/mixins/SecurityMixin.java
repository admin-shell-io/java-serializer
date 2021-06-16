package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.AccessControlPolicyPoints;
import io.adminshell.aas.v3.model.Certificate;
import io.adminshell.aas.v3.model.Reference;
import java.util.List;

public interface SecurityMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public AccessControlPolicyPoints getAccessControlPolicyPoints();

    @JsonProperty("certificate")
    public List<Certificate> getCertificates();

    @JsonProperty("certificate")
    public void setCertificates(List<Certificate> certificates);

    @JsonProperty("requiredCertificateExtension")
    public List<Reference> getRequiredCertificateExtensions();

    @JsonProperty("requiredCertificateExtension")
    public void setRequiredCertificateExtensions(List<Reference> requiredCertificateExtensions);
}
