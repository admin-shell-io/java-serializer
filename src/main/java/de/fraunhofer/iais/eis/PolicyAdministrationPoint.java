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
* "Policy Administration Point"
* "Definition of a security administration point (PDP)."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultPolicyAdministrationPoint.class)
})
public interface PolicyAdministrationPoint {

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
	* "The policy administration point of access control as realized by the AAS itself."@en
	* "Constraint AASd-009: Either there is an external policy administration point endpoint defined or the AAS has its own access control."@en
	* @return Returns the AccessControl for the property localAccessControl.
	* More information under https://admin-shell.io/aas/3/0/RC01/PolicyAdministrationPoint/localAccessControl
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/PolicyAdministrationPoint/localAccessControl")
	public AccessControl getLocalAccessControl();

	/**
	* "Endpoint to an external access control defining a policy administration point to be used by the AAS."@en
	* @return Returns the boolean for the property externalAccessControl.
	* More information under https://admin-shell.io/aas/3/0/RC01/PolicyAdministrationPoint/externalAccessControl
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/PolicyAdministrationPoint/externalAccessControl")
	public boolean getExternalAccessControl();

}
