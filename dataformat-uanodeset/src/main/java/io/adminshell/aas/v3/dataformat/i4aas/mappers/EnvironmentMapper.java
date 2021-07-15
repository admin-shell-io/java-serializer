package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.opcfoundation.ua._2008._02.types.ObjectFactory;
import org.opcfoundation.ua._2011._03.uanodeset.ListOfReferences;
import org.opcfoundation.ua._2011._03.uanodeset.LocalizedText;
import org.opcfoundation.ua._2011._03.uanodeset.Reference;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable.Value;
import org.opcfoundation.ua.i4aas.types.AASIdentifierTypeDataType;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject.Builder;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.Identifiable;
import io.adminshell.aas.v3.model.Identifier;
import io.adminshell.aas.v3.model.IdentifierType;
import io.adminshell.aas.v3.model.Referable;
import io.adminshell.aas.v3.model.Submodel;

public class EnvironmentMapper {

	private MappingContext ctx;
	private AssetAdministrationShellEnvironment aasEnvironment;

	public EnvironmentMapper(AssetAdministrationShellEnvironment aasEnvironment, MappingContext ctx) {
		this.aasEnvironment = aasEnvironment;
		this.ctx = ctx;
	}

	public UANodeSet toI4AAS() {
		List<AssetAdministrationShell> assetAdministrationShells = aasEnvironment.getAssetAdministrationShells();
		for (AssetAdministrationShell assetAdministrationShell : assetAdministrationShells) {
			
			UAObject aasUaObject = new AssetAdministrationShellMapper(assetAdministrationShell, ctx).map();
			aasUaObject.getSymbolicName().add(assetAdministrationShell.getIdShort());
			Reference orgaRef = new Reference();
			orgaRef.setReferenceType(UaId.Organizes.getName());
			orgaRef.setIsForward(false);
			orgaRef.setValue("i=85");
			aasUaObject.getReferences().getReference().add(orgaRef);

			// map identifier
			UAObject createIdentifierUaObject = createIdentifierUaObject(assetAdministrationShell);
			attachAsComponent(aasUaObject, createIdentifierUaObject);

			// map derived from
			io.adminshell.aas.v3.model.Reference derivedFrom = assetAdministrationShell.getDerivedFrom();
			if (derivedFrom != null) {
				UAObject createDerivedFromUaObject = createDerivedFromUaObject(derivedFrom);
				attachAsComponent(aasUaObject, createDerivedFromUaObject);
			}


		}
		return ctx.getNodeSet();
	}

	private UAObject createIdentifierUaObject(Identifiable identification) {
		IdentifierType sourceIdType = identification.getIdentification().getIdType();
		String sourceIdentifierValue = identification.getIdentification().getIdentifier();
		Builder<Void> coreUAObject = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString())
				.withDisplayName(I4AASUtils.createLocalizedText("Identification"))
				.withBrowseName(browseNameOf("Identification"));
		addTypeReference(coreUAObject, I4aasId.AASIdentifierType);
		UAObject uaObject = coreUAObject.build();
		
		//create Properties
		//map id
		org.opcfoundation.ua._2011._03.uanodeset.UAVariable.Builder<Void> idVarBuilder = UAVariable.builder().withDisplayName(I4AASUtils.createLocalizedText("Id")).withDataType(UaId.String.getName())
				.withNodeId(ctx.newModelNodeIdAsString()).withBrowseName(browseNameOf("Id")).withAccessLevel(3L);
		addTypeReference(idVarBuilder, ctx.getUaBaseNodeIdAsString(UaId.PropertyType));
		JAXBElement<String> idStringValue = new ObjectFactory().createString(sourceIdentifierValue);
		UAVariable targetIdVar = idVarBuilder.withValue().withAny(idStringValue).end().build();
		//map idtype
		org.opcfoundation.ua._2011._03.uanodeset.UAVariable.Builder<Void> idTypeVarBuilder = UAVariable.builder().withDisplayName(I4AASUtils.createLocalizedText("IdType"))
				.withDataType(I4aasId.AASIdentifierTypeDataType.getName()).withNodeId(ctx.newModelNodeIdAsString())
				.withBrowseName(browseNameOf("IdType")).withAccessLevel(3L);
		addTypeReference(idTypeVarBuilder, ctx.getUaBaseNodeIdAsString(UaId.PropertyType));
		JAXBElement<Integer> targetIdTypeVar2 = new ObjectFactory().createInt32(sourceIdType.ordinal());
		UAVariable targetIdTypeVar = idTypeVarBuilder.withValue().withAny(targetIdTypeVar2).end().build();
		
		// attach Properties
		attachAsProperty(uaObject, targetIdVar);
		attachAsProperty(uaObject, targetIdTypeVar);

		return uaObject;
	}


	private void addTypeReference(Builder<Void> coreUAObject, I4aasId idForType) {
		coreUAObject.withReferences().addReference().withReferenceType(UaId.HasTypeDefinition.getName())
				.withValue(ctx.getI4aasNodeIdAsString(idForType));
	}
	
	
	private void addTypeReference(org.opcfoundation.ua._2011._03.uanodeset.UAVariable.Builder<Void> uaCoreVar,
			String uaBaseNodeIdAsString) {
		uaCoreVar.withReferences().addReference().withReferenceType(UaId.HasTypeDefinition.getName())
		.withValue(uaBaseNodeIdAsString);		
	}
	

	private Builder<Void> builderForReferables(Referable source) {
		Builder<Void> coreUAObject = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString())
				.withBrowseName(browseNameOf(source)).withDisplayName(I4AASUtils.createDisplayName(source));
		return coreUAObject;
	}

	private String browseNameOf(Referable source) {
		return browseNameOf(source.getIdShort());
	}

	private String browseNameOf(String value) {
		return ctx.getModelNsIndex() + ":" + value;
	}

	private void attachAsProperty(UAObject parent, UAVariable child) {
		child.setParentNodeId(parent.getNodeId());
		if (child.getReferences() == null) {
			child.setReferences(new ListOfReferences());
		}
		Reference parentRef = Reference.builder().withIsForward(false).withReferenceType(UaId.HasProperty.getName())
				.withValue(parent.getNodeId()).build();
		child.getReferences().getReference().add(parentRef);
		if (parent.getReferences() == null) {
			parent.setReferences(new ListOfReferences());
		}
		Reference childRef = Reference.builder().withReferenceType(UaId.HasProperty.getName())
				.withValue(child.getNodeId()).build();
		parent.getReferences().getReference().add(childRef);
		ctx.getNodeSet().getUAObjectOrUAVariableOrUAMethod().add(child);		
	}
	
	private void attachAsComponent(UAObject parent, UAObject child) {
		child.setParentNodeId(parent.getNodeId());
		if (child.getReferences() == null) {
			child.setReferences(new ListOfReferences());
		}
		Reference parentRef = Reference.builder().withIsForward(false).withReferenceType(UaId.HasComponent.getName())
				.withValue(parent.getNodeId()).build();
		child.getReferences().getReference().add(parentRef);
		if (parent.getReferences() == null) {
			parent.setReferences(new ListOfReferences());
		}
		Reference childRef = Reference.builder().withReferenceType(UaId.HasComponent.getName())
				.withValue(child.getNodeId()).build();
		parent.getReferences().getReference().add(childRef);
		ctx.getNodeSet().getUAObjectOrUAVariableOrUAMethod().add(child);
	}

	private UAObject createDerivedFromUaObject(io.adminshell.aas.v3.model.Reference derivedFrom) {
		Builder<Void> build = UAObject.builder()//
				.withNodeId(ctx.newModelNodeIdAsString())//
				.addDisplayName(I4AASUtils.createLocalizedText("DerivedFrom"))//
				.withReferences(ListOfReferences.builder().addReference()//
						.withReferenceType(UaId.HasTypeDefinition.getName())
						.withValue(ctx.getI4aasNodeIdAsString(I4aasId.AASReferenceType))//
						.end().build())
				.withBrowseName("" + ctx.getI4aasNsIndex() + ":DerivedFrom");
		return build.build();
	}

}
