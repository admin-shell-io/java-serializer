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

public class DefaultAccessControl implements AccessControl {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Access Control", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("Access Control defines the local access control policy administration point. Access Control has the major task to define the access permission rules.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "has access permission rule"
	* "Access permission rules of the AAS describing the rights assigned to (already authenticated) subjects to access elements of the AAS."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/accessPermissionRule")
	protected List<AccessPermissionRule> accessPermissionRules;


	/**
	* "has default environment attributes"
	* "Reference to a submodel defining default environment attributes, i.e. attributes that are not describing the asset itself. The submodel is of kind=Type. At the same type the values of these environment attributes need to be accessible when evaluating the access permission rules. This is realized as a policy information point."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/defaultEnvironmentAttributes")
	protected Reference defaultEnvironmentAttributes;


	/**
	* "has default permissions"
	* "Reference to a submodel defining the default permissions for the AAS."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/defaultPermissions")
	protected Reference defaultPermissions;


	/**
	* "has default subject attributes"
	* "Reference to a submodel defining the default subjects attributes for the AAS that can be used to describe access permission rules."@en
	* "The submodel is of kind=Type."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/defaultSubjectAttributes")
	protected Reference defaultSubjectAttributes;


	/**
	* "has selectable environment attributes"
	* "Reference to a submodel defining which environment attributes can be accessed via the permission rules."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/selectableEnvironmentAttributes")
	protected Reference selectableEnvironmentAttributes;


	/**
	* "has selectable permissions"
	* "Reference to a submodel defining which permissions can be assigned to the subjects."@en
	* "Default: reference to the submodel referenced via defaultPermissions"@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/selectablePermissions")
	protected Reference selectablePermissions;


	/**
	* "has selectable subject attributes"
	* "Reference to a submodel defining the authenticated subjects that are configured for the AAS. They are selectable by the access permission rules to assign permissions to the subjects."@en
	* "Default: reference to the submodel referenced via defaultSubjectAttributes."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/selectableSubjectAttributes")
	protected Reference selectableSubjectAttributes;


	// no manual construction
	protected DefaultAccessControl() {
		id = VocabUtil.getInstance().createRandomUrl("accessControl");
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
		return Objects.hash(new Object[]{this.accessPermissionRules,
			this.selectableSubjectAttributes,
			this.defaultSubjectAttributes,
			this.selectablePermissions,
			this.defaultPermissions,
			this.selectableEnvironmentAttributes,
			this.defaultEnvironmentAttributes});
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
			DefaultAccessControl other = (DefaultAccessControl) obj;
			return Objects.equals(this.accessPermissionRules, other.accessPermissionRules) &&
				Objects.equals(this.selectableSubjectAttributes, other.selectableSubjectAttributes) &&
				Objects.equals(this.defaultSubjectAttributes, other.defaultSubjectAttributes) &&
				Objects.equals(this.selectablePermissions, other.selectablePermissions) &&
				Objects.equals(this.defaultPermissions, other.defaultPermissions) &&
				Objects.equals(this.selectableEnvironmentAttributes, other.selectableEnvironmentAttributes) &&
				Objects.equals(this.defaultEnvironmentAttributes, other.defaultEnvironmentAttributes);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/accessPermissionRule")
	final public List<AccessPermissionRule> getAccessPermissionRules() {
		return accessPermissionRules;
	}
	
	final public void setAccessPermissionRules (List<AccessPermissionRule> accessPermissionRules) {
		this.accessPermissionRules = accessPermissionRules;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/selectableSubjectAttributes")
	final public Reference getSelectableSubjectAttributes() {
		return selectableSubjectAttributes;
	}
	
	final public void setSelectableSubjectAttributes (Reference selectableSubjectAttributes) {
		this.selectableSubjectAttributes = selectableSubjectAttributes;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/defaultSubjectAttributes")
	final public Reference getDefaultSubjectAttributes() {
		return defaultSubjectAttributes;
	}
	
	final public void setDefaultSubjectAttributes (Reference defaultSubjectAttributes) {
		this.defaultSubjectAttributes = defaultSubjectAttributes;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/selectablePermissions")
	final public Reference getSelectablePermissions() {
		return selectablePermissions;
	}
	
	final public void setSelectablePermissions (Reference selectablePermissions) {
		this.selectablePermissions = selectablePermissions;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/defaultPermissions")
	final public Reference getDefaultPermissions() {
		return defaultPermissions;
	}
	
	final public void setDefaultPermissions (Reference defaultPermissions) {
		this.defaultPermissions = defaultPermissions;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/selectableEnvironmentAttributes")
	final public Reference getSelectableEnvironmentAttributes() {
		return selectableEnvironmentAttributes;
	}
	
	final public void setSelectableEnvironmentAttributes (Reference selectableEnvironmentAttributes) {
		this.selectableEnvironmentAttributes = selectableEnvironmentAttributes;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/AccessControl/defaultEnvironmentAttributes")
	final public Reference getDefaultEnvironmentAttributes() {
		return defaultEnvironmentAttributes;
	}
	
	final public void setDefaultEnvironmentAttributes (Reference defaultEnvironmentAttributes) {
		this.defaultEnvironmentAttributes = defaultEnvironmentAttributes;
	}
}
