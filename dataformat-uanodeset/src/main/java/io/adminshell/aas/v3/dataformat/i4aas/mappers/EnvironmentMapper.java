package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.List;

import org.opcfoundation.ua._2011._03.uanodeset.ListOfReferences;
import org.opcfoundation.ua._2011._03.uanodeset.LocalizedText;
import org.opcfoundation.ua._2011._03.uanodeset.Reference;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject.Builder;

import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
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
			UAObject aasUaObject = new UAObject();
			aasUaObject.getSymbolicName().add(assetAdministrationShell.getIdShort());
			aasUaObject.setNodeId(ctx.newNodeId());
			aasUaObject.setBrowseName("" + ctx.getModelNsIndex() + ":AAS:" + assetAdministrationShell.getIdShort());

			LocalizedText localizedText = new LocalizedText();
			localizedText.setValue("AAS:" + assetAdministrationShell.getIdShort());
			aasUaObject.getDisplayName().add(localizedText);
			aasUaObject.setReferences(new ListOfReferences());

			Reference orgaRef = new Reference();
			orgaRef.setReferenceType(UaId.Organizes.getName());
			orgaRef.setIsForward(false);
			orgaRef.setValue("i=85");
			aasUaObject.getReferences().getReference().add(orgaRef);

			Reference typeRef = new Reference();
			typeRef.setReferenceType(UaId.HasTypeDefinition.getName());
			typeRef.setValue(ctx.getI4aasNodeId(I4aasId.AASAssetAdministrationShellType));
			aasUaObject.getReferences().getReference().add(typeRef);

			// map derived from
			io.adminshell.aas.v3.model.Reference derivedFrom = assetAdministrationShell.getDerivedFrom();
			if (derivedFrom != null) {
				UAObject createDerivedFromUaObject = createDerivedFromUaObject();
				attach(aasUaObject, createDerivedFromUaObject);
			}

			// map Submodels
			List<io.adminshell.aas.v3.model.Reference> submodels = assetAdministrationShell.getSubmodels();
			for (io.adminshell.aas.v3.model.Reference reference : submodels) {
				Submodel resolvedSubmodel = ctx.resolveSubmodelReference(reference);
				if (resolvedSubmodel != null) {
					UAObject createSubmodelUaObject = createSubmodelUaObject();
					attach(aasUaObject, createSubmodelUaObject);
				}
			}

			ctx.getNodeSet().getUAObjectOrUAVariableOrUAMethod().add(aasUaObject);
		}

		return ctx.getNodeSet();
	}

	private UAObject createSubmodelUaObject() {
		// TODO Auto-generated method stub
		return null;
	}

	private void attach(UAObject parent, UAObject child) {
		child.setParentNodeId(parent.getNodeId());
		if (child.getReferences() == null) {
			child.setReferences(new ListOfReferences());
		}
		Reference parentRef = Reference.builder().withIsForward(false).withReferenceType(UaId.HasComponent.getName()).withValue(parent.getNodeId()).build();
		child.getReferences().getReference().add(parentRef);
		if (parent.getReferences() == null) {
			parent.setReferences(new ListOfReferences());
		}
		Reference childRef = Reference.builder().withReferenceType(UaId.HasComponent.getName()).withValue(child.getNodeId()).build();
		parent.getReferences().getReference().add(childRef);
		ctx.getNodeSet().getUAObjectOrUAVariableOrUAMethod().add(child);
	}

	private UAObject createDerivedFromUaObject() {
		Builder<Void> build = UAObject.builder()//
				.withNodeId(ctx.newNodeId())//
				.addDisplayName(createLocalizedText("DerivedFrom"))//
				.withReferences(ListOfReferences.builder().addReference()//
						.withReferenceType(UaId.HasTypeDefinition.getName()).withValue(ctx.getI4aasNodeId(I4aasId.AASReferenceType))//
						.end().build())
				.withBrowseName("" + ctx.getI4aasNsIndex() + ":DerivedFrom");
		return build.build();
	}

	private LocalizedText createLocalizedText(String value) {
		return LocalizedText.builder().withValue(value).build();
	}

}
