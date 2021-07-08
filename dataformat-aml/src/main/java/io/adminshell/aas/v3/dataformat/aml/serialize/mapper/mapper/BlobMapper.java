/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import java.util.Arrays;

public class BlobMapper extends CustomMapper<Blob, InternalElement> {

    @Override
    public void mapAtoB(Blob blob, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.Blob.getRefBaseRoleClassPath()));

        ExternalInterface externalInterface = new ExternalInterface();
        externalInterface.setName("FileDataReference");
        externalInterface.setRefBaseClassPath(AssetAdministrationShellInterfaceClassLib.FileDataReference.getRefBaseRoleClassPath());
        externalInterface.setAttributes(new ArrayList<>());
        externalInterface.getAttributes().add(new Attribute(
                "MIMEType",
                "xs:anyURI",
                null,
                blob.getMimeType(),
                new RefSemantic(AASNamespace.Blob_MimeType.getRefSemantic()),
                null
        ));
        externalInterface.getAttributes().add(new Attribute(
                "refURI",
                "xs:string",
                null,
                Arrays.toString(blob.getValue()),
                new RefSemantic(AASNamespace.Blob_Value.getRefSemantic()),
                null
        ));

        if (internalElement.getExternalInterfaces() == null) {
            internalElement.setExternalInterfaces(new ArrayList<>());
        }
        internalElement.getExternalInterfaces().add(externalInterface);
    }
}
