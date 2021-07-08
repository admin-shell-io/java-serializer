package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellRoleClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.model.View;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class ViewMapper extends CustomMapper<View, InternalElement> {

    @Override
    public void mapAtoB(View view, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.View.getRefBaseRoleClassPath()));

        view.getContainedElements().forEach(containedElement -> {

        });
    }
}
