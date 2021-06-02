package io.adminshell.aas.v3.dataformat.json.custommixins;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeName;

import io.adminshell.aas.v3.model.AnnotatedRelationshipElement;

@JsonTypeName("RelationshipElement")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "modelType")
@JsonSubTypes({ @JsonSubTypes.Type(value = AnnotatedRelationshipElement.class, name = "AnnotatedRelationshipElement") })
public interface RelationshipElementMixin {

}
