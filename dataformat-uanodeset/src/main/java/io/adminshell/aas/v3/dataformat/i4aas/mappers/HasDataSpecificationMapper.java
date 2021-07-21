package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.List;

import org.opcfoundation.ua._2011._03.uanodeset.ListOfReferences;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;
import io.adminshell.aas.v3.model.DataSpecificationContent;
import io.adminshell.aas.v3.model.DataSpecificationIEC61360;
import io.adminshell.aas.v3.model.EmbeddedDataSpecification;
import io.adminshell.aas.v3.model.HasDataSpecification;
import io.adminshell.aas.v3.model.Reference;

public interface HasDataSpecificationMapper {

	public default void mapDataSpecification(HasDataSpecification source, UAObject target, MappingContext ctx) {
		List<EmbeddedDataSpecification> embeddedDataSpecifications = source.getEmbeddedDataSpecifications();
		for (int i = 0; i < embeddedDataSpecifications.size(); i++) {
			EmbeddedDataSpecification embeddedDataSpecification = embeddedDataSpecifications.get(i);
			Reference dataSpecification = embeddedDataSpecification.getDataSpecification();
			DataSpecificationContent dataSpecificationContent = embeddedDataSpecification.getDataSpecificationContent();
			if (dataSpecificationContent instanceof DataSpecificationIEC61360) {
				UAObject uaIEC61360 = new DataSpecificationIEC61360Mapper(
						(DataSpecificationIEC61360) dataSpecificationContent, ctx, "dataSpecification_" + i).map();
				uaIEC61360.setParentNodeId(target.getNodeId());
				if (uaIEC61360.getReferences() == null) {
					uaIEC61360.setReferences(new ListOfReferences());
				}
				org.opcfoundation.ua._2011._03.uanodeset.Reference parentRef = org.opcfoundation.ua._2011._03.uanodeset.Reference
						.builder().withIsForward(false).withReferenceType(UaId.HasComponent.getName())
						.withValue(target.getNodeId()).build();
				uaIEC61360.getReferences().getReference().add(parentRef);
				if (target.getReferences() == null) {
					target.setReferences(new ListOfReferences());
				}
				org.opcfoundation.ua._2011._03.uanodeset.Reference childRef = org.opcfoundation.ua._2011._03.uanodeset.Reference
						.builder().withReferenceType(UaId.HasComponent.getName()).withValue(uaIEC61360.getNodeId())
						.build();
				target.getReferences().getReference().add(childRef);
			}
		}
	}
}
