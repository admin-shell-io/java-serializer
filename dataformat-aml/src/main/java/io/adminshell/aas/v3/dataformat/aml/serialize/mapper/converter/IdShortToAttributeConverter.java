package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AASNamespace;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class IdShortToAttributeConverter extends CustomConverter<String, Attribute> {

    @Override
    public Attribute convert(String source, Type<? extends Attribute> destinationType, MappingContext mappingContext) {
        return new Attribute(
                "idShort",
                null,
                null,
                source,
                new RefSemantic(AASNamespace.Referable_IdShort.getRefSemantic()),
                null
        );
    }
}
