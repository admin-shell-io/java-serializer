package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import javax.xml.bind.JAXBElement;

import org.opcfoundation.ua._2008._02.types.ObjectFactory;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;

public class StringPropertyMapper extends I4AASMapper<String, UAVariable> {

	private String key;

	public StringPropertyMapper(String key, String src, MappingContext ctx) {
		super(src, ctx);
		this.key = key;
	}

	@Override
	protected UAVariable createTargetObject() {
		JAXBElement<String> idStringValue = new ObjectFactory().createString(source);
		org.opcfoundation.ua._2011._03.uanodeset.UAVariable.Builder<Void> idVarBuilder = UAVariable.builder()
				.withValue().withAny(idStringValue).end().withDisplayName(I4AASUtils.createLocalizedText(key))
				.withDataType(UaId.String.getName()).withNodeId(ctx.newModelNodeIdAsString())
				.withBrowseName(browseNameOf(key)).withAccessLevel(3L);
		target = idVarBuilder.build();
		addTypeReference(target, UaId.PropertyType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		// TODO Auto-generated method stub
		
	}

}
