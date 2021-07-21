package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.ListOfReferences;
import org.opcfoundation.ua._2011._03.uanodeset.UANode;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;
import io.adminshell.aas.v3.model.HasSemantics;

public interface HasSemanticsMapper {

	default void mapSemantics(HasSemantics source, UAObject target, MappingContext ctx) {
		io.adminshell.aas.v3.model.Reference semanticId = source.getSemanticId();
		if (semanticId != null && !semanticId.getKeys().isEmpty()) {
						
			// get Dictionary Entry based on first key
			String keyValue = semanticId.getKeys().get(0).getValue();
			UANode nodeForIdentification = ctx.getNodeIdForIdentification(keyValue);
			if (nodeForIdentification != null) {
				// add HasDictionaryEntry reference
				if (nodeForIdentification.getReferences() == null) {
					nodeForIdentification.setReferences(new ListOfReferences());
				}
				org.opcfoundation.ua._2011._03.uanodeset.Reference parentRef = org.opcfoundation.ua._2011._03.uanodeset.Reference
						.builder().withIsForward(false).withReferenceType(UaId.HasDictionaryEntry.getName())
						.withValue(target.getNodeId()).build();
				nodeForIdentification.getReferences().getReference().add(parentRef);
				if (target.getReferences() == null) {
					target.setReferences(new ListOfReferences());
				}
				org.opcfoundation.ua._2011._03.uanodeset.Reference childRef = org.opcfoundation.ua._2011._03.uanodeset.Reference
						.builder().withReferenceType(UaId.HasDictionaryEntry.getName()).withValue(nodeForIdentification.getNodeId())
						.build();
				target.getReferences().getReference().add(childRef);
			}
		}
	}
}
