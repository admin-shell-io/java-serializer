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
* "Blob Certificate"
* "Certificate provided as BLOB."@en 
*/

public class DefaultBlobCertificate implements BlobCertificate {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Blob Certificate", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("Certificate provided as BLOB.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "Blob Certificate"
	* "Certificate as BLOB."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/BlobCertificate/blobCertificate")
	protected Blob blobCertificate;


	/**
	* "contains extension"
	* "Extensions contained in the certificate."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/BlobCertificate/containedExtension")
	protected List<Reference> containedExtensions;


	/**
	* "is last certificate"
	* "Denotes whether this certificate is the certificated that fast added last."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/BlobCertificate/lastCertificate")
	protected boolean lastCertificate;


	/**
	* "has policy administration point"
	* "The access control administration policy point of the AAS."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Certificate/policyAdministrationPoint")
	protected PolicyAdministrationPoint policyAdministrationPoint;


	// no manual construction
	protected DefaultBlobCertificate() {
		id = VocabUtil.getInstance().createRandomUrl("blobCertificate");
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
		return Objects.hash(new Object[]{this.blobCertificate,
			this.containedExtensions,
			this.lastCertificate,
			this.policyAdministrationPoint});
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
			DefaultBlobCertificate other = (DefaultBlobCertificate) obj;
			return Objects.equals(this.blobCertificate, other.blobCertificate) &&
				Objects.equals(this.containedExtensions, other.containedExtensions) &&
				Objects.equals(this.lastCertificate, other.lastCertificate) &&
				Objects.equals(this.policyAdministrationPoint, other.policyAdministrationPoint);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/BlobCertificate/blobCertificate")
	final public Blob getBlobCertificate() {
		return blobCertificate;
	}
	
	final public void setBlobCertificate (Blob blobCertificate) {
		this.blobCertificate = blobCertificate;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/BlobCertificate/containedExtension")
	final public List<Reference> getContainedExtensions() {
		return containedExtensions;
	}
	
	final public void setContainedExtensions (List<Reference> containedExtensions) {
		this.containedExtensions = containedExtensions;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/BlobCertificate/lastCertificate")
	final public boolean getLastCertificate() {
		return lastCertificate;
	}
	
	final public void setLastCertificate (boolean lastCertificate) {
		this.lastCertificate = lastCertificate;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/Certificate/policyAdministrationPoint")
	final public PolicyAdministrationPoint getPolicyAdministrationPoint() {
		return policyAdministrationPoint;
	}
	
	final public void setPolicyAdministrationPoint (PolicyAdministrationPoint policyAdministrationPoint) {
		this.policyAdministrationPoint = policyAdministrationPoint;
	}
}
