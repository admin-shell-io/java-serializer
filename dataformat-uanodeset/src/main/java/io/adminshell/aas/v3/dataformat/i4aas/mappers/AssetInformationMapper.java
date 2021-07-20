package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.AssetInformation;

public class AssetInformationMapper extends I4AASMapper<AssetInformation, UAObject> {

	public AssetInformationMapper(AssetInformation src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		target = UAObject.builder().build();
		//TODO
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		//TODO
	}

}
