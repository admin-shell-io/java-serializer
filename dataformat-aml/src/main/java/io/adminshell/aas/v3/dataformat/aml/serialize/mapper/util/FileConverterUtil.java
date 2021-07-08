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
package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AASNamespace;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import io.adminshell.aas.v3.model.File;

import java.util.ArrayList;

public class FileConverterUtil {

    public static Attribute convert(File file, String rootName, RefSemantic rootRefSemantic) {
        if (file == null) {
            return null;
        }

        Attribute attribute = new Attribute(
                rootName,
                null,
                null,
                null,
                rootRefSemantic,
                null
        );
        attribute.setAttributes(new ArrayList<>());

        attribute.getAttributes().add(new Attribute(
                "MIMEType",
                "xs:anyURI",
                null,
                file.getMimeType(),
                new RefSemantic(AASNamespace.File_MimeType.getRefSemantic()),
                null
        ));
        attribute.getAttributes().add(new Attribute(
                "refURI",
                "xs:string",
                null,
                file.getValue(),
                new RefSemantic(AASNamespace.File_Value.getRefSemantic()),
                null
        ));

        return attribute;
    }
}
