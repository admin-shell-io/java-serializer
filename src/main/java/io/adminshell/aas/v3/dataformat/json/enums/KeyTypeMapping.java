package io.adminshell.aas.v3.dataformat.json.enums;

import java.util.Map;

import io.adminshell.aas.v3.model.KeyType;

public class KeyTypeMapping implements CustomEnumNaming<KeyType> {

	@Override
	public Map<String, KeyType> getMapping() {
		return Map.of("Custom", KeyType.CUSTOM, "FragmentId", KeyType.FRAGMENT_ID, "IdShort", KeyType.IDSHORT, "IRDI",
				KeyType.IRDI, "IRI", KeyType.IRI);
	}

}
