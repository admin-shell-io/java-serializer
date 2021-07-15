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
		
		IdentifierType sourceIdType = src.getIdentification().getIdType();
		String sourceIdentifierValue = src.getIdentification().getIdentifier();
		
		Builder<Void> coreUAObject = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString())
				.withDisplayName(I4AASUtils.createLocalizedText("Identification"))
				.withBrowseName(browseNameOf("Identification"));
		addTypeReference(coreUAObject.build(), I4aasId.AASIdentifierType);
		UAObject uaObject = coreUAObject.build();
		
		//create Properties
		//map id
		org.opcfoundation.ua._2011._03.uanodeset.UAVariable.Builder<Void> idVarBuilder = UAVariable.builder().withDisplayName(I4AASUtils.createLocalizedText("Id")).withDataType(UaId.String.getName())
				.withNodeId(ctx.newModelNodeIdAsString()).withBrowseName(browseNameOf("Id")).withAccessLevel(3L);
		addTypeReference(idVarBuilder.build(), UaId.PropertyType);
		JAXBElement<String> idStringValue = new ObjectFactory().createString(sourceIdentifierValue);
		UAVariable targetIdVar = idVarBuilder.withValue().withAny(idStringValue).end().build();
		//map idtype
		org.opcfoundation.ua._2011._03.uanodeset.UAVariable.Builder<Void> idTypeVarBuilder = UAVariable.builder().withDisplayName(I4AASUtils.createLocalizedText("IdType"))
				.withDataType(I4aasId.AASIdentifierTypeDataType.getName()).withNodeId(ctx.newModelNodeIdAsString())
				.withBrowseName(browseNameOf("IdType")).withAccessLevel(3L);
		addTypeReference(idTypeVarBuilder.build(), UaId.PropertyType);
		JAXBElement<Integer> targetIdTypeVar2 = new ObjectFactory().createInt32(sourceIdType.ordinal());
		UAVariable targetIdTypeVar = idTypeVarBuilder.withValue().withAny(targetIdTypeVar2).end().build();
		
		// attach Properties
		attachAsProperty(uaObject, targetIdVar);
		attachAsProperty(uaObject, targetIdTypeVar);

	}

}
