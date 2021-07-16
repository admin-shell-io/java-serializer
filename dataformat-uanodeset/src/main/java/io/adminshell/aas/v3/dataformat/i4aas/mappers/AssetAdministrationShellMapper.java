package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.List;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetInformation;
import io.adminshell.aas.v3.model.ModelingKind;
import io.adminshell.aas.v3.model.Submodel;

public class AssetAdministrationShellMapper extends IdentifiableMapper<AssetAdministrationShell> {

	public AssetAdministrationShellMapper(AssetAdministrationShell src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		addTypeReference(I4aasId.AASAssetAdministrationShellType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();
		
		// map Submodels and/or Submodel References
		List<io.adminshell.aas.v3.model.Reference> submodels = source.getSubmodels();
		for (io.adminshell.aas.v3.model.Reference reference : submodels) {
			Submodel resolvedSubmodel = ctx.resolveSubmodelReference(reference);
			if (resolvedSubmodel != null) {
				UAObject createSubmodelUaObject = new SubmodelMapper(resolvedSubmodel, ctx).map();
				attachAsComponent(target, createSubmodelUaObject);
			} else {
				// createSubmodelReference(); TODO
			}
		}
	}

}
