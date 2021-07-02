package de.fraunhofer.iais.eis.ids.jsonld.preprocessing;


import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFLanguages;


import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * basic implementation of {@code JsonPreprocessor} that encapsulates validation.
 * By default, validation is disabled for performance reasons (@context has to be downloaded each time).
 */
public abstract class BasePreprocessor implements JsonPreprocessor {

    private boolean validate = false;


    @Override
    public final String preprocess(String input) throws IOException {
        String result = preprocess_impl(input);
        if(validate) {
            Model m = ModelFactory.createDefaultModel();
            RDFDataMgr.read(m, new ByteArrayInputStream(result.getBytes()), RDFLanguages.JSONLD);
        }
        return result;
    }

    abstract String preprocess_impl(String input) throws IOException;

    @Override
    public void enableRDFValidation(boolean validate) {
        this.validate = validate;
    }
}
