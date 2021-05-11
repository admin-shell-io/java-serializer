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
* "Range"
* "An element that is referable by its idShort. This id is not globally unique. This id is unique within the name space of the element."@en
* "Constraint AASd-053: The semanticId of a Range submodel element shall only reference a ConceptDescription with the category PROPERTY."@en
* "Constraint AASd-068: If the semanticId of a  Range references a ConceptDescription then DataSpecificationIEC61360/dataType shall be a numerical one, i.e. REAL_* or RATIONAL_*."@en
* "Constraint AASd-069: If the semanticId of a  Range references a ConceptDescription then DataSpecificationIEC61360/levelType shall be identical to the set {Min,Max}."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultRange.class)
})
public interface Range extends DataElement {

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
	* "Data type of the min and max."@en
	* @return Returns the String for the property valueType.
	* More information under https://admin-shell.io/aas/3/0/RC01/Range/valueType
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Range/valueType")
	public String getValueType();

	/**
	* "The maximum value of the range."@en
	* @return Returns the TypedLiteral for the property max.
	* More information under https://admin-shell.io/aas/3/0/RC01/Range/max
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Range/max")
	public TypedLiteral getMax();

	/**
	* "The minimum value of the range."@en
	* @return Returns the TypedLiteral for the property min.
	* More information under https://admin-shell.io/aas/3/0/RC01/Range/min
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Range/min")
	public TypedLiteral getMin();

}
