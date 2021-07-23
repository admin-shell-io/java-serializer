package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.LangStringPropertyMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.ReferenceMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.MultiLanguageProperty;
import io.adminshell.aas.v3.model.Reference;

public class MultiLanguagePropertyMapper extends SubmodelElementMapper<MultiLanguageProperty> {

	public MultiLanguagePropertyMapper(MultiLanguageProperty src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		addTypeReference(I4aasId.AASMultiLanguagePropertyType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();
		Reference valueId = source.getValueId();
		if (valueId != null) {
			UAObject map = new ReferenceMapper(valueId, ctx, "ValueId").map();
			attachAsComponent(target, map);
		}
		UAVariable uaLangString = new LangStringPropertyMapper("Value", source.getValues(), ctx).map();
		attachAsProperty(target, uaLangString);
	}

}
