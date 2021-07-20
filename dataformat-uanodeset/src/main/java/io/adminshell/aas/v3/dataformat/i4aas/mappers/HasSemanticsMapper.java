package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.ListOfReferences;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;
import io.adminshell.aas.v3.model.HasSemantics;
import io.adminshell.aas.v3.model.KeyType;

public interface HasSemanticsMapper {

	default void mapSemantics(HasSemantics source, UAObject target, MappingContext ctx) {
		io.adminshell.aas.v3.model.Reference semanticId = source.getSemanticId();
		if (semanticId != null && !semanticId.getKeys().isEmpty()) {
			// create Dictionary Entry based on first key
			String keyValue = semanticId.getKeys().get(0).getValue();
			KeyType idType = semanticId.getKeys().get(0).getIdType();
			ListOfReferences listOfReferences = new ListOfReferences();

			String typeNodeId = null;
			if (KeyType.IRI == idType) {
				typeNodeId = ctx.getI4aasNodeIdAsString(I4aasId.AASIriConceptDescriptionType);
			} else if (KeyType.IRDI == idType) {
				typeNodeId = ctx.getI4aasNodeIdAsString(I4aasId.AASIrdiConceptDescriptionType);
			}

			org.opcfoundation.ua._2011._03.uanodeset.Reference typeRef = org.opcfoundation.ua._2011._03.uanodeset.Reference
					.builder().withReferenceType(UaId.HasTypeDefinition.getName()).withValue(typeNodeId).build();
			listOfReferences.getReference().add(typeRef);

			org.opcfoundation.ua._2011._03.uanodeset.Reference orgaRef = org.opcfoundation.ua._2011._03.uanodeset.Reference
					.builder().withReferenceType(UaId.Organizes.getName()).withIsForward(false).withValue("i=17594")
					.build();
			listOfReferences.getReference().add(orgaRef);
			ListOfReferences dictionaryStaticReferences = listOfReferences;
			UAObject dictEntry = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString())
					.withReferences(dictionaryStaticReferences).withBrowseName(ctx.getModelNsIndex() + ":" + keyValue)
					.withDisplayName(I4AASUtils.createLocalizedText(keyValue)).build();

			ctx.getNodeSet().getUAObjectOrUAVariableOrUAMethod().add(dictEntry);

			// add Concept Description
			UAObject dictConcept = new ReferenceMapper(semanticId, ctx, "ConceptDescription").map();

			//attach dictConcept and dictEntry as component
			dictConcept.setParentNodeId(dictEntry.getNodeId());
			if (dictConcept.getReferences() == null) {
				dictConcept.setReferences(new ListOfReferences());
			}
			org.opcfoundation.ua._2011._03.uanodeset.Reference parentRef1 = org.opcfoundation.ua._2011._03.uanodeset.Reference
					.builder().withIsForward(false).withReferenceType(UaId.HasComponent.getName())
					.withValue(dictEntry.getNodeId()).build();
			dictConcept.getReferences().getReference().add(parentRef1);
			if (dictEntry.getReferences() == null) {
				dictEntry.setReferences(new ListOfReferences());
			}
			org.opcfoundation.ua._2011._03.uanodeset.Reference childRef1 = org.opcfoundation.ua._2011._03.uanodeset.Reference
					.builder().withReferenceType(UaId.HasComponent.getName()).withValue(dictConcept.getNodeId())
					.build();
			dictEntry.getReferences().getReference().add(childRef1);

			//add HasDictionaryEntry reference
			dictEntry.setParentNodeId(target.getNodeId());
			if (dictEntry.getReferences() == null) {
				dictEntry.setReferences(new ListOfReferences());
			}
			org.opcfoundation.ua._2011._03.uanodeset.Reference parentRef = org.opcfoundation.ua._2011._03.uanodeset.Reference
					.builder().withIsForward(false).withReferenceType(UaId.HasDictionaryEntry.getName())
					.withValue(target.getNodeId()).build();
			dictEntry.getReferences().getReference().add(parentRef);
			if (target.getReferences() == null) {
				target.setReferences(new ListOfReferences());
			}
			org.opcfoundation.ua._2011._03.uanodeset.Reference childRef = org.opcfoundation.ua._2011._03.uanodeset.Reference
					.builder().withReferenceType(UaId.HasDictionaryEntry.getName()).withValue(dictEntry.getNodeId())
					.build();
			target.getReferences().getReference().add(childRef);
		}

	}
}
