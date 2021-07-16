package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.Submodel;

public class SubmodelMapper extends IdentifiableMapper<Submodel> {

	public SubmodelMapper(Submodel src, MappingContext ctx) {
		super(src, ctx);
	}
	
	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		addTypeReference(I4aasId.AASSubmodelType);
		return result;
	}
	
	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();
	}

}
