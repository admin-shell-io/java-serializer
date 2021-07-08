package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellRoleClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.model.*;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

import java.util.ArrayList;
import java.util.List;

public class EntityMapper extends CustomMapper<Entity, InternalElement> {

    @Override
    public void mapAtoB(Entity entity, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.Entity.getRefBaseRoleClassPath()));

        List<InternalElement> nested = new ArrayList<>();

        entity.getStatements().forEach(submodelElement -> {
            InternalElement statementInternalElement = new InternalElement();
            nested.add(statementInternalElement);
        });

        internalElement.setInternalElements(nested);
    }
}
