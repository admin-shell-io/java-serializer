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
* "identifier key value pair"
* "An IdentifierKeyValuePair describes a generic identifier as key-value pair."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultIdentifierKeyValuePair.class)
})
public interface IdentifierKeyValuePair extends HasSemantics {

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
	* "Key of the identifier."@en
	* @return Returns the String for the property key.
	* More information under https://admin-shell.io/aas/3/0/RC01/IdentifierKeyValuePair/key
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/IdentifierKeyValuePair/key")
	public String getKey();

	/**
	* "The value of the identifier with the corresponding key."@en
	* @return Returns the String for the property value.
	* More information under https://admin-shell.io/aas/3/0/RC01/IdentifierKeyValuePair/value
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/IdentifierKeyValuePair/value")
	public String getValue();

	/**
	* "The (external) subject the key belongs to or has meaning to."@en
	* @return Returns the Reference for the property externalSubjectId.
	* More information under https://admin-shell.io/aas/3/0/RC01/IdentifierKeyValuePair/externalSubjectId
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/IdentifierKeyValuePair/externalSubjectId")
	public Reference getExternalSubjectId();

}
