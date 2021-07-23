package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import javax.xml.bind.JAXBElement;

import org.opcfoundation.ua._2008._02.types.ObjectFactory;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;

public class ByteStringPropertyMapper extends I4AASMapper<byte[], UAVariable> {

	private String key;

	public ByteStringPropertyMapper(String key, byte[] src, MappingContext ctx) {
		super(src, ctx);
		this.key = key;
	}

	@Override
	protected UAVariable createTargetObject() {
		JAXBElement<byte[]> byteStringValue = new ObjectFactory().createByteString(source);
		org.opcfoundation.ua._2011._03.uanodeset.UAVariable.Builder<Void> idVarBuilder = UAVariable.builder()
				.withValue().withAny(byteStringValue).end().withDisplayName(createLocalizedText(key))
				.withDataType(UaId.ByteString.getName()).withNodeId(ctx.newModelNodeIdAsString())
				.withBrowseName(createBrowseName(key)).withAccessLevel(3L);
		target = idVarBuilder.build();
		addTypeReferenceFor(target, UaId.PropertyType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
	}

}
