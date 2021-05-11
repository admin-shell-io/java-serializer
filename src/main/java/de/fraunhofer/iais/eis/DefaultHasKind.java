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

public class DefaultHasKind implements HasKind {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Has Kind", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("An element with a kind is an element that can either represent a type or an instance. Default for an element is that it is representing an instance.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "has kind"
	* "ModelingKind of the element: either type or instance."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/HasKind/kind")
	protected ModelingKind kind;


	// no manual construction
	protected DefaultHasKind() {
		id = VocabUtil.getInstance().createRandomUrl("hasKind");
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
		return Objects.hash(new Object[]{this.kind});
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
			DefaultHasKind other = (DefaultHasKind) obj;
			return Objects.equals(this.kind, other.kind);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/HasKind/kind")
	final public ModelingKind getKind() {
		return kind;
	}
	
	final public void setKind (ModelingKind kind) {
		this.kind = kind;
	}
}
