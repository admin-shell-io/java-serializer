package de.fraunhofer.iais.eis;

import de.fraunhofer.iais.eis.util.*;
import de.fraunhofer.iais.eis.*;

import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.String;
import java.math.BigInteger;
import java.net.URL;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* "Blob Data Element"
* "A BLOB is a data element that represents a file that is contained with its source code in the value attribute."@en
* "Constraint AASd-057: The semanticId of a File or Blob submodel element shall only reference a ConceptDescription with the category DOCUMENT."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultBlob.class)
})
public interface Blob extends DataElement {

	// standard methods

	/**
	* This function retrieves the ID of the current object (can be set via the constructor of the builder class)
	* @return ID of current object as URI
	*/
	public URI getId();

	/**
	* This function retrieves a human readable labels about the current class, as defined in the ontology.
	* This label could, for example, be used as a field heading in a user interface
	* @return Human readable labels
	*/
	public List<TypedLiteral> getLabels();

	/**
	* This function retrieves a human readable explanatory comments about the current class, as defined in the ontology.
	* This comment could, for example, be used as a tooltip in a user interface
	* @return Human readable explanatory comments
	*/
	public List<TypedLiteral> getComments();

	// accessor methods as derived from the Asset Administration Shell ontology


	/**
	* "Mime type of the content of the BLOB. The mime type states which file extension the file has. Valid values are e.g. \'application/json\', \'application/xls\', \'image/jpg\' The allowed values are defined as in RFC2046."@en
	* @return Returns the String for the property mimeType.
	* More information under https://admin-shell.io/aas/3/0/RC01/Blob/mimeType
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Blob/mimeType")
	public String getMimeType();

	/**
	* "The value of the BLOB instance of a blob data element."@en
	* "In contrast to the file property the file content is stored directly as value in the Blob data element."@en
	* @return Returns the byte[] for the property value.
	* More information under https://admin-shell.io/aas/3/0/RC01/Blob/value
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Blob/value")
	public byte[] getValue();

}
