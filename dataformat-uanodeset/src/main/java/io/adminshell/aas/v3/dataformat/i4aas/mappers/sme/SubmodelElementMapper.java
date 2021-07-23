package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.HasDataSpecificationMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.HasKindMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.HasSemanticsMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.QualifiableMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.ReferableMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.SubmodelElement;

public class SubmodelElementMapper<T extends SubmodelElement> extends ReferableMapper<T> implements HasKindMapper, HasSemanticsMapper, HasDataSpecificationMapper, QualifiableMapper {

	public SubmodelElementMapper(T src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();
		mapKind(source, target, ctx);
		mapSemantics(source, target, ctx);
		mapDataSpecification(source, target, ctx);
		mapQualifiable(source, target, ctx);
	}



}
