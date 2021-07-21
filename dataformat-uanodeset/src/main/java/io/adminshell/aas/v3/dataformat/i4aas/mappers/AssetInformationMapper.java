package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.AssetInformation;
import io.adminshell.aas.v3.model.AssetKind;
import io.adminshell.aas.v3.model.Reference;

public class AssetInformationMapper extends I4AASMapper<AssetInformation, UAObject> {

	public AssetInformationMapper(AssetInformation src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		target = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString()).withBrowseName(browseNameOf("Asset")).withDisplayName(I4AASUtils.createLocalizedText("Asset")).build();
		addTypeReference(I4aasId.AASAssetType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		AssetKind assetKind = source.getAssetKind();
		UAVariable uaAssetKind = new I4AASEnumMapper(assetKind, ctx).map();
		attachAsProperty(target, uaAssetKind);
		
		Reference globalAssetId = source.getGlobalAssetId();
		if (globalAssetId != null) {
			UAObject uaIdentification = new ReferenceMapper(globalAssetId, ctx, "AssetIdentificationModel").map();
			attachAsComponent(target, uaIdentification);
		}
		
		if (!source.getBillOfMaterials().isEmpty()) {
			Reference bom = source.getBillOfMaterials().get(0);
			UAObject uaBom = new ReferenceMapper(bom, ctx, "BillOfMaterial").map();
			attachAsComponent(target, uaBom);
		}
		
		//not part of I4AAS so far, since it is based on V2
		source.getDefaultThumbnail();
		source.getSpecificAssetIds();
		//TODO
	}

}
