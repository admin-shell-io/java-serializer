package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.ByteStringPropertyMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.StringPropertyMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.Blob;

public class BlobMapper extends SubmodelElementMapper<Blob> {

	public BlobMapper(Blob src, MappingContext ctx) {
		super(src, ctx);
	}
	
	@Override
	protected UAObject createTargetObject() {
		super.createTargetObject();
		addTypeReference(I4aasId.AASBlobType);
		return target;
	}
	
	@Override
	protected void mapAndAttachChildren() {
		super.mapAndAttachChildren();
		String mimeType = source.getMimeType();
		if (mimeType != null) {
			UAVariable map = new MimeTypeMapper(mimeType, ctx).map();
			attachAsProperty(target, map);
		}
		byte[] value = source.getValue();
		if (value != null) {
			UAVariable map = new ByteStringPropertyMapper("Value", value, ctx).map();
			attachAsProperty(target, map);
		}
	}

}
