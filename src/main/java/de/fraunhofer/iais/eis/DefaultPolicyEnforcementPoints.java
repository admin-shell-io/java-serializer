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
* "Policy Enforcement Point"
* "Defines the security policy enforcement points (PEP)."@en 
*/

public class DefaultPolicyEnforcementPoints implements PolicyEnforcementPoints {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Policy Enforcement Point", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("Defines the security policy enforcement points (PEP).", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "is external policy enforcement point defined"
	* "If externalPolicyEnforcementPoint True then an Endpoint to external available enforcement point taking needs to be configured for the AAS."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/PolicyEnforcementPoints/externalPolicyEnforcementPoint")
	protected boolean externalPolicyEnforcementPoint;


	// no manual construction
	protected DefaultPolicyEnforcementPoints() {
		id = VocabUtil.getInstance().createRandomUrl("policyEnforcementPoints");
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
		return Objects.hash(new Object[]{this.externalPolicyEnforcementPoint});
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
			DefaultPolicyEnforcementPoints other = (DefaultPolicyEnforcementPoints) obj;
			return Objects.equals(this.externalPolicyEnforcementPoint, other.externalPolicyEnforcementPoint);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/PolicyEnforcementPoints/externalPolicyEnforcementPoint")
	final public boolean getExternalPolicyEnforcementPoint() {
		return externalPolicyEnforcementPoint;
	}
	
	final public void setExternalPolicyEnforcementPoint (boolean externalPolicyEnforcementPoint) {
		this.externalPolicyEnforcementPoint = externalPolicyEnforcementPoint;
	}
}
