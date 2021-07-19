package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.ReferableMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.Blob;
import io.adminshell.aas.v3.model.SubmodelElement;

public class SubmodelElementMapper<T extends SubmodelElement> extends ReferableMapper<T> {

	public SubmodelElementMapper(T src, MappingContext ctx) {
		super(src, ctx);
	}



}
