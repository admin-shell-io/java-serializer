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
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultAccessControlPolicyPoints.class)
})
public interface AccessControlPolicyPoints {

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
	* "The access control administration policy point of the AAS."@en
	* @return Returns the PolicyAdministrationPoint for the property policyAdministrationPoint.
	* More information under https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyAdministrationPoint
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyAdministrationPoint")
	public PolicyAdministrationPoint getPolicyAdministrationPoint();

	/**
	* "The access control policy decision point of the AAS."@en
	* @return Returns the PolicyDecisionPoint for the property policyDecisionPoint.
	* More information under https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyDecisionPoint
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyDecisionPoint")
	public PolicyDecisionPoint getPolicyDecisionPoint();

	/**
	* "The access control policy enforcement point of the AAS."@en
	* @return Returns the PolicyEnforcementPoints for the property policyEnforcementPoint.
	* More information under https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyEnforcementPoint
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyEnforcementPoint")
	public PolicyEnforcementPoints getPolicyEnforcementPoint();

	/**
	* "The access control policy information points of the AAS."@en
	* @return Returns the PolicyInformationPoints for the property policyInformationPoints.
	* More information under https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyInformationPoints
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControlPolicyPoints/policyInformationPoints")
	public PolicyInformationPoints getPolicyInformationPoints();

}
