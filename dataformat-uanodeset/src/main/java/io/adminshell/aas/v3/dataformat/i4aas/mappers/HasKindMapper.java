package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.ListOfReferences;
import org.opcfoundation.ua._2011._03.uanodeset.Reference;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;
import io.adminshell.aas.v3.model.ModelingKind;

public interface HasKindMapper {

	default void mapKind(ModelingKind modelingKind, UAObject target, MappingContext ctx) {

		UAVariable mappedKind = new I4AASEnumMapper(modelingKind, ctx).map();

		mappedKind.setParentNodeId(target.getNodeId());
		if (mappedKind.getReferences() == null) {
			mappedKind.setReferences(new ListOfReferences());
		}
		Reference parentRef = Reference.builder().withIsForward(false).withReferenceType(UaId.HasProperty.getName())
				.withValue(target.getNodeId()).build();
		mappedKind.getReferences().getReference().add(parentRef);
		if (target.getReferences() == null) {
			target.setReferences(new ListOfReferences());
		}
		Reference childRef = Reference.builder().withReferenceType(UaId.HasProperty.getName())
				.withValue(mappedKind.getNodeId()).build();
		target.getReferences().getReference().add(childRef);

	}

}
