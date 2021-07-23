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

		UAObject folder = I4AASMapper.createFolder(target, "DataSpecification", ctx);
		I4AASMapper.attachAsComponent(target, folder);

		List<EmbeddedDataSpecification> embeddedDataSpecifications = source.getEmbeddedDataSpecifications();
		for (int i = 0; i < embeddedDataSpecifications.size(); i++) {
			EmbeddedDataSpecification embeddedDataSpecification = embeddedDataSpecifications.get(i);
			Reference dataSpecification = embeddedDataSpecification.getDataSpecification();

			if (dataSpecification != null) {
				String dataSpecKey = dataSpecification.getKeys().get(0).getValue();
				UAObject uaObject = new ReferenceMapper(dataSpecification, ctx, dataSpecKey).map();
				I4AASMapper.attachAsComponent(folder, uaObject);
			}

			DataSpecificationContent dataSpecificationContent = embeddedDataSpecification.getDataSpecificationContent();
			if (dataSpecificationContent instanceof DataSpecificationIEC61360) {
				UAObject uaIEC61360 = new DataSpecificationIEC61360Mapper(
						(DataSpecificationIEC61360) dataSpecificationContent, ctx, "embedded_" + i).map();
				I4AASMapper.attachAsComponent(folder, uaIEC61360);
			}
		}
	}
}
