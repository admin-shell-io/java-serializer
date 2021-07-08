package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AASNamespace;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellInterfaceClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellRoleClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.ExternalInterface;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.model.*;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

import java.util.ArrayList;

public class FileMapper extends CustomMapper<File, InternalElement> {

    @Override
    public void mapAtoB(File file, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.File.getRefBaseRoleClassPath()));

        ExternalInterface externalInterface = new ExternalInterface();
        externalInterface.setName("FileDataReference");
        externalInterface.setRefBaseClassPath(AssetAdministrationShellInterfaceClassLib.FileDataReference.getRefBaseRoleClassPath());
        externalInterface.setAttributes(new ArrayList<>());
        externalInterface.getAttributes().add(new Attribute(
                "MIMEType",
                "xs:anyURI",
                null,
                file.getMimeType(),
                new RefSemantic(AASNamespace.File_MimeType.getRefSemantic()),
                null
        ));
        externalInterface.getAttributes().add(new Attribute(
                "refURI",
                "xs:string",
                null,
                file.getValue(),
                new RefSemantic(AASNamespace.File_Value.getRefSemantic()),
                null
        ));

        if (internalElement.getExternalInterfaces() == null) {
            internalElement.setExternalInterfaces(new ArrayList<>());
        }
        internalElement.getExternalInterfaces().add(externalInterface);
    }
}
