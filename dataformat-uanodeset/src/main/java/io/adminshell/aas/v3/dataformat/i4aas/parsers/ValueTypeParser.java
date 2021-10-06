package io.adminshell.aas.v3.dataformat.i4aas.parsers;

import java.util.Map;
import java.util.TreeMap;

import org.opcfoundation.ua.i4aas.v3.types.AASValueTypeDataType;

public class ValueTypeParser extends I4AASParser<String> {
	
	private static Map<AASValueTypeDataType, String> staticMap = new TreeMap<>();
	static {
		staticMap.put(AASValueTypeDataType.INT_32_5,"integer");
		staticMap.put(AASValueTypeDataType.LOCALIZED_TEXT_14,"langstring");
		staticMap.put(AASValueTypeDataType.STRING_11,"string");
		staticMap.put(AASValueTypeDataType.BOOLEAN_0,"boolean");
		staticMap.put(AASValueTypeDataType.FLOAT_9,"float");
		staticMap.put(AASValueTypeDataType.DOUBLE_10,"double");
		staticMap.put(AASValueTypeDataType.INT_64_7,"long");
		//to be extended
	}

	public ValueTypeParser(UANodeWrapper src, ParserContext ctx) {
		super(src, ctx);
	}

	@Override
	protected String createTargetObject() {
		Integer enumIdx = ParserUtils.extractValueAsInteger(source.getNodeVariable());
		AASValueTypeDataType aasValueTypeDataType = AASValueTypeDataType.values()[enumIdx];
		return staticMap.get(aasValueTypeDataType);
	}

	@Override
	protected void parseAndAttachChildren() {
	}

}
