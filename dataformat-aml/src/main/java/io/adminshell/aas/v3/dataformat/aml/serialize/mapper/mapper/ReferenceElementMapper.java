package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AASNamespace;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellRoleClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util.ReferenceConverterUtil;
import io.adminshell.aas.v3.model.ReferenceElement;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class ReferenceElementMapper extends CustomMapper<ReferenceElement, InternalElement> {

    @Override
    public void mapAtoB(ReferenceElement referenceElement, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.ReferenceElement.getRefBaseRoleClassPath()));
        internalElement.getAttributes().add(new Attribute(
                "value",
                null,
                null,
                ReferenceConverterUtil.convert(referenceElement.getValue()),
                new RefSemantic(AASNamespace.ReferenceElement_Value.getRefSemantic()),
                null
        ));
    }
}
