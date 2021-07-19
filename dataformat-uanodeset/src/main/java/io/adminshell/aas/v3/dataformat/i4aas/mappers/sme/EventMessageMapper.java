package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.ReferableMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.EventMessage;

public class EventMessageMapper extends SubmodelElementMapper<EventMessage> {

	public EventMessageMapper(EventMessage src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		throw new UnsupportedOperationException("Unknown Target Type.");
		// addTypeReference(I4aasId.???);
		// return target;
	}
}
