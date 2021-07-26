package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.DataSpecificationIEC61360;
import io.adminshell.aas.v3.model.DataTypeIEC61360;
import io.adminshell.aas.v3.model.Reference;

public class DataSpecificationIEC61360Mapper extends I4AASMapper<DataSpecificationIEC61360, UAObject> {

	private String name;
	private int namespaceIdx;


	public DataSpecificationIEC61360Mapper(DataSpecificationIEC61360 src, MappingContext ctx,
			String name, int namespaceIdx) {
		super(src, ctx);
		this.namespaceIdx = namespaceIdx;
		this.name = name;
	}

	@Override
	protected UAObject createTargetObject() {
		target = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString()).withBrowseName(createBrowseName(name, namespaceIdx))
				.withDisplayName(createLocalizedText(name)).build();
		addTypeReference(I4aasId.AASDataSpecificationIEC61360Type);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		
		DataTypeIEC61360 dataType = source.getDataType();
		if (dataType != null) {
			UAVariable uaDataType = new I4AASEnumMapper(dataType, ctx).map();
			attachAsProperty(target, uaDataType);
		}

		String sourceOfDefinition = source.getSourceOfDefinition();
		if (sourceOfDefinition != null) {
			UAVariable uaVariable = new StringPropertyMapper("SourceOfDefinition", sourceOfDefinition, ctx, ctx.getI4aasNsIndex()).map();
			attachAsProperty(target, uaVariable);
		}

		String symbol = source.getSymbol();
		if (symbol != null) {
			UAVariable uaVariable = new StringPropertyMapper("Symbol", symbol, ctx, ctx.getI4aasNsIndex()).map();
			attachAsProperty(target, uaVariable);
		}

		String unit = source.getUnit();
		if (unit != null) {
			UAVariable uaVariable = new StringPropertyMapper("Unit", unit, ctx, ctx.getI4aasNsIndex()).map();
			attachAsProperty(target, uaVariable);
		}

		String valueFormat = source.getValueFormat();
		if (valueFormat != null) {
			UAVariable uaVariable = new StringPropertyMapper("ValueFormat", valueFormat, ctx, ctx.getI4aasNsIndex()).map();
			attachAsProperty(target, uaVariable);
		}

		String value = source.getValue();
		if (value != null) {
			UAVariable uaVariable = new StringPropertyMapper("Value", value, ctx, ctx.getI4aasNsIndex()).map();
			attachAsProperty(target, uaVariable);
		}

		Reference unitId = source.getUnitId();
		if (unitId != null) {
			UAObject uaRef = new ReferenceMapper(unitId, ctx, "UnitId").map();
			attachAsComponent(target, uaRef);
		}

		Reference valueId = source.getValueId();
		if (valueId != null) {
			UAObject uaRef = new ReferenceMapper(valueId, ctx, "ValueId").map();
			attachAsComponent(target, uaRef);
		}
		
		UAVariable uaDefinition = new LangStringPropertyMapper("Definition", source.getDefinitions(), ctx, ctx.getI4aasNsIndex()).map();
		attachAsProperty(target, uaDefinition);

		UAVariable uaPreferredNames = new LangStringPropertyMapper("PreferredName", source.getPreferredNames(), ctx, ctx.getI4aasNsIndex()).map();
		attachAsProperty(target, uaPreferredNames);

		UAVariable uaShortName = new LangStringPropertyMapper("ShortName", source.getShortNames(), ctx, ctx.getI4aasNsIndex()).map();
		attachAsProperty(target, uaShortName);

	}

}
