package io.adminshell.aas.v3.dataformat.json.deserialization.mixins;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.fraunhofer.iais.eis.Formula;
import de.fraunhofer.iais.eis.Qualifier;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "modelType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Qualifier.class, name = "Qualifier"),
    @JsonSubTypes.Type(value = Formula.class, name = "Formula")
})
public interface ConstraintMixin {

}
