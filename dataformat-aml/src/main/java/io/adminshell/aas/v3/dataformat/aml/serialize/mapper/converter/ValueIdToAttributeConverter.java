package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AASNamespace;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util.ReferenceConverterUtil;
import io.adminshell.aas.v3.model.*;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class ValueIdToAttributeConverter extends CustomConverter<Reference, Attribute> {

    @Override
    public Attribute convert(Reference source, Type<? extends Attribute> destinationType, MappingContext mappingContext) {
        return new Attribute(
                "valueId",
                null,
                null,
                ReferenceConverterUtil.convert(source),
                new RefSemantic(AASNamespace.Property_ValueId.getRefSemantic()),
                null
        );
    }
}
