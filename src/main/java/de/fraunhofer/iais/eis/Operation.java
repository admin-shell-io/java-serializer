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
* "Operation"
* "An operation is a submodel element with input and output variables."@en
* "Constraint AASd-060: The semanticId of a Operation submodel element shall only reference a ConceptDescription with the category FUNCTION."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultOperation.class)
})
public interface Operation extends SubmodelElement {

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
	* "Input parameter of the operation."@en
	* @return Returns the List of OperationVariables for the property inputVariables.
	* More information under https://admin-shell.io/aas/3/0/RC01/Operation/inputVariable
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Operation/inputVariable")
	public List<OperationVariable> getInputVariables();

	/**
	* "Parameter that is input and output of the operation."@en
	* @return Returns the List of OperationVariables for the property inoutputVariables.
	* More information under https://admin-shell.io/aas/3/0/RC01/Operation/inoutputVariable
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Operation/inoutputVariable")
	public List<OperationVariable> getInoutputVariables();

	/**
	* "Output parameter of the operation."@en
	* @return Returns the List of OperationVariables for the property outputVariables.
	* More information under https://admin-shell.io/aas/3/0/RC01/Operation/outputVariable
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Operation/outputVariable")
	public List<OperationVariable> getOutputVariables();

}
