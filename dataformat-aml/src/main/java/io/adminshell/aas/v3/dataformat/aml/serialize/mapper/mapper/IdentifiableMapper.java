package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.model.Identifiable;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class IdentifiableMapper extends CustomMapper<Identifiable, InternalElement> {

    @Override
    public void mapAtoB(Identifiable identifiable, InternalElement internalElement, MappingContext context) {

    }
}
