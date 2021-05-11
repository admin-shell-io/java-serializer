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
* "Property"
* "A property is a data element that has a single value."@en
* "Constraint AASd-052a: If the semanticId of a Property references a ConceptDescription then the ConceptDescription/category shall be one of  following values: VALUE, PROPERTY."@en
* "Constraint AASd-065: If the semanticId of a Property or MultiLanguageProperty references a ConceptDescription with the  category VALUE then the value of the property is identical to  DataSpecificationIEC61360/value and the valueId of the property is identical to DataSpecificationIEC61360/valueId."@en
* "Constraint AASd-066: If the semanticId of a Property or MultiLanguageProperty references a ConceptDescription with the  category PROPERTY and DataSpecificationIEC61360/valueList is defined the value and valueId of the property is identical to one of the value reference pair types references in the value list, i.e. ValueReferencePairType/value or ValueReferencePairType/valueId, resp."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultProperty.class)
})
public interface Property extends DataElement {

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
	* "Data type pf the value."@en
	* @return Returns the String for the property valueType.
	* More information under https://admin-shell.io/aas/3/0/RC01/Property/valueType
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Property/valueType")
	public String getValueType();

	/**
	* "The value of the property instance."@en
	* @return Returns the TypedLiteral for the property value.
	* More information under https://admin-shell.io/aas/3/0/RC01/Property/value
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Property/value")
	public TypedLiteral getValue();

	/**
	* "Reference to the global unique id of a coded value."@en
	* "Constraint AASd-007: if both, the value and the valueId are present then the value needs to be identical to the value of the referenced coded value in valueId."@en
	* @return Returns the Reference for the property valueId.
	* More information under https://admin-shell.io/aas/3/0/RC01/Property/valueId
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Property/valueId")
	public Reference getValueId();

}
