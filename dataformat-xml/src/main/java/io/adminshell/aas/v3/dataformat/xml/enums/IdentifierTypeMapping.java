package io.adminshell.aas.v3.dataformat.xml.enums;

import java.util.Map;

import io.adminshell.aas.v3.model.IdentifierType;

public class IdentifierTypeMapping implements CustomEnumNaming<IdentifierType> {

	@Override
	public Map<String, IdentifierType> getMapping() {
		return Map.of("Custom", IdentifierType.CUSTOM, "IRDI", IdentifierType.IRDI, "IRI", IdentifierType.IRI);
	}

}
