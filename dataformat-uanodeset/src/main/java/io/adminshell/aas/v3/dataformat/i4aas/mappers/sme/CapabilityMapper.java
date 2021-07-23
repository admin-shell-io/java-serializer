package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.Capability;

public class CapabilityMapper extends SubmodelElementMapper<Capability> {

	public CapabilityMapper(Capability src, MappingContext ctx) {
		super(src, ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		addTypeReference(I4aasId.AASCapabilityType);
		return target;
	}
	
	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();
	}
}
