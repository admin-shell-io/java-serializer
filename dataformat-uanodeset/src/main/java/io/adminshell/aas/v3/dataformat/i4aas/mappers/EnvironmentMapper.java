package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.Reference;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.ConceptDescription;

public class EnvironmentMapper {

	private MappingContext ctx;
	private AssetAdministrationShellEnvironment aasEnvironment;

	public EnvironmentMapper(AssetAdministrationShellEnvironment aasEnvironment, MappingContext ctx) {
		this.aasEnvironment = aasEnvironment;
		this.ctx = ctx;
	}

	public UANodeSet toI4AAS() {
		//AAS depends on CD, so this must be done first!
		for (ConceptDescription conceptDescription : aasEnvironment.getConceptDescriptions()) {
			UAObject uaCD = new ConceptDescriptionMapper(conceptDescription, ctx).map();
			Reference orgaRef = new Reference();
			orgaRef.setReferenceType(UaId.Organizes.getName());
			orgaRef.setIsForward(false);
			orgaRef.setValue("i=17594");
			uaCD.getReferences().getReference().add(orgaRef);
		}
		for (AssetAdministrationShell assetAdministrationShell : aasEnvironment.getAssetAdministrationShells()) {
			UAObject aasUaObject = new AssetAdministrationShellMapper(assetAdministrationShell, ctx).map();
			aasUaObject.getSymbolicName().add(assetAdministrationShell.getIdShort());
			Reference orgaRef = new Reference();
			orgaRef.setReferenceType(UaId.Organizes.getName());
			orgaRef.setIsForward(false);
			orgaRef.setValue("i=85");
			aasUaObject.getReferences().getReference().add(orgaRef);
		}
		
		
		return ctx.getNodeSet();
	}
}
