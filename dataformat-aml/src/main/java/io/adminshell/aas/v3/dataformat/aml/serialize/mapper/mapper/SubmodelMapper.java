package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellRoleClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util.QualifierConverterUtil;
import io.adminshell.aas.v3.model.Submodel;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class SubmodelMapper extends CustomMapper<Submodel, InternalElement> {

    @Override
    public void mapAtoB(Submodel submodel, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.Submodel.getRefBaseRoleClassPath()));
        QualifierConverterUtil.createQualifierAttributesForSubmodel(submodel, internalElement);
    }
}
