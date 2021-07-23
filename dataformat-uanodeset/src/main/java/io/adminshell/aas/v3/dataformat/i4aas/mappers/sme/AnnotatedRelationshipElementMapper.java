package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import java.util.List;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.ReferenceMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.AnnotatedRelationshipElement;
import io.adminshell.aas.v3.model.DataElement;
import io.adminshell.aas.v3.model.Reference;

public class AnnotatedRelationshipElementMapper extends SubmodelElementMapper<AnnotatedRelationshipElement> {

	public AnnotatedRelationshipElementMapper(AnnotatedRelationshipElement src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		addTypeReference(I4aasId.AASAnnotatedRelationshipElementType);
		return target;
	}
	
	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();
		UAObject createFolder = createFolder("Annotation");
		List<DataElement> annotations = source.getAnnotations();
		for (DataElement dataElement : annotations) {
			UAObject uaDataElement = SubmodelElementMappers.getMapper(dataElement, ctx).map();
			attachAsComponent(createFolder, uaDataElement);
		}
		Reference first = source.getFirst();
		if (first != null) {
			UAObject uaFirst = new ReferenceMapper(first, ctx, "First").map();
			attachAsComponent(target, uaFirst);
		}
		Reference second = source.getSecond();
		if (second != null) {
			UAObject uaSecond = new ReferenceMapper(second, ctx, "Second").map();
			attachAsComponent(target, uaSecond);
		}
	}
}
