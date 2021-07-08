package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellRoleClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.model.*;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class AnnotatedRelationshipElementMapper extends CustomMapper<AnnotatedRelationshipElement, InternalElement> {

    @Override
    public void mapAtoB(AnnotatedRelationshipElement annotatedRelationshipElement, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.AnnotatedRelationshipElement.getRefBaseRoleClassPath()));
    }
}
