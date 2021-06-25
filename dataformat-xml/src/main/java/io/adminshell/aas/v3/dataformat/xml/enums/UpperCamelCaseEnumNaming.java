package io.adminshell.aas.v3.dataformat.xml.enums;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UpperCamelCaseEnumNaming<T extends Enum> implements CustomEnumNaming<T> {

    private final Map<String, T> mapping;

    public UpperCamelCaseEnumNaming(Class<T> clazz) {
        PropertyNamingStrategy.PropertyNamingStrategyBase namingStrategy = new UpperSnakeCaseToUpperCamelCaseStrategy();
        mapping = Stream.of(clazz.getEnumConstants())
                .collect(Collectors.toMap(x -> namingStrategy.translate(x.name()), x -> x));
    }

    @Override
    public Map<String, T> getMapping() {
        return mapping;
    }

}
