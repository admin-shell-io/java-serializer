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
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultSecurity.class)
})
public interface Security {

	// standard methods

	/**
	* This function retrieves the ID of the current object (can be set via the constructor of the builder class)
	* @return ID of current object as URI
	*/
	public URI getId();

	/**
	* This function retrieves a human readable labels about the current class, as defined in the ontology.
	* This label could, for example, be used as a field heading in a user interface
	* @return Human readable labels
	*/
	public List<TypedLiteral> getLabels();

	/**
	* This function retrieves a human readable explanatory comments about the current class, as defined in the ontology.
	* This comment could, for example, be used as a tooltip in a user interface
	* @return Human readable explanatory comments
	*/
	public List<TypedLiteral> getComments();

	// accessor methods as derived from the Asset Administration Shell ontology


	/**
	* "Access control policy points of the AAS."@en
	* @return Returns the AccessControlPolicyPoints for the property accessControlPolicyPoints.
	* More information under https://admin-shell.io/aas/3/0/RC01/Security/accessControlPolicyPoints
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Security/accessControlPolicyPoints")
	public AccessControlPolicyPoints getAccessControlPolicyPoints();

	/**
	* "Certificates of the AAS."@en
	* @return Returns the List of Certificates for the property certificates.
	* More information under https://admin-shell.io/aas/3/0/RC01/Security/certificate
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Security/certificate")
	public List<Certificate> getCertificates();

	/**
	* "Certificate extensions as required by the AAS."@en
	* @return Returns the List of References for the property requiredCertificateExtensions.
	* More information under https://admin-shell.io/aas/3/0/RC01/Security/requiredCertificateExtension
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Security/requiredCertificateExtension")
	public List<Reference> getRequiredCertificateExtensions();

}
