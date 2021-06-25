package io.adminshell.aas.v3.dataformat.json.mixins;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.adminshell.aas.v3.model.ValueReferencePair;
import java.util.List;

public interface ValueListMixin {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public List<ValueReferencePair> getValueReferencePairTypes();
}
