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
* "Administrative Information"
* "Every Identifiable may have administrative information. Administrative information includes for example 1) Information about the version of the element 2) Information about who created or who made the last change to the element 3) Information about the languages available in case the element contains text, for translating purposed also themmaster or default language may be definedIn the first version of the AAS metamodel only version information as defined by IEC 61360 is defined. In later versions additional attributes may be added."@en 
*/

public class DefaultAdministrativeInformation implements AdministrativeInformation {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Administrative Information", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("Every Identifiable may have administrative information. Administrative information includes for example 1) Information about the version of the element 2) Information about who created or who made the last change to the element 3) Information about the languages available in case the element contains text, for translating purposed also themmaster or default language may be definedIn the first version of the AAS metamodel only version information as defined by IEC 61360 is defined. In later versions additional attributes may be added.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "has revision"
	* "Revision of the element."@en
	* "Constraint AASd-005: A revision requires a version. This means, if there is no version there is no revision neither."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AdministrativeInformation/revision")
	protected String revision;


	/**
	* "has version"
	* "Version of the element."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AdministrativeInformation/version")
	protected String version;


	/**
	* "has Data Specification"
	* "Global reference to the data specification template used by the element."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/HasDataSpecification/dataSpecification")
	protected List<Reference> dataSpecifications;


	// no manual construction
	protected DefaultAdministrativeInformation() {
		id = VocabUtil.getInstance().createRandomUrl("administrativeInformation");
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
		return Objects.hash(new Object[]{this.version,
			this.revision,
			this.dataSpecifications});
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
			DefaultAdministrativeInformation other = (DefaultAdministrativeInformation) obj;
			return Objects.equals(this.version, other.version) &&
				Objects.equals(this.revision, other.revision) &&
				Objects.equals(this.dataSpecifications, other.dataSpecifications);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/AdministrativeInformation/version")
	final public String getVersion() {
		return version;
	}
	
	final public void setVersion (String version) {
		this.version = version;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/AdministrativeInformation/revision")
	final public String getRevision() {
		return revision;
	}
	
	final public void setRevision (String revision) {
		this.revision = revision;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/HasDataSpecification/dataSpecification")
	final public List<Reference> getDataSpecifications() {
		return dataSpecifications;
	}
	
	final public void setDataSpecifications (List<Reference> dataSpecifications) {
		this.dataSpecifications = dataSpecifications;
	}
}
