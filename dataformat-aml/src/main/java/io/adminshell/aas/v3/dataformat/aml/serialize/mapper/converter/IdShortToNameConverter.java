package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.converter;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

import java.util.UUID;

public class IdShortToNameConverter extends CustomConverter<String, String> {

    @Override
    public String convert(String source, Type<? extends String> destinationType, MappingContext mappingContext) {
        if (source == null) {
            return UUID.randomUUID().toString();
        }

        return source;
    }
}
