package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.ListOfReferences;
import org.opcfoundation.ua._2011._03.uanodeset.Reference;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASConstants;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.Submodel;

public class EnvironmentMapper extends I4AASMapper<AssetAdministrationShellEnvironment, UAObject> {

	public EnvironmentMapper(AssetAdministrationShellEnvironment aasEnvironment, MappingContext ctx) {
		super(aasEnvironment, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		target = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString())
				.withBrowseName(createModelBrowseName("AASEnvironment"))
				.withDisplayName(createLocalizedText("AASEnvironment"))
				.withReferences(new ListOfReferences()).build();
		addTypeReference(I4aasId.AASEnvironmentType);
		Reference orgaRef = new Reference();
		orgaRef.setReferenceType(UaId.Organizes.getName());
		orgaRef.setIsForward(false);
		orgaRef.setValue("i=85");
		target.getReferences().getReference().add(orgaRef);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		// AAS might depend on CD, so this must be done first!
		for (ConceptDescription conceptDescription : source.getConceptDescriptions()) {
			UAObject uaCD = new ConceptDescriptionMapper(conceptDescription, ctx).map();
			Reference orgaRef = new Reference();
			orgaRef.setReferenceType(UaId.Organizes.getName());
			orgaRef.setIsForward(false);
			orgaRef.setValue("i=17594");
			uaCD.getReferences().getReference().add(orgaRef);
		}
		for (Submodel submodel : source.getSubmodels()) {
			UAObject uaSubmodel = new SubmodelMapper(submodel, ctx).map();
			attachAsComponent(target, uaSubmodel);
		}
		for (AssetAdministrationShell assetAdministrationShell : source.getAssetAdministrationShells()) {
			UAObject aasUaObject = new AssetAdministrationShellMapper(assetAdministrationShell, ctx).map();
			attachAsComponent(target, aasUaObject);
		}
	}
}
