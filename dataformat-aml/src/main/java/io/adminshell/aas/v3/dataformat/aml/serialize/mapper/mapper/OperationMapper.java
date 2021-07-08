package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellRoleClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.model.Operation;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import java.util.ArrayList;

public class OperationMapper extends CustomMapper<Operation, InternalElement> {

    @Override
    public void mapAtoB(Operation operation, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.Operation.getRefBaseRoleClassPath()));

        InternalElement inputVar = new InternalElement();
        inputVar.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.OperationInputVar.getRefBaseRoleClassPath()));

        InternalElement outputVar = new InternalElement();
        outputVar.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.OperationOutputVar.getRefBaseRoleClassPath()));

        InternalElement inoutVar = new InternalElement();
        inoutVar.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.OperationInOutputVar.getRefBaseRoleClassPath()));

        internalElement.setInternalElements(new ArrayList<>());
        internalElement.getInternalElements().add(inputVar);
        internalElement.getInternalElements().add(outputVar);
        internalElement.getInternalElements().add(inoutVar);
    }
}
