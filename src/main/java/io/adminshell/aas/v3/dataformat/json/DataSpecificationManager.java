package io.adminshell.aas.v3.dataformat.json;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import io.adminshell.aas.v3.model.DataSpecificationContent;
import io.adminshell.aas.v3.model.DataSpecificationIEC61360;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.KeyType;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.impl.builder.DefaultKeyBuilder;
import io.adminshell.aas.v3.model.impl.builder.DefaultReferenceBuilder;

public class DataSpecificationManager {

	public static final String PROP_DATA_SPECIFICATION = "dataSpecification";
	public static final String PROP_DATA_SPECIFICATION_CONTENT = "dataSpecificationContent";

	private static final Map<Reference, Class<? extends DataSpecificationContent>> KNOWN_IMPLEMENTATIONS = Map.of(
			createGlobalIri("http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0"),
			DataSpecificationIEC61360.class);

	public static void register(Reference reference, Class<? extends DataSpecificationContent> implementation) {
		KNOWN_IMPLEMENTATIONS.put(reference, implementation);
	}

	public static void registerGlobalIri(String iri, Class<? extends DataSpecificationContent> implementation) {
		KNOWN_IMPLEMENTATIONS.put(createGlobalIri(iri), implementation);
	}

	private static Reference createGlobalIri(String iri) {
		return new DefaultReferenceBuilder().keys(Arrays.asList(
				new DefaultKeyBuilder().idType(KeyType.IRI).type(KeyElements.GLOBAL_REFERENCE).value(iri).build()))
				.build();
	}

	public static Reference getReference(Class<? extends DataSpecificationContent> implementation) {
		Reference result = getReferenceSafe(implementation);
		if (result == null) {
			throw new IllegalArgumentException(
					String.format("unknown data specification implementation '%s'", implementation.getName()));
		}
		return result;
	}

	private static Reference getReferenceSafe(Class<? extends DataSpecificationContent> implementation) {
		Optional<Reference> exactMatch = KNOWN_IMPLEMENTATIONS.entrySet().stream()
				.filter(x -> Objects.equals(x.getValue(), implementation)).map(x -> x.getKey()).findFirst();
		if (exactMatch.isPresent()) {
			return exactMatch.get();
		}
		Optional<Reference> inheritanceMatch = KNOWN_IMPLEMENTATIONS.entrySet().stream()
				.filter(x -> x.getValue().isAssignableFrom(implementation)).map(x -> x.getKey()).findFirst();
		if (inheritanceMatch.isPresent()) {
			return inheritanceMatch.get();
		}
		return null;
	}

	public static Class<? extends DataSpecificationContent> getImplementation(Reference reference) {
		if (KNOWN_IMPLEMENTATIONS.containsKey(reference)) {
			return KNOWN_IMPLEMENTATIONS.get(reference);
		}
		throw new IllegalArgumentException(String.format("unknown data specification reference '%s'", reference));
	}
}
