package io.adminshell.aas.v3.dataformat.i4aas.mappers.sme;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.StringPropertyMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.File;

public class FileMapper extends SubmodelElementMapper<File> {

	private String customName;

	public FileMapper(File src, MappingContext ctx) {
		this(src, ctx, src.getIdShort());
	}

	public FileMapper(File src, MappingContext ctx, String name) {
		super(src, ctx);
		this.customName = name;
	}

	@Override
	protected UAObject createTargetObject() {
		target = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString()).withBrowseName(createBrowseName(customName))
				.withDisplayName(createLocalizedText(customName)).build();
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
