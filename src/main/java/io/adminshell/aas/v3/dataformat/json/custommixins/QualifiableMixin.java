package io.adminshell.aas.v3.dataformat.json.custommixins;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.adminshell.aas.v3.model.AccessPermissionRule;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.SubmodelElement;

@JsonTypeName("Qualifiable")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
@JsonSubTypes({ @JsonSubTypes.Type(value = AccessPermissionRule.class), @JsonSubTypes.Type(value = Submodel.class),
		@JsonSubTypes.Type(value = SubmodelElement.class) })
public interface QualifiableMixin {

}
