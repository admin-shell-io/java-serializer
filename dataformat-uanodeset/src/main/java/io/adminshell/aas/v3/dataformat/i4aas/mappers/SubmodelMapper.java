package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.List;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.sme.SubmodelElementMappers;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.ModelingKind;
import io.adminshell.aas.v3.model.Property;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.SubmodelElement;
import io.adminshell.aas.v3.model.SubmodelElementCollection;

public class SubmodelMapper extends IdentifiableMapper<Submodel> implements HasKindMapper, HasSemanticsMapper {

	public SubmodelMapper(Submodel src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		addTypeReference(I4aasId.AASSubmodelType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();
		
		mapKind(source.getKind(), target, ctx);
		mapSemantics(source, target, ctx);
		
		List<SubmodelElement> submodelElements = source.getSubmodelElements();
		for (SubmodelElement submodelElement : submodelElements) {
			I4AASMapper<? extends SubmodelElement, UAObject> mapper = SubmodelElementMappers.getMapper(submodelElement, ctx);
			UAObject uaSubmodel = mapper.map();
			attachAsComponent(target, uaSubmodel);
		}
	}

}
