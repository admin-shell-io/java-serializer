package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.sme.ValueTypeMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.Qualifier;
import io.adminshell.aas.v3.model.Reference;

public class QualifierMapper extends I4AASMapper<Qualifier, UAObject> {

	private String name;

	public QualifierMapper(Qualifier src, MappingContext ctx, String name) {
		super(src, ctx);
		this.name = name;
	}

	@Override
	protected UAObject createTargetObject() {
		target = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString()).withBrowseName(createBrowseName(name))
				.withDisplayName(createLocalizedText(name)).build();
		addTypeReference(I4aasId.AASQualifierType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		Reference semanticId = source.getSemanticId();
		if (semanticId != null) {
			UAObject map = new ReferenceMapper(semanticId, ctx, "ValueId").map();
			attachAsComponent(target, map);
		}

		String type = source.getType();
		if (type != null) {
			UAVariable map = new StringPropertyMapper("Type", type, ctx).map();
			attachAsProperty(target, map);
		}

		String value = source.getValue();
		if (value != null) {
			UAVariable map = new StringPropertyMapper("Value", value, ctx).map();
			attachAsProperty(target, map);
		}
		
		String valueType = source.getValueType();
		if (valueType != null) {
			UAVariable map = new ValueTypeMapper(valueType, ctx).map();
			attachAsProperty(target, map);
		}
	}

}
