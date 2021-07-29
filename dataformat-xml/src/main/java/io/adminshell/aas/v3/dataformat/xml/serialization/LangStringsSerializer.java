package io.adminshell.aas.v3.dataformat.xml.serialization;

import io.adminshell.aas.v3.model.LangString;

public class LangStringsSerializer extends NoEntryWrapperListSerializer<LangString> {
    public LangStringsSerializer() {
        super();
        this.setObjectForMinCardinality(new LangString());
    }
}