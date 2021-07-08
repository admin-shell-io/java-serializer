package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter;

import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class AllowDuplicatesToAttributeConverter extends CustomConverter<Boolean, Attribute> {

    @Override
    public Attribute convert(Boolean source, Type<? extends Attribute> destinationType, MappingContext mappingContext) {
        return new Attribute(
                "allowDuplicates",
                "xs:boolean",
                null,
                source.toString(),
                new RefSemantic("AAS:SubmodelElementCollection/allowDuplicates"),
                null
        );
    }
}
