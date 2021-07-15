package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.List;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.Submodel;

public class AssetAdministrationShellMapper extends IdentifiableMapper<AssetAdministrationShell> {

	public AssetAdministrationShellMapper(AssetAdministrationShell src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		addTypeReference(I4aasId.AASAssetAdministrationShellType);
		return result;
	}

	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();
		
		// map Submodels and/or Submodel References
		List<io.adminshell.aas.v3.model.Reference> submodels = src.getSubmodels();
		for (io.adminshell.aas.v3.model.Reference reference : submodels) {
			Submodel resolvedSubmodel = ctx.resolveSubmodelReference(reference);
			if (resolvedSubmodel != null) {
				UAObject createSubmodelUaObject = new SubmodelMapper(resolvedSubmodel, ctx).map();
				attachAsComponent(result, createSubmodelUaObject);
			} else {
				// createSubmodelReference(); TODO
			}
		}
	}

}
