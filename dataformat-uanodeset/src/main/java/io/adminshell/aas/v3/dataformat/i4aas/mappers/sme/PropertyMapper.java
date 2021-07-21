package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.ReferenceMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.StringPropertyMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.Property;

public class PropertyMapper extends SubmodelElementMapper<Property> {

	public PropertyMapper(Property src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		addTypeReference(I4aasId.AASPropertyType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();

		if (source.getValueId() != null) {
			UAObject uaValueId = new ReferenceMapper(source.getValueId(), ctx, "ValueId").map();
			attachAsComponent(target, uaValueId);
		}

		if (source.getValueType() != null) {
			String valueType = source.getValueType();
			UAVariable mappedValueType = new ValueTypeMapper(valueType, ctx).map();
			attachAsProperty(target, mappedValueType);
		}

		if (source.getValue() != null) {
			//use string property first, should use a interpretation of valuetype later
			UAVariable newStringProperty = new StringPropertyMapper("Value", source.getValue(), ctx).map();
			attachAsProperty(target, newStringProperty);
		}
	}

}
