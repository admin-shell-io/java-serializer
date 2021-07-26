package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.List;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.Constraint;
import io.adminshell.aas.v3.model.Qualifiable;
import io.adminshell.aas.v3.model.Qualifier;

public interface QualifiableMapper {

	public default void mapQualifiable(Qualifiable source, UAObject target, MappingContext ctx) {

		UAObject folder = I4AASMapper.createFolder(target, "Qualifier", ctx);

		List<Constraint> qualifiers = source.getQualifiers();
		for (int i = 0; i < qualifiers.size(); i++) {
			Constraint constraint = qualifiers.get(i);
			if (constraint instanceof Qualifier) {
				UAObject uaQualifier = new QualifierMapper((Qualifier) constraint, ctx, "qualifier_" + i, ctx.getModelNsIndex()).map();
				I4AASMapper.attachAsComponent(folder, uaQualifier);
			}
		}
	}
}
