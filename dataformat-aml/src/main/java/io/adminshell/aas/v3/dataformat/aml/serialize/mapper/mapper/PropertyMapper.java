package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AASNamespace;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellRoleClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.model.Property;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class PropertyMapper extends CustomMapper<Property, InternalElement> {

    @Override
    public void mapAtoB(Property property, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.Property.getRefBaseRoleClassPath()));
        internalElement.getAttributes().add(new Attribute(
                "value",
                "xs:" + property.getValueType(),
                null,
                property.getValue(),
                new RefSemantic(AASNamespace.Property_Value.getRefSemantic()),
                null
        ));
    }
}
