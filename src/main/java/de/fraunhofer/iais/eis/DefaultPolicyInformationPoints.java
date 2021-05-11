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
* "Policy Information Points"
* "Defines the security policy information points (PIP). Serves as the retrieval source of attributes, or the data required for policy evaluation to provide the information needed by the policy decision point to make the decisions."@en 
*/

public class DefaultPolicyInformationPoints implements PolicyInformationPoints {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Policy Information Points", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("Defines the security policy information points (PIP). Serves as the retrieval source of attributes, or the data required for policy evaluation to provide the information needed by the policy decision point to make the decisions.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "has external information point"
	* "If externalInformationPoints True then at least one Endpoint to external available information needs to be configured for the AAS."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/PolicyInformationPoints/externalInformationPoints")
	protected boolean externalInformationPoints;


	/**
	* "has internal information point"
	* "References to submodels defining information used by security access permission rules."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/PolicyInformationPoints/internalInformationPoint")
	protected List<Submodel> internalInformationPoints;


	// no manual construction
	protected DefaultPolicyInformationPoints() {
		id = VocabUtil.getInstance().createRandomUrl("policyInformationPoints");
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
		return Objects.hash(new Object[]{this.externalInformationPoints,
			this.internalInformationPoints});
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
			DefaultPolicyInformationPoints other = (DefaultPolicyInformationPoints) obj;
			return Objects.equals(this.externalInformationPoints, other.externalInformationPoints) &&
				Objects.equals(this.internalInformationPoints, other.internalInformationPoints);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/PolicyInformationPoints/externalInformationPoints")
	final public boolean getExternalInformationPoints() {
		return externalInformationPoints;
	}
	
	final public void setExternalInformationPoints (boolean externalInformationPoints) {
		this.externalInformationPoints = externalInformationPoints;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/PolicyInformationPoints/internalInformationPoint")
	final public List<Submodel> getInternalInformationPoints() {
		return internalInformationPoints;
	}
	
	final public void setInternalInformationPoints (List<Submodel> internalInformationPoints) {
		this.internalInformationPoints = internalInformationPoints;
	}
}
