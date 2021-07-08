package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellRoleClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.model.SubmodelElementCollection;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class SubmodelElementCollectionMapper extends CustomMapper<SubmodelElementCollection, InternalElement> {

    @Override
    public void mapAtoB(SubmodelElementCollection submodelElementCollection, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.SubmodelElementCollection.getRefBaseRoleClassPath()));
    }
}
