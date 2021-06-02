package io.adminshell.aas.v3.dataformat.json.custommixins;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.adminshell.aas.v3.model.Reference;

//@JsonSubTypes({ @JsonSubTypes.Type(value = Submodel.class), @JsonSubTypes.Type(value = AdministrativeInformation.class),
//		@JsonSubTypes.Type(value = Asset.class), @JsonSubTypes.Type(value = AssetAdministrationShell.class),
//		@JsonSubTypes.Type(value = View.class), @JsonSubTypes.Type(value = ConceptDescription.class),
//		@JsonSubTypes.Type(value = SubmodelElement.class) })
public interface HasDataSpecificationMixin {

    @JsonProperty("dataSpecification")
    public List<Reference> getDataSpecifications();

    @JsonProperty("dataSpecification")
    public void setDataSpecifications(List<Reference> dataSpecifications);
}
