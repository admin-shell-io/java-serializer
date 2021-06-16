package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.adminshell.aas.v3.model.Key;
import java.util.List;

public interface ReferenceMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public List<Key> getKeys();
}
