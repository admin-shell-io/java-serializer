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
* "Qualifiable"
* "Additional qualification of a qualifiable element."@en
* "Constraint AASd-021: Every qualifiable can only have one qualifier with the same Qualifier/type."@en 
*/

public class DefaultQualifiable implements Qualifiable {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Qualifiable", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("Additional qualification of a qualifiable element.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "has qualifier"
	* "Additional qualification of a qualifiable element."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Qualifiable/qualifier")
	protected List<Constraint> qualifiers;


	// no manual construction
	protected DefaultQualifiable() {
		id = VocabUtil.getInstance().createRandomUrl("qualifiable");
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
		return Objects.hash(new Object[]{this.qualifiers});
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
			DefaultQualifiable other = (DefaultQualifiable) obj;
			return Objects.equals(this.qualifiers, other.qualifiers);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/Qualifiable/qualifier")
	final public List<Constraint> getQualifiers() {
		return qualifiers;
	}
	
	final public void setQualifiers (List<Constraint> qualifiers) {
		this.qualifiers = qualifiers;
	}
}
