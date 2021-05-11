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
* "Access Control"
* "Access Control defines the local access control policy administration point. Access Control has the major task to define the access permission rules."@en
*/
@KnownSubtypes({
	@KnownSubtypes.Type(value = DefaultAccessControl.class)
})
public interface AccessControl {

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
	* "Access permission rules of the AAS describing the rights assigned to (already authenticated) subjects to access elements of the AAS."@en
	* @return Returns the List of AccessPermissionRules for the property accessPermissionRules.
	* More information under https://admin-shell.io/aas/3/0/RC01/AccessControl/accessPermissionRule
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/accessPermissionRule")
	public List<AccessPermissionRule> getAccessPermissionRules();

	/**
	* "Reference to a submodel defining the authenticated subjects that are configured for the AAS. They are selectable by the access permission rules to assign permissions to the subjects."@en
	* "Default: reference to the submodel referenced via defaultSubjectAttributes."@en
	* @return Returns the Reference for the property selectableSubjectAttributes.
	* More information under https://admin-shell.io/aas/3/0/RC01/AccessControl/selectableSubjectAttributes
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/selectableSubjectAttributes")
	public Reference getSelectableSubjectAttributes();

	/**
	* "Reference to a submodel defining the default subjects attributes for the AAS that can be used to describe access permission rules."@en
	* "The submodel is of kind=Type."@en
	* @return Returns the Reference for the property defaultSubjectAttributes.
	* More information under https://admin-shell.io/aas/3/0/RC01/AccessControl/defaultSubjectAttributes
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/defaultSubjectAttributes")
	public Reference getDefaultSubjectAttributes();

	/**
	* "Reference to a submodel defining which permissions can be assigned to the subjects."@en
	* "Default: reference to the submodel referenced via defaultPermissions"@en
	* @return Returns the Reference for the property selectablePermissions.
	* More information under https://admin-shell.io/aas/3/0/RC01/AccessControl/selectablePermissions
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/selectablePermissions")
	public Reference getSelectablePermissions();

	/**
	* "Reference to a submodel defining the default permissions for the AAS."@en
	* @return Returns the Reference for the property defaultPermissions.
	* More information under https://admin-shell.io/aas/3/0/RC01/AccessControl/defaultPermissions
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/defaultPermissions")
	public Reference getDefaultPermissions();

	/**
	* "Reference to a submodel defining which environment attributes can be accessed via the permission rules."@en
	* @return Returns the Reference for the property selectableEnvironmentAttributes.
	* More information under https://admin-shell.io/aas/3/0/RC01/AccessControl/selectableEnvironmentAttributes
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/selectableEnvironmentAttributes")
	public Reference getSelectableEnvironmentAttributes();

	/**
	* "Reference to a submodel defining default environment attributes, i.e. attributes that are not describing the asset itself. The submodel is of kind=Type. At the same type the values of these environment attributes need to be accessible when evaluating the access permission rules. This is realized as a policy information point."@en
	* @return Returns the Reference for the property defaultEnvironmentAttributes.
	* More information under https://admin-shell.io/aas/3/0/RC01/AccessControl/defaultEnvironmentAttributes
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/defaultEnvironmentAttributes")
	public Reference getDefaultEnvironmentAttributes();

}
