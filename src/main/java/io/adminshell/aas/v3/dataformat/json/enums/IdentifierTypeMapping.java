package io.adminshell.aas.v3.dataformat.json.enums;

import io.adminshell.aas.v3.dataformat.json.enums.CustomEnumNaming;
import de.fraunhofer.iais.eis.IdentifierType;
import java.util.Map;

public class IdentifierTypeMapping implements CustomEnumNaming<IdentifierType> {

    @Override
    public Map<String, IdentifierType> getMapping() {
        return Map.of(
                "Custom", IdentifierType.CUSTOM,
                "IRDI", IdentifierType.IRDI,
                "IRI", IdentifierType.IRI);
    }

}
