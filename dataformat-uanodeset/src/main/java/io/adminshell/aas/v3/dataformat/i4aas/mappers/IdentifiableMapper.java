package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import javax.xml.bind.JAXBElement;

import org.opcfoundation.ua._2008._02.types.ObjectFactory;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject.Builder;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;
import io.adminshell.aas.v3.model.AdministrativeInformation;
import io.adminshell.aas.v3.model.Identifiable;
import io.adminshell.aas.v3.model.IdentifierType;

public class IdentifiableMapper<T extends Identifiable> extends ReferableMapper<T> {

	public IdentifiableMapper(T src, MappingContext ctx) {
		super(src, ctx);
	}
	
	@Override
	protected UAObject createTargetObject() {
		// TODO Auto-generated method stub
		return super.createTargetObject();
	}
	
	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();
		
		IdentifierType sourceIdType = source.getIdentification().getIdType();
		String sourceIdentifierValue = source.getIdentification().getIdentifier();
		
		Builder<Void> coreUAObject = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString())
				.withDisplayName(I4AASUtils.createLocalizedText("Identification"))
				.withBrowseName(browseNameOf("Identification"));
		UAObject uaObject = coreUAObject.build();
		addTypeReference(uaObject, I4aasId.AASIdentifierType);
		addToNodeset(uaObject);
		attachAsComponent(target, uaObject);

		UAVariable targetIdVar = newStringProperty("Id", sourceIdentifierValue);
		addToNodeset(targetIdVar);
		attachAsProperty(uaObject, targetIdVar);
		
		UAVariable mappedEnum = new I4AASEnumMapper(sourceIdType, ctx).map();
		attachAsProperty(uaObject, mappedEnum);
		
		UAObject uaAdministration = new AdministrationMapper(source.getAdministration(), ctx).map();
		attachAsComponent(target, uaAdministration);
	}



}
