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
* "Operation Variable"
* "An operation variable is a submodel element that is used as input or output variable of an operation."@en 
*/

public class DefaultOperationVariable implements OperationVariable {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Operation Variable", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("An operation variable is a submodel element that is used as input or output variable of an operation.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "value"
	* "Describes the needed argument for an operation via a submodel element of kind=Template."@en
	* "The submodel element value of an operation variable shall be of kind=Template."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/OperationVariable/value")
	protected SubmodelElement value;


	// no manual construction
	protected DefaultOperationVariable() {
		id = VocabUtil.getInstance().createRandomUrl("operationVariable");
	}

	final public URI getId() {
		return id;
	}

	public List<TypedLiteral> getLabels() {
		return this.labels;
	}

	public List<TypedLiteral> getComments() {
		return this.comments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(new Object[]{this.value});
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (this.getClass() != obj.getClass()) {
			return false;
		} else {
			DefaultOperationVariable other = (DefaultOperationVariable) obj;
			return Objects.equals(this.value, other.value);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/OperationVariable/value")
	final public SubmodelElement getValue() {
		return value;
	}
	
	final public void setValue (SubmodelElement value) {
		this.value = value;
	}
}
