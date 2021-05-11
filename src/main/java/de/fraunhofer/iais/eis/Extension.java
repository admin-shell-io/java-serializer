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
* "Extensions"
* "Single extension of an element."@en
* "Constraint AASd-077: The name of an extension within HasExtensions needs to be unique."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultExtension.class)
})
public interface Extension extends HasSemantics {

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
	* "An extension of the element."@en
	* @return Returns the String for the property name.
	* More information under https://admin-shell.io/aas/3/0/RC01/Extension/name
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Extension/name")
	public String getName();

	/**
	* "Type of the value of the extension."@en
	* @return Returns the String for the property valueType.
	* More information under https://admin-shell.io/aas/3/0/RC01/Extension/valueType
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Extension/valueType")
	public String getValueType();

	/**
	* "Value of the extension."@en
	* @return Returns the String for the property value.
	* More information under https://admin-shell.io/aas/3/0/RC01/Extension/value
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Extension/value")
	public String getValue();

	/**
	* "Reference to an element the extension refers to."@en
	* @return Returns the Reference for the property refersTo.
	* More information under https://admin-shell.io/aas/3/0/RC01/Extension/refersTo
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Extension/refersTo")
	public Reference getRefersTo();

}
