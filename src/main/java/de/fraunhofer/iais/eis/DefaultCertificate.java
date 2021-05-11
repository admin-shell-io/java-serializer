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
* "Certificate"
* "A technical certificate proofing the identity through cryptographic measures."@en 
*/

public class DefaultCertificate implements Certificate {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Certificate", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("A technical certificate proofing the identity through cryptographic measures.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "has policy administration point"
	* "The access control administration policy point of the AAS."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Certificate/policyAdministrationPoint")
	protected PolicyAdministrationPoint policyAdministrationPoint;


	// no manual construction
	protected DefaultCertificate() {
		id = VocabUtil.getInstance().createRandomUrl("certificate");
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
		return Objects.hash(new Object[]{this.policyAdministrationPoint});
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
			DefaultCertificate other = (DefaultCertificate) obj;
			return Objects.equals(this.policyAdministrationPoint, other.policyAdministrationPoint);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/Certificate/policyAdministrationPoint")
	final public PolicyAdministrationPoint getPolicyAdministrationPoint() {
		return policyAdministrationPoint;
	}
	
	final public void setPolicyAdministrationPoint (PolicyAdministrationPoint policyAdministrationPoint) {
		this.policyAdministrationPoint = policyAdministrationPoint;
	}
}
