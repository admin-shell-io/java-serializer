package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import javax.xml.bind.JAXBElement;

import org.opcfoundation.ua._2008._02.types.ObjectFactory;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;

public class StringPropertyMapper extends I4AASMapper<String, UAVariable> {

	private String key;
	private int nsIdx;

	public StringPropertyMapper(String key, String src, MappingContext ctx, int nsIdx) {
		super(src, ctx);
		this.key = key;
		this.nsIdx = nsIdx;
	}

	@Override
	protected UAVariable createTargetObject() {
		JAXBElement<String> idStringValue = new ObjectFactory().createString(source);
		org.opcfoundation.ua._2011._03.uanodeset.UAVariable.Builder<Void> idVarBuilder = UAVariable.builder()
				.withValue().withAny(idStringValue).end().withDisplayName(createLocalizedText(key))
				.withDataType(UaId.String.getName()).withNodeId(ctx.newModelNodeIdAsString())
				.withBrowseName(createBrowseName(key, nsIdx)).withAccessLevel(3L);
		target = idVarBuilder.build();
		addTypeReferenceFor(target, UaId.PropertyType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		// TODO Auto-generated method stub

	}

}
