package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import de.fraunhofer.iais.eis.AccessPermissionRule;
import de.fraunhofer.iais.eis.Submodel;
import de.fraunhofer.iais.eis.SubmodelElement;

@JsonTypeName("Qualifiable")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = AccessPermissionRule.class),
    @JsonSubTypes.Type(value = Submodel.class),
    @JsonSubTypes.Type(value = SubmodelElement.class)
})
public interface QualifiableMixin {

}
