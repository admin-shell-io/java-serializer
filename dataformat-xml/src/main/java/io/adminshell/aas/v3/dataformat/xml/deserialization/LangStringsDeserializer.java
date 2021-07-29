package io.adminshell.aas.v3.dataformat.xml.deserialization;

import io.adminshell.aas.v3.model.LangString;

public class LangStringsDeserializer extends NoEntryWrapperListDeserializer<LangString> {
    public LangStringsDeserializer() {
        super("langString", new LangStringNodeDeserializer());
    }
}
