package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeName;
import de.fraunhofer.iais.eis.AnnotatedRelationshipElement;

@JsonTypeName("RelationshipElement")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "modelType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = AnnotatedRelationshipElement.class, name = "AnnotatedRelationshipElement")
})
public interface RelationshipElementMixin {

}
