package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.IdentifierKeyValuePair;
import io.adminshell.aas.v3.model.Reference;

public class IdentifierKeyValuePairMapper extends I4AASMapper<IdentifierKeyValuePair, UAObject> {

	public IdentifierKeyValuePairMapper(IdentifierKeyValuePair src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		target = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString())
				.withBrowseName(createBrowseName(source.getKey()))
				.withDisplayName(createLocalizedText(source.getKey())).build();
		addTypeReference(I4aasId.AASIdentifierKeyValuePairType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		Reference externalSubjectId = source.getExternalSubjectId();
		if (externalSubjectId != null) {
			UAObject uaExtSubId = new ReferenceMapper(externalSubjectId, ctx, "ExternalSubjectId").map();
			attachAsComponent(target, uaExtSubId);
		}
		String key = source.getKey();
		if (key != null) {
			UAVariable uaKey = new StringPropertyMapper("Key", key, ctx).map();
			attachAsProperty(target, uaKey);
		}
		String value = source.getValue();
		if (value != null) {
			UAVariable uaValue = new StringPropertyMapper("Value", value, ctx).map();
			attachAsProperty(target, uaValue);
		}
	}

}
