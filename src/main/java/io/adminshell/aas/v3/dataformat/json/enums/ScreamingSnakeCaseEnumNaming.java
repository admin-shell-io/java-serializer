package io.adminshell.aas.v3.dataformat.json.enums;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScreamingSnakeCaseEnumNaming<T extends Enum> implements CustomEnumNaming<T> {

    private final Map<String, T> mapping;

    public ScreamingSnakeCaseEnumNaming(Class<T> clazz) {
        mapping = Stream.of(clazz.getEnumConstants())
                .collect(Collectors.toMap(x -> x.name().toUpperCase(), x -> x));
    }

    @Override
    public Map<String, T> getMapping() {
        return mapping;
    }

}
