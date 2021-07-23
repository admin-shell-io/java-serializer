package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import java.util.Collection;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.BooleanPropertyMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.I4AASMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.SubmodelElement;
import io.adminshell.aas.v3.model.SubmodelElementCollection;

public class SubmodelElementCollectionMapper extends SubmodelElementMapper<SubmodelElementCollection> {

	public SubmodelElementCollectionMapper(SubmodelElementCollection submodelElement, MappingContext ctx) {
		super(submodelElement, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		if (source.getOrdered()) {
			addTypeReference(I4aasId.AASOrderedSubmodelElementCollectionType);
		} else {
			addTypeReference(I4aasId.AASSubmodelElementCollectionType);
		}
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();
		Collection<SubmodelElement> values = source.getValues();
		for (SubmodelElement submodelElement : values) {
			I4AASMapper<? extends SubmodelElement, UAObject> mapper = SubmodelElementMappers.getMapper(submodelElement,
					ctx);
			UAObject uaSubmodel = mapper.map();
			if (source.getOrdered()) {
				attachAsOrderedComponent(target, uaSubmodel);
			} else {
				attachAsComponent(target, uaSubmodel);
			}
		}
		UAVariable uaAllowDuplicates = new BooleanPropertyMapper("AllowDuplicates", source.getAllowDuplicates(), ctx)
				.map();
		attachAsProperty(target, uaAllowDuplicates);
	}


}
