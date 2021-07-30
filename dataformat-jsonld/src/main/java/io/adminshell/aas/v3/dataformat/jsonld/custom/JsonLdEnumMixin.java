package io.adminshell.aas.v3.dataformat.jsonld.custom;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.adminshell.aas.v3.dataformat.jsonld.JsonLdEnumSerializer;

@JsonSerialize(using = JsonLdEnumSerializer.class)
public class JsonLdEnumMixin {
}
