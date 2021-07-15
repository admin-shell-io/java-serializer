package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.ListOfReferences;
import org.opcfoundation.ua._2011._03.uanodeset.Reference;
import org.opcfoundation.ua._2011._03.uanodeset.UANode;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject.Builder;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.BasicId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;

public abstract class I4AASMapper<SOURCE, TARGET extends UANode> {

	protected MappingContext ctx;
	protected SOURCE src;
	protected TARGET result;

	public I4AASMapper(SOURCE src, MappingContext ctx) {
		this.src = src;
		this.ctx = ctx;
	}

	public TARGET map() {
		if (src == null) {
			return null;
		}
		result = createTargetObject();
		addToNodeset();
		mapAndAttachChildren();
		return result;
	}

	protected abstract TARGET createTargetObject();

	protected abstract void mapAndAttachChildren();

	private void addToNodeset() {
		ctx.getNodeSet().getUAObjectOrUAVariableOrUAMethod().add(result);
	}

	protected final void addTypeReference(BasicId idForType) {
		if (result.getReferences() == null) {
			result.setReferences(new ListOfReferences());
		}
		ListOfReferences references = result.getReferences();
		if (idForType instanceof I4aasId) {
			references.getReference().add(Reference.builder().withReferenceType(UaId.HasTypeDefinition.getName())
					.withValue(ctx.getI4aasNodeIdAsString((I4aasId) idForType)).build());
		}
		if (idForType instanceof UaId) {
			references.getReference().add(Reference.builder().withReferenceType(UaId.HasTypeDefinition.getName())
					.withValue(ctx.getUaBaseNodeIdAsString((UaId) idForType)).build());
		}
	}

}
