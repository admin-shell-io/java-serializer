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
* "Referable"
* "An element that is referable by its idShort. This id is not globally unique. This id is unique within the name space of the element."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultReferable.class),
	@KnownSubtypes.Type(value = AccessPermissionRule.class),
	@KnownSubtypes.Type(value = Identifiable.class),
	@KnownSubtypes.Type(value = View.class),
	@KnownSubtypes.Type(value = SubmodelElement.class)
})
public interface Referable {

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
	* "The category is a value that gives further meta information w.r.t. to the class of the element. It affects the expected existence of attributes and the applicability of constraints."@en
	* @return Returns the List of Strings for the property referableCategories.
	* More information under https://admin-shell.io/aas/3/0/RC01/Referable/referableCategory
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Referable/referableCategory")
	public List<String> getReferableCategories();

	/**
	* "Description or comments on the element. The description can be provided in several languages."@en
	* @return Returns the List of TypedLiterals for the property descriptions.
	* More information under https://admin-shell.io/aas/3/0/RC01/Referable/description
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Referable/description")
	public List<TypedLiteral> getDescriptions();

	/**
	* "Display name. Can be provided in several languages."@en
	* @return Returns the TypedLiteral for the property displayName.
	* More information under https://admin-shell.io/aas/3/0/RC01/Referable/displayName
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Referable/displayName")
	public TypedLiteral getDisplayName();

	/**
	* "Identifying string of the element within its name space."@en
	* "Constraint AASd-002: idShort shall only feature letters, digits, underscore (\'_\'); starting with a small letter. I.e. [a-z][a-zA-Z0-9_]+."@en
	* "Constraint AASd-003: idShort shall be matched case-insensitive."@en
	* "Constraint AASd-022: idShort of non-identifiable referables shall be unqiue in its namespace."@en
	* "Note: In case the element is a property and the property has a semantic definition (HasSemantics) the idShort is typically identical to the short name in English. "@en
	* "Note: In case of an identifiable element idShort is optional but recommended to be defined. It can be used for unique reference in its name space and thus allows better usability and a more performant implementation. In this case it is similar to the \'BrowserPath\' in OPC UA."@en
	* @return Returns the String for the property idShort.
	* More information under https://admin-shell.io/aas/3/0/RC01/Referable/idShort
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Referable/idShort")
	public String getIdShort();

}
