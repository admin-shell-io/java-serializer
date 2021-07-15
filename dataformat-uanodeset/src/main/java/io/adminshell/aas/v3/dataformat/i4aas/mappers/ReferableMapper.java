package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.Referable;

public class ReferableMapper<T extends Referable> extends I4AASMapper<T, UAObject> {

	public ReferableMapper(T src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		UAObject uaObject = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString())
		.withBrowseName(browseNameOf(src.getIdShort())).withDisplayName(I4AASUtils.createDisplayName(src)).build();
		return uaObject;
	}

	@Override
	protected void mapAndAttachChildren() {
		// TODO Auto-generated method stub
		
	}


	private String browseNameOf(String value) {
		return ctx.getModelNsIndex() + ":" + value;
	}
}
