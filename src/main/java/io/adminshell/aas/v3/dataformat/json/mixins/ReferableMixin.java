package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeName;
import de.fraunhofer.iais.eis.AccessPermissionRule;
import de.fraunhofer.iais.eis.Asset;
import de.fraunhofer.iais.eis.AssetAdministrationShell;
import de.fraunhofer.iais.eis.ConceptDescription;
import de.fraunhofer.iais.eis.Submodel;
import de.fraunhofer.iais.eis.SubmodelElement;
import de.fraunhofer.iais.eis.SubmodelElementCollection;
import de.fraunhofer.iais.eis.View;

/**
 *
 * @author Michael Jacoby
 */
@JsonTypeName("Referable")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "modelType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = AccessPermissionRule.class, name = "AccessPermissionRule"),
    @JsonSubTypes.Type(value = AssetAdministrationShell.class, name = "AssetAdministrationShell"),
    @JsonSubTypes.Type(value = View.class, name = "View"),
    @JsonSubTypes.Type(value = Asset.class, name = "Asset"),
    @JsonSubTypes.Type(value = Submodel.class, name = "Submodel"),
    @JsonSubTypes.Type(value = SubmodelElement.class, name = "SubmodelElement"),
    @JsonSubTypes.Type(value = ConceptDescription.class, name = "ConceptDescription"),
    @JsonSubTypes.Type(value = SubmodelElementCollection.class, name = "SubmodelElementCollection")
})

public interface ReferableMixin {

}
