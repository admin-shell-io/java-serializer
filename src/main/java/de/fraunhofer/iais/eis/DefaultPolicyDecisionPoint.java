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
* "Policy Decision Point"
* "Defines a security policy decision point (PDP). "@en 
*/

public class DefaultPolicyDecisionPoint implements PolicyDecisionPoint {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Policy Decision Point", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("Defines a security policy decision point (PDP). ", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "is external policy decision point defined"
	* "If externalPolicyDecisionPoints True then Endpoints to external available decision points taking into consideration for access control for the AAS need to be configured."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/PolicyDecisionPoint/externalPolicyDecisionPoints")
	protected boolean externalPolicyDecisionPoints;


	// no manual construction
	protected DefaultPolicyDecisionPoint() {
		id = VocabUtil.getInstance().createRandomUrl("policyDecisionPoint");
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
		return Objects.hash(new Object[]{this.externalPolicyDecisionPoints});
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
			DefaultPolicyDecisionPoint other = (DefaultPolicyDecisionPoint) obj;
			return Objects.equals(this.externalPolicyDecisionPoints, other.externalPolicyDecisionPoints);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/PolicyDecisionPoint/externalPolicyDecisionPoints")
	final public boolean getExternalPolicyDecisionPoints() {
		return externalPolicyDecisionPoints;
	}
	
	final public void setExternalPolicyDecisionPoints (boolean externalPolicyDecisionPoints) {
		this.externalPolicyDecisionPoints = externalPolicyDecisionPoints;
	}
}
