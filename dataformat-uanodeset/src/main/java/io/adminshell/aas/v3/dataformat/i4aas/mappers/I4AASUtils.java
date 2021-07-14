package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.UUID;

public class I4AASUtils {

	public static String generateRandomNamespace() {
		return "http://example.org/i4aasNs/" + UUID.randomUUID() +"/";
	}
}
