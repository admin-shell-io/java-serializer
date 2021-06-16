package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.KeyType;

public interface KeyMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public KeyType getIdType();

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public KeyElements getType();

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public String getValue();
}
