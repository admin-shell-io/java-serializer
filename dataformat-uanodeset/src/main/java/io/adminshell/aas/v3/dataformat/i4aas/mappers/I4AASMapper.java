package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.ListOfReferences;
import org.opcfoundation.ua._2011._03.uanodeset.LocalizedText;
import org.opcfoundation.ua._2011._03.uanodeset.Reference;
import org.opcfoundation.ua._2011._03.uanodeset.UANode;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.BasicId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;
import io.adminshell.aas.v3.model.LangString;

public abstract class I4AASMapper<SOURCE, TARGET extends UANode> {

	protected MappingContext ctx;
	protected SOURCE source;
	protected TARGET target;

	public I4AASMapper(SOURCE src, MappingContext ctx) {
		this.source = src;
		this.ctx = ctx;
	}

	public final TARGET map() {
		if (source == null) {
			return null;
		}
		target = createTargetObject();
		addToNodeset();
		mapAndAttachChildren();
		return target;
	}

	protected abstract TARGET createTargetObject();

	protected abstract void mapAndAttachChildren();

	private void addToNodeset() {
		ctx.getNodeSet().getUAObjectOrUAVariableOrUAMethod().add(target);
	}

	protected final void addToNodeset(UANode node) {
		ctx.getNodeSet().getUAObjectOrUAVariableOrUAMethod().add(node);
	}

	protected final void addTypeReference(BasicId idForType) {
		addTypeReference(target, idForType);
	}

	protected final void addTypeReference(UANode anyNode, BasicId idForType) {
		if (anyNode.getReferences() == null) {
			anyNode.setReferences(new ListOfReferences());
		}
		ListOfReferences references = anyNode.getReferences();
		if (idForType instanceof I4aasId) {
			references.getReference().add(Reference.builder().withReferenceType(UaId.HasTypeDefinition.getName())
					.withValue(ctx.getI4aasNodeIdAsString((I4aasId) idForType)).build());
		}
		if (idForType instanceof UaId) {
			references.getReference().add(Reference.builder().withReferenceType(UaId.HasTypeDefinition.getName())
					.withValue(ctx.getUaBaseNodeIdAsString((UaId) idForType)).build());
		}
	}

	protected final String browseNameOf(String name) {
		return ctx.getModelNsIndex() + ":" + name;
	}

	protected final void attachAsProperty(UAObject parent, UAVariable child) {
		child.setParentNodeId(parent.getNodeId());
		if (child.getReferences() == null) {
			child.setReferences(new ListOfReferences());
		}
		Reference parentRef = Reference.builder().withIsForward(false).withReferenceType(UaId.HasProperty.getName())
				.withValue(parent.getNodeId()).build();
		child.getReferences().getReference().add(parentRef);
		if (parent.getReferences() == null) {
			parent.setReferences(new ListOfReferences());
		}
		Reference childRef = Reference.builder().withReferenceType(UaId.HasProperty.getName())
				.withValue(child.getNodeId()).build();
		parent.getReferences().getReference().add(childRef);
	}

	protected final void attachAsComponent(UAObject parent, UAObject child) {
		child.setParentNodeId(parent.getNodeId());
		if (child.getReferences() == null) {
			child.setReferences(new ListOfReferences());
		}
		Reference parentRef = Reference.builder().withIsForward(false).withReferenceType(UaId.HasComponent.getName())
				.withValue(parent.getNodeId()).build();
		child.getReferences().getReference().add(parentRef);
		if (parent.getReferences() == null) {
			parent.setReferences(new ListOfReferences());
		}
		Reference childRef = Reference.builder().withReferenceType(UaId.HasComponent.getName())
				.withValue(child.getNodeId()).build();
		parent.getReferences().getReference().add(childRef);
	}

	protected final void attachAsDictionaryEntry(UAObject parent, UAObject child) {
		child.setParentNodeId(parent.getNodeId());
		if (child.getReferences() == null) {
			child.setReferences(new ListOfReferences());
		}
		Reference parentRef = Reference.builder().withIsForward(false)
				.withReferenceType(UaId.HasDictionaryEntry.getName()).withValue(parent.getNodeId()).build();
		child.getReferences().getReference().add(parentRef);
		if (parent.getReferences() == null) {
			parent.setReferences(new ListOfReferences());
		}
		Reference childRef = Reference.builder().withReferenceType(UaId.HasDictionaryEntry.getName())
				.withValue(child.getNodeId()).build();
		parent.getReferences().getReference().add(childRef);
	}

	protected LocalizedText mapLangString(LangString description) {
		return LocalizedText.builder().withLocale(description.getLanguage()).withValue(description.getValue()).build();
	}
}
