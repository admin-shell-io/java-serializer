package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AASNamespace;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import io.adminshell.aas.v3.model.AdministrativeInformation;
import java.util.ArrayList;
import java.util.List;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class AdministrativeInformationToAttributeConverter extends CustomConverter<AdministrativeInformation, Attribute> {

    @Override
    public Attribute convert(AdministrativeInformation source, Type<? extends Attribute> destinationType, MappingContext mappingContext) {
        // Root attribute
        Attribute attribute = new Attribute(
                "administration",
                null,
                null,
                null,
                new RefSemantic(AASNamespace.Identifiable_Administration.getRefSemantic()),
                null
        );

        // Nested attributes
        List<Attribute> nestedAttributes = new ArrayList<>();
        nestedAttributes.add(new Attribute(
                "version",
                null,
                null,
                source.getVersion(),
                new RefSemantic(AASNamespace.AdministrationInformation_Version.getRefSemantic()),
                null
        ));
        nestedAttributes.add(new Attribute(
                "revision",
                null,
                null,
                source.getRevision(),
                new RefSemantic(AASNamespace.AdministrationInformation_Revision.getRefSemantic()),
                null)
        );
        attribute.setAttributes(nestedAttributes);

        return attribute;
    }
}
