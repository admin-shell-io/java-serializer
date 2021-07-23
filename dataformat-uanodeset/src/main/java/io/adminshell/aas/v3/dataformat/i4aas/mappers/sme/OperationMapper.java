package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.Operation;

public class OperationMapper extends SubmodelElementMapper<Operation> {

	public OperationMapper(Operation src, MappingContext ctx) {
		super(src, ctx);
	}
	
	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		addTypeReference(I4aasId.AASOperationType);
		return target;
	}
	
	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();
		//not applicable for I4AAS
	}

}
