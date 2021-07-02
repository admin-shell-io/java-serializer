package de.fraunhofer.iais.eis.ids.jsonld.preprocessing;

import java.io.IOException;

/**
 * Interface for JSON-LD preprocessors which should transform JSON-LD inputs
 * before they are deserialized by Jackson.
 *
 * Implementations used at the same time must not interfere with each other.
 */
public interface JsonPreprocessor {

    /**
     * preprocessing method
     * @param input of the transformation, the original JSON-LD
     * @return the transformation´s result
     */
    public String preprocess(String input) throws IOException;

    /**
     * specify wheter the transformation's result should be validated to
     * be parsable by RDF4j
     * @param validate enable/disable switch
     */
    public void enableRDFValidation(boolean validate);
}
