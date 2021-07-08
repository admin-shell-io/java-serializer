package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AASNamespace;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellRoleClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util.ReferenceConverterUtil;
import io.adminshell.aas.v3.model.*;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

import java.util.ArrayList;

public class AssetAdministrationShellMapper extends CustomMapper<AssetAdministrationShell, InternalElement> {

    @Override
    public void mapAtoB(AssetAdministrationShell assetAdministrationShell, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.AssetAdministrationShell.getRefBaseRoleClassPath()));

        // asset information
        // submodel references
        Attribute submodels = new Attribute(
                "submodels",
                null,
                null,
                null,
                new RefSemantic(AASNamespace.AssetAdministrationShell_Submodels.getRefSemantic()),
                null
        );
        submodels.setAttributes(new ArrayList<>());
        assetAdministrationShell.getSubmodels().forEach(submodel -> {
            submodels.getAttributes().add(new Attribute(
                    "reference",
                    null,
                    null,
                    ReferenceConverterUtil.convert(submodel),
                    new RefSemantic(AASNamespace.ReferenceElement_Value.getRefSemantic()),
                    null
            ));
        });
        internalElement.getAttributes().add(submodels);
    }
}
