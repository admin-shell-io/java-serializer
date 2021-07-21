package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.IdentifierType;

public class ConceptDescriptionMapper extends IdentifiableMapper<ConceptDescription> implements HasDataSpecificationMapper {

	public ConceptDescriptionMapper(ConceptDescription src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		target = super.createTargetObject();

		IdentifierType idType = source.getIdentification().getIdType();
		if (IdentifierType.IRI == idType) {
			addTypeReference(I4aasId.AASIriConceptDescriptionType);
		} else if (IdentifierType.IRDI == idType) {
			addTypeReference(I4aasId.AASIrdiConceptDescriptionType);
		} else if (IdentifierType.CUSTOM == idType) {
			addTypeReference(I4aasId.AASCustomConceptDescriptionType);
		}
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();

		mapDataSpecification(source, target, ctx);

		// not part of I4AAS V2 so far
		source.getIsCaseOfs();
		// TODO
	}


}
