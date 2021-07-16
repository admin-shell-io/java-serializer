package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.Referable;

public class ReferableMapper<T extends Referable> extends I4AASMapper<T, UAObject> {

	public ReferableMapper(T src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		target = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString())
		.withBrowseName(browseNameOf(source)).withDisplayName(I4AASUtils.createDisplayName(source)).build();
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		String category = source.getCategory();
		if (category != null) {
			UAVariable categoryProperty = newStringProperty("Category", category);
			addToNodeset(categoryProperty);
			attachAsProperty(target, categoryProperty);
		}
	}

	protected String browseNameOf(Referable src) {
		return super.browseNameOf(src.getIdShort());
	}
}
