package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.sme.FileMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;
import io.adminshell.aas.v3.model.AssetInformation;
import io.adminshell.aas.v3.model.AssetKind;
import io.adminshell.aas.v3.model.File;
import io.adminshell.aas.v3.model.IdentifierKeyValuePair;
import io.adminshell.aas.v3.model.Reference;

public class AssetInformationMapper extends I4AASMapper<AssetInformation, UAObject> {

	public AssetInformationMapper(AssetInformation src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		target = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString()).withBrowseName(createBrowseName("Asset"))
				.withDisplayName(createLocalizedText("Asset")).build();
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
			UAObject uaIdentification = new ReferenceMapper(globalAssetId, ctx, "GlobalAssetId").map();
			attachAsComponent(target, uaIdentification);
		}

		if (!source.getBillOfMaterials().isEmpty()) {
			Reference bom = source.getBillOfMaterials().get(0); // workaround, should be just one entry
			UAObject uaBom = new ReferenceMapper(bom, ctx, "BillOfMaterial").map();
			attachAsComponent(target, uaBom);
		}

		File defaultThumbnail = source.getDefaultThumbnail();
		if (defaultThumbnail != null) {
			UAObject uaThumbnail = new FileMapper(defaultThumbnail, ctx, "DefaultThumbnail").map();
			attachAsComponent(target, uaThumbnail);
		}

		UAObject folder = createFolder("SpecificAssetId");
		for (IdentifierKeyValuePair identifierKeyValuePair : source.getSpecificAssetIds()) {
			UAObject uaIdKVP = new IdentifierKeyValuePairMapper(identifierKeyValuePair, ctx).map();
			attachAsComponent(folder, uaIdKVP);
		}
	}


}
