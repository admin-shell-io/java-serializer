package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import javax.xml.bind.annotation.XmlEnumValue;

import org.mapstruct.EnumMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;
import org.opcfoundation.ua.i4aas.types.AASKeyDataType;
import org.opcfoundation.ua.i4aas.types.AASKeyElementsDataType;
import org.opcfoundation.ua.i4aas.types.AASKeyTypeDataType;

import io.adminshell.aas.v3.model.Key;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.KeyType;

@Mapper
public interface KeyMapper {
	
	@Mapping(source="value", target = "value")
	@Mapping(target = "type", ignore = true)//TODO
	AASKeyDataType toI4AASKey(Key key);
	
	@ValueMappings({
        @ValueMapping(source = "ID_SHORT", target = "ID_SHORT_0"),
        @ValueMapping(source = "FRAGMENT_ID", target = "FRAGMENT_ID_1"),
        @ValueMapping(source = "CUSTOM", target = "CUSTOM_2"),
        @ValueMapping(source = "IRDI", target = "IRDI_3"),
        @ValueMapping(source = "IRI", target = "IRI_4"),

	})
	AASKeyTypeDataType toI4AASKeyTypeEnum(KeyType type);

}
