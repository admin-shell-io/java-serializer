package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.AdministrativeInformation;

public class AdministrationMapper extends I4AASMapper<AdministrativeInformation, UAObject> implements HasDataSpecificationMapper {

	public AdministrationMapper(AdministrativeInformation src, MappingContext ctx) {
		super(src, ctx);
	}

	@Override
	protected UAObject createTargetObject() {
		target = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString())
		.withBrowseName(createBrowseName("Administration")).withDisplayName(createLocalizedText("Administration")).build();
		addTypeReference(I4aasId.AASAdministrativeInformationType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		String revision = source.getRevision();
		if(revision != null) {
			UAVariable revisionStringProperty = new StringPropertyMapper("Revision", revision, ctx).map();
			attachAsProperty(target, revisionStringProperty);
		}
		String version = source.getVersion();
		if(version != null) {
			UAVariable versionStringProperty = new StringPropertyMapper("Version", version, ctx).map();
			attachAsProperty(target, versionStringProperty);
		}
		mapDataSpecification(source, target, ctx);
	}

}
