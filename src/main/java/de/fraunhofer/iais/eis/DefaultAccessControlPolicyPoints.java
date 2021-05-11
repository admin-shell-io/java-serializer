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
* "Access ControlPolicy Points"
* "Container for access control policy points."@en 
*/

public class DefaultAccessControlPolicyPoints implements AccessControlPolicyPoints {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Access ControlPolicy Points", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("Container for access control policy points.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "has policy administration point"
	* "The access control administration policy point of the AAS."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyAdministrationPoint")
	protected PolicyAdministrationPoint policyAdministrationPoint;


	/**
	* "has policy decision point"
	* "The access control policy decision point of the AAS."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyDecisionPoint")
	protected PolicyDecisionPoint policyDecisionPoint;


	/**
	* "has policy enforcement point"
	* "The access control policy enforcement point of the AAS."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyEnforcementPoint")
	protected PolicyEnforcementPoints policyEnforcementPoint;


	/**
	* "has policy information points"
	* "The access control policy information points of the AAS."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyInformationPoints")
	protected PolicyInformationPoints policyInformationPoints;


	// no manual construction
	protected DefaultAccessControlPolicyPoints() {
		id = VocabUtil.getInstance().createRandomUrl("accessControlPolicyPoints");
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
		return Objects.hash(new Object[]{this.policyAdministrationPoint,
			this.policyDecisionPoint,
			this.policyEnforcementPoint,
			this.policyInformationPoints});
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
			DefaultAccessControlPolicyPoints other = (DefaultAccessControlPolicyPoints) obj;
			return Objects.equals(this.policyAdministrationPoint, other.policyAdministrationPoint) &&
				Objects.equals(this.policyDecisionPoint, other.policyDecisionPoint) &&
				Objects.equals(this.policyEnforcementPoint, other.policyEnforcementPoint) &&
				Objects.equals(this.policyInformationPoints, other.policyInformationPoints);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyAdministrationPoint")
	final public PolicyAdministrationPoint getPolicyAdministrationPoint() {
		return policyAdministrationPoint;
	}
	
	final public void setPolicyAdministrationPoint (PolicyAdministrationPoint policyAdministrationPoint) {
		this.policyAdministrationPoint = policyAdministrationPoint;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyDecisionPoint")
	final public PolicyDecisionPoint getPolicyDecisionPoint() {
		return policyDecisionPoint;
	}
	
	final public void setPolicyDecisionPoint (PolicyDecisionPoint policyDecisionPoint) {
		this.policyDecisionPoint = policyDecisionPoint;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyEnforcementPoint")
	final public PolicyEnforcementPoints getPolicyEnforcementPoint() {
		return policyEnforcementPoint;
	}
	
	final public void setPolicyEnforcementPoint (PolicyEnforcementPoints policyEnforcementPoint) {
		this.policyEnforcementPoint = policyEnforcementPoint;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyInformationPoints")
	final public PolicyInformationPoints getPolicyInformationPoints() {
		return policyInformationPoints;
	}
	
	final public void setPolicyInformationPoints (PolicyInformationPoints policyInformationPoints) {
		this.policyInformationPoints = policyInformationPoints;
	}
}
