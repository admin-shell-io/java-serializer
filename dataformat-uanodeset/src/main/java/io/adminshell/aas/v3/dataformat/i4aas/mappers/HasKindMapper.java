package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.ModelingKind;

public interface HasKindMapper {

	default void mapKind(ModelingKind modelingKind, UAObject target, MappingContext ctx) {
		UAVariable mappedKind = new I4AASEnumMapper(modelingKind, ctx).map();
		I4AASMapper.attachAsProperty(target, mappedKind);
	}

}
