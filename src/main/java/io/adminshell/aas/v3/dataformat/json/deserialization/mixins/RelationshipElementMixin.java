package io.adminshell.aas.v3.dataformat.json.deserialization.mixins;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import de.fraunhofer.iais.eis.AnnotatedRelationshipElement;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "modelType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = AnnotatedRelationshipElement.class, name = "AnnotatedRelationshipElement")
})
public interface RelationshipElementMixin {

}
