package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.model.Referable;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class ReferableMapper extends CustomMapper<Referable, InternalElement> {

    @Override
    public void mapAtoB(Referable referable, InternalElement internalElement, MappingContext context) {

    }
}
