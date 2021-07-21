package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

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
		
		//no mapping possible with current I4AAS specification
		String mimeType = source.getMimeType();
		byte[] value = source.getValue();
		
		addTypeReference(I4aasId.AASBlobType);
		return target;
	}

}
