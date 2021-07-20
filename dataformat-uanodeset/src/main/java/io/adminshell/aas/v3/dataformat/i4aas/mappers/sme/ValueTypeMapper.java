package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

import javax.xml.bind.JAXBElement;

import org.opcfoundation.ua._2008._02.types.ObjectFactory;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable.Builder;
import org.opcfoundation.ua.i4aas.types.AASValueTypeDataType;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.I4AASMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;

public class ValueTypeMapper extends I4AASMapper<String, UAVariable> {

	private static Map<String, AASValueTypeDataType> staticMap = new TreeMap<>();
	static {
		staticMap.put("integer", AASValueTypeDataType.INT_32_5);
		staticMap.put("langString", AASValueTypeDataType.LOCALIZED_TEXT_14);
		// TODO
	}

	public ValueTypeMapper(String src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAVariable createTargetObject() {
		Builder<Void> builder = UAVariable.builder().withDisplayName(I4AASUtils.createLocalizedText("ValueType"))
				.withBrowseName(browseNameOf("ValueType")).withNodeId(ctx.newModelNodeIdAsString()).withAccessLevel(3L)
				.withDataType(AASValueTypeDataType.class.getSimpleName());

		AASValueTypeDataType derivedEnum = staticMap.computeIfAbsent(source, this::mappingFuntion);
		if (derivedEnum == null) {
			throw new IllegalArgumentException("There is no ValueType mapping rule defined for '" + source + "'.");
		}

		JAXBElement<Integer> targetIdTypeVar = new ObjectFactory().createInt32(derivedEnum.ordinal());
		UAVariable uaVariable = builder.withValue().withAny(targetIdTypeVar).end().build();
		addTypeReference(uaVariable, UaId.PropertyType);
		return uaVariable;
	}

	@Override
	protected void mapAndAttachChildren() {
		// TODO Auto-generated method stub
	}

	private AASValueTypeDataType mappingFuntion(String input) {
		// retry with lower case
		AASValueTypeDataType aasValueTypeDataType = null;
		aasValueTypeDataType = staticMap.get(input.toLowerCase());
		if (aasValueTypeDataType == null) {
			return aasValueTypeDataType;
		}
		// TODO
		return null;
	}

}
