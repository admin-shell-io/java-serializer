package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.adminshell.aas.v3.dataformat.core.serialization.EmbeddedDataSpecificationSerializer;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.HasKind;
import io.adminshell.aas.v3.model.ModelingKind;

public interface HasKindMapper {

	static final Logger logger = LoggerFactory.getLogger(HasKindMapper.class);

	default void mapKind(HasKind kind, UAObject target, MappingContext ctx) {
		ModelingKind modelingKind = kind.getKind();
		if (modelingKind == null) {
			logger.warn("Missing ModelingKind attribute, fallback to 'Instance'");
			modelingKind = ModelingKind.INSTANCE;
		}
		UAVariable mappedKind = new I4AASEnumMapper(modelingKind, ctx).map();
		I4AASMapper.attachAsProperty(target, mappedKind);
	}

}
