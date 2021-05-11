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
* "Security"
* "Container for security relevant information of the AAS."@en 
*/

public class DefaultSecurity implements Security {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Security", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("Container for security relevant information of the AAS.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "has access control policy points"
	* "Access control policy points of the AAS."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Security/accessControlPolicyPoints")
	protected AccessControlPolicyPoints accessControlPolicyPoints;


	/**
	* "has certificate"
	* "Certificates of the AAS."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Security/certificate")
	protected List<Certificate> certificates;


	/**
	* "has required certificate extension"
	* "Certificate extensions as required by the AAS."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Security/requiredCertificateExtension")
	protected List<Reference> requiredCertificateExtensions;


	// no manual construction
	protected DefaultSecurity() {
		id = VocabUtil.getInstance().createRandomUrl("security");
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
		return Objects.hash(new Object[]{this.accessControlPolicyPoints,
			this.certificates,
			this.requiredCertificateExtensions});
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
			DefaultSecurity other = (DefaultSecurity) obj;
			return Objects.equals(this.accessControlPolicyPoints, other.accessControlPolicyPoints) &&
				Objects.equals(this.certificates, other.certificates) &&
				Objects.equals(this.requiredCertificateExtensions, other.requiredCertificateExtensions);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/Security/accessControlPolicyPoints")
	final public AccessControlPolicyPoints getAccessControlPolicyPoints() {
		return accessControlPolicyPoints;
	}
	
	final public void setAccessControlPolicyPoints (AccessControlPolicyPoints accessControlPolicyPoints) {
		this.accessControlPolicyPoints = accessControlPolicyPoints;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/Security/certificate")
	final public List<Certificate> getCertificates() {
		return certificates;
	}
	
	final public void setCertificates (List<Certificate> certificates) {
		this.certificates = certificates;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/Security/requiredCertificateExtension")
	final public List<Reference> getRequiredCertificateExtensions() {
		return requiredCertificateExtensions;
	}
	
	final public void setRequiredCertificateExtensions (List<Reference> requiredCertificateExtensions) {
		this.requiredCertificateExtensions = requiredCertificateExtensions;
	}
}
