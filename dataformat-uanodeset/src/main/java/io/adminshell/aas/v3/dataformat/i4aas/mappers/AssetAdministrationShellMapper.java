package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.opcfoundation.ua._2011._03.uanodeset.ListOfReferences;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject.Builder;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetInformation;
import io.adminshell.aas.v3.model.Key;
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
		mapAsset();
		mapSubmodels();
		mapDerivedFrom();
	}

	private void mapAsset() {
		AssetInformation assetInformation = source.getAssetInformation();
		UAObject uaAsset = new AssetInformationMapper(assetInformation, ctx).map();
		attachAsComponent(target, uaAsset);
	}

	private void mapSubmodels() {
		List<io.adminshell.aas.v3.model.Reference> submodels = source.getSubmodels();
		for (int i = 0; i < submodels.size(); i++) {
			io.adminshell.aas.v3.model.Reference reference = submodels.get(i);
			Submodel resolvedSubmodel = ctx.resolveSubmodelReference(reference);
			if (resolvedSubmodel != null) {
				UAObject createSubmodelUaObject = new SubmodelMapper(resolvedSubmodel, ctx).map();
				attachAsComponent(target, createSubmodelUaObject);
			}
			UAObject createSubmodelReferenceUaObject = new ReferenceMapper(reference, ctx, "SubmodelReference_" + i)
					.map();
			attachAsComponent(target, createSubmodelReferenceUaObject);
		}
	}

	private void mapDerivedFrom() {
		io.adminshell.aas.v3.model.Reference derivedFrom = source.getDerivedFrom();
		if (derivedFrom != null) {
			UAObject uaDerivedFrom = new ReferenceMapper(derivedFrom, ctx, "DerivedFrom").map();
			attachAsComponent(target, uaDerivedFrom);
		}
	}
}
