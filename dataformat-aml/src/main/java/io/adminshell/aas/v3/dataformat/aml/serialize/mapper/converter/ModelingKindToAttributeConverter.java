package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AASNamespace;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import io.adminshell.aas.v3.model.*;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class ModelingKindToAttributeConverter extends CustomConverter<ModelingKind, Attribute> {

    @Override
    public Attribute convert(ModelingKind source, Type<? extends Attribute> destinationType, MappingContext mappingContext) {
        return new Attribute(
                "kind",
                null,
                null,
                source.toString(),
                new RefSemantic(AASNamespace.HasKind_Kind.getRefSemantic()),
                null);
    }
}
