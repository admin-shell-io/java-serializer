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
* "Has Kind"
* "An element with a kind is an element that can either represent a type or an instance. Default for an element is that it is representing an instance."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultHasKind.class),
	@KnownSubtypes.Type(value = Submodel.class),
	@KnownSubtypes.Type(value = SubmodelElement.class)
})
public interface HasKind {

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
	* "ModelingKind of the element: either type or instance."@en
	* @return Returns the ModelingKind for the property kind.
	* More information under https://admin-shell.io/aas/3/0/RC01/HasKind/kind
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/HasKind/kind")
	public ModelingKind getKind();

}
