package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import de.fraunhofer.iais.eis.AdministrativeInformation;
import de.fraunhofer.iais.eis.Asset;
import de.fraunhofer.iais.eis.AssetAdministrationShell;
import de.fraunhofer.iais.eis.ConceptDescription;
import de.fraunhofer.iais.eis.Reference;
import de.fraunhofer.iais.eis.Submodel;
import de.fraunhofer.iais.eis.SubmodelElement;
import de.fraunhofer.iais.eis.View;
import java.util.List;

@JsonTypeName("HasDataSpecification")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Submodel.class),
    @JsonSubTypes.Type(value = AdministrativeInformation.class),
    @JsonSubTypes.Type(value = Asset.class),
    @JsonSubTypes.Type(value = AssetAdministrationShell.class),
    @JsonSubTypes.Type(value = View.class),
    @JsonSubTypes.Type(value = ConceptDescription.class),
    @JsonSubTypes.Type(value = SubmodelElement.class)
})
public interface HasDataSpecificationMixin {

    @JsonProperty("dataSpecification")
    public List<Reference> getDataSpecifications();

    @JsonProperty("dataSpecification")
    public void setDataSpecifications(List<Reference> dataSpecifications);
}
