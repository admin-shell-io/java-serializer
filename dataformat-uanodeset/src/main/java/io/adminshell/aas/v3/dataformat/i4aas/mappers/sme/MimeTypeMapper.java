package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import javax.xml.bind.JAXBElement;

import org.opcfoundation.ua._2008._02.types.ObjectFactory;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.I4AASMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;

public class MimeTypeMapper extends I4AASMapper<String, UAVariable> {

	public MimeTypeMapper(String src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAVariable createTargetObject() {
		JAXBElement<String> idStringValue = new ObjectFactory().createString(source);
		org.opcfoundation.ua._2011._03.uanodeset.UAVariable.Builder<Void> idVarBuilder = UAVariable.builder()
				.withValue().withAny(idStringValue).end().withDisplayName(createLocalizedText("MimeType"))
				.withDataType(I4aasId.AASMimeDataType.getName()).withNodeId(ctx.newModelNodeIdAsString())
				.withBrowseName(createI4AASBrowseName("MimeType")).withAccessLevel(3L);
		target = idVarBuilder.build();
		addTypeReferenceFor(target, UaId.PropertyType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
	}

}
