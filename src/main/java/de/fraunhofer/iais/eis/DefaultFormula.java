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
* "Formula" 
*/

public class DefaultFormula implements Formula {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Formula", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Collections.emptyList();

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "depends on"
	* "A formula may depend on referable or even external global elements - assumed that can be referenced and their value may be evaluated - that are used in the logical expression."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Formula/dependsOn")
	protected List<Reference> dependsOns;


	// no manual construction
	protected DefaultFormula() {
		id = VocabUtil.getInstance().createRandomUrl("formula");
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
		return Objects.hash(new Object[]{this.dependsOns});
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
			DefaultFormula other = (DefaultFormula) obj;
			return Objects.equals(this.dependsOns, other.dependsOns);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/Formula/dependsOn")
	final public List<Reference> getDependsOns() {
		return dependsOns;
	}
	
	final public void setDependsOns (List<Reference> dependsOns) {
		this.dependsOns = dependsOns;
	}

}
