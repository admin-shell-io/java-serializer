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
import io.adminshell.aas.v3.model.impl.DefaultKey;
import io.adminshell.aas.v3.model.impl.DefaultReference;

/**
 * This class is used to manage supported data specification templates. Each
 * template is identified through a reference and provides a corresponding Java
 * class.
 */
public class DataSpecificationManager {

    public static final String PROP_DATA_SPECIFICATION = "dataSpecification";
    public static final String PROP_DATA_SPECIFICATION_CONTENT = "dataSpecificationContent";

    private static final Map<Reference, Class<? extends DataSpecificationContent>> KNOWN_IMPLEMENTATIONS = Map.of(
            createGlobalIri("http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0"),
            DataSpecificationIEC61360.class);

    /**
     * Allows to register an additional data specification template based on any
     * kind of Reference.
     *
     * @param reference The reference used to identify the data specification
     * template
     * @param implementation The Java class implementing that template
     */
    public static void register(Reference reference, Class<? extends DataSpecificationContent> implementation) {
        KNOWN_IMPLEMENTATIONS.put(reference, implementation);
    }

    /**
     * Allows to register an additional data specification template based on a
     * global IRI.
     *
     * @param iri The IRI of the global reference used to identify the data
     * specification template
     * @param implementation The Java class implementing that template
     */
    public static void registerGlobalIri(String iri, Class<? extends DataSpecificationContent> implementation) {
        KNOWN_IMPLEMENTATIONS.put(createGlobalIri(iri), implementation);
    }

    private static Reference createGlobalIri(String iri) {
        return new DefaultReference.Builder().keys(Arrays.asList(
                new DefaultKey.Builder().idType(KeyType.IRI).type(KeyElements.GLOBAL_REFERENCE).value(iri).build()))
                .build();
    }

    /**
     * Returns a Reference describing the data specification template
     * implemented by the given class. If the class is unknown, an
     * IllegalArgumentException is thrown
     *
     * @param implementation type of the implementation class
     * @return a reference describing the data specification template
     * implemented by the given class
     * @throws IllegalArgumentException when implementation class is not known
     */
    public static Reference getReference(Class<? extends DataSpecificationContent> implementation) {
        Reference result = getReferenceSafe(implementation);
        if (result == null) {
            throw new IllegalArgumentException(
                    String.format("unknown data specification implementation '%s'", implementation.getName()));
        }
        return result;
    }

    /**
     * Returns a Reference describing the data specification template
     * implemented by the given class. If the class is unknown, null is
     * returned.
     *
     * @param implementation type of the implementation class
     * @return a reference describing the data specification template
     * implemented by the given class, or null if the implementation class is
     * unknown
     */
    public static Reference getReferenceSafe(Class<? extends DataSpecificationContent> implementation) {
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

    /**
     * Returns the Java class implementing the data specification template
     * identified by the given reference. If the reference is unkown
     *
     * @param reference A reference used to identify the used data specification
     * template.
     * @return Java class implementing the given data specification template
     * @throws IllegalArgumentException if there is no known implementation for
     * the given reference identifier
     */
    public static Class<? extends DataSpecificationContent> getImplementation(Reference reference) {
        if (KNOWN_IMPLEMENTATIONS.containsKey(reference)) {
            return KNOWN_IMPLEMENTATIONS.get(reference);
        }
        throw new IllegalArgumentException(String.format("unknown data specification reference '%s'", reference));
    }
}
