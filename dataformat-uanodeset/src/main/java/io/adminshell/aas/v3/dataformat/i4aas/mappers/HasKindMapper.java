package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.HasKind;

public interface HasKindMapper {

	default void mapKind(HasKind kind, UAObject target, MappingContext ctx) {
		if (kind.getKind() != null) {
			UAVariable mappedKind = new I4AASEnumMapper(kind.getKind(), ctx).map();
			I4AASMapper.attachAsProperty(target, mappedKind);
		}
	}

}
