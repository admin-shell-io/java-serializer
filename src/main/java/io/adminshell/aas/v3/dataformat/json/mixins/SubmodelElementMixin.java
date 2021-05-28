package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeName;
import de.fraunhofer.iais.eis.BasicEvent;
import de.fraunhofer.iais.eis.Blob;
import de.fraunhofer.iais.eis.Capability;
import de.fraunhofer.iais.eis.Entity;
import de.fraunhofer.iais.eis.File;
import de.fraunhofer.iais.eis.MultiLanguageProperty;
import de.fraunhofer.iais.eis.Operation;
import de.fraunhofer.iais.eis.Property;
import de.fraunhofer.iais.eis.Range;
import de.fraunhofer.iais.eis.ReferenceElement;
import de.fraunhofer.iais.eis.RelationshipElement;
import de.fraunhofer.iais.eis.SubmodelElementCollection;

@JsonTypeName("SubmodelElement")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "modelType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Property.class, name = "Property"),
    @JsonSubTypes.Type(value = MultiLanguageProperty.class, name = "MultiLanguageProperty"),
    @JsonSubTypes.Type(value = Range.class, name = "Range"),
    @JsonSubTypes.Type(value = BasicEvent.class, name = "BasicEvent"),
    @JsonSubTypes.Type(value = Capability.class, name = "Capability"),
    @JsonSubTypes.Type(value = ReferenceElement.class, name = "ReferenceElement"),
    @JsonSubTypes.Type(value = File.class, name = "File"),
    @JsonSubTypes.Type(value = Operation.class, name = "Operation"),
    @JsonSubTypes.Type(value = Blob.class, name = "Blob"),
    @JsonSubTypes.Type(value = Entity.class, name = "Entity"),
    @JsonSubTypes.Type(value = SubmodelElementCollection.class, name = "SubmodelElementCollection"),
    @JsonSubTypes.Type(value = RelationshipElement.class, name = "RelationshipElement")
})
public interface SubmodelElementMixin {

}
