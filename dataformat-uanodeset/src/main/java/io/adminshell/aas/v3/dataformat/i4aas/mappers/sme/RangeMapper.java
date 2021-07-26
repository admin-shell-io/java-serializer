package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.StringPropertyMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.Range;

public class RangeMapper extends SubmodelElementMapper<Range> {

	public RangeMapper(Range src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		addTypeReference(I4aasId.AASRangeType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();

		String min = source.getMin();
		if (min != null) {
			UAVariable map = new StringPropertyMapper("Min", min, ctx, ctx.getI4aasNsIndex()).map();
			attachAsProperty(target, map);
		}

		String max = source.getMax();
		if (max != null) {
			UAVariable map = new StringPropertyMapper("Max", max, ctx, ctx.getI4aasNsIndex()).map();
			attachAsProperty(target, map);
		}

		String valueType = source.getValueType();
		if (valueType != null) {
			UAVariable map = new ValueTypeMapper(valueType, ctx).map();
			attachAsProperty(target, map);
		}
	}
}
