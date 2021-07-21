package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.StringPropertyMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.File;

public class FileMapper extends SubmodelElementMapper<File> {

	public FileMapper(File src, MappingContext ctx) {
		super(src, ctx);
	}
	
	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		addTypeReference(I4aasId.AASFileType);
		return target;
	}
	
	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();
		if (source.getMimeType() != null) {
			UAVariable map = new MimeTypeMapper(source.getMimeType(), ctx).map();
			attachAsProperty(target, map);
		}
		if (source.getValue() != null) {
			UAVariable map = new StringPropertyMapper("Value", source.getValue(), ctx).map();
			attachAsProperty(target, map);
		}
	}

}
