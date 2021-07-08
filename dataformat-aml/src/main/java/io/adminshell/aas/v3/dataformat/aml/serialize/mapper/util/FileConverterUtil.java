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
