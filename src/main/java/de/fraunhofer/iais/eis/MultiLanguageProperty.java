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
* "Multi Language Property"
* "A property is a data element that has a multi language value."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultMultiLanguageProperty.class)
})
public interface MultiLanguageProperty extends DataElement {

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
	* "The value of the property instance."@en
	* "Constraint AASd-052b: If the semanticId of a MultiLanguageProperty references a ConceptDescription then the ConceptDescription/category shall be one of  following values: PROPERTY."@en
	* "Constraint AASd-012: If both, the MultiLanguageProperty/value and the MultiLanguageProperty/valueId are present then for each string in a specific language the meaning must be the same as specified in MultiLanguageProperty/valueId."@en
	* "Constraint AASd-067: If the semanticId of a MultiLanguageProperty references a ConceptDescription then DataSpecificationIEC61360/dataType shall be STRING_TRANSLATABLE."@en
	* @return Returns the List of TypedLiterals for the property values.
	* More information under https://admin-shell.io/aas/3/0/RC01/MultiLanguageProperty/value
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/MultiLanguageProperty/value")
	public List<TypedLiteral> getValues();

	/**
	* "Reference to the global unqiue id of a coded value."@en
	* @return Returns the Reference for the property valueId.
	* More information under https://admin-shell.io/aas/3/0/RC01/MultiLanguageProperty/valueId
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/MultiLanguageProperty/valueId")
	public Reference getValueId();

}
