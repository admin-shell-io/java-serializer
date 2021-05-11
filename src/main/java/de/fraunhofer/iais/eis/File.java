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
* "File Submodel Element"
* "A File is a data element that represents a file via its path description."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultFile.class)
})
public interface File extends SubmodelElement {

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
	* "Mime type of the content of the File."@en
	* @return Returns the String for the property mimeType.
	* More information under https://admin-shell.io/aas/3/0/RC01/File/mimeType
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/File/mimeType")
	public String getMimeType();

	/**
	* "Path and name of the referenced file (with file extension). The path can be absolute or relative."@en
	* @return Returns the String for the property value.
	* More information under https://admin-shell.io/aas/3/0/RC01/File/value
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/File/value")
	public String getValue();

}
