package io.adminshell.aas.v3.dataformat.xml.enums;

import java.util.Map;

public interface CustomEnumNaming<T extends Enum> {

    public Map<String, T> getMapping();
}
