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

public class AccessControlBuilder {

	private DefaultAccessControl defaultAccessControl;

	public AccessControlBuilder() {
		defaultAccessControl = new DefaultAccessControl();
	}

	public AccessControlBuilder(URI id) {
		this();
		defaultAccessControl.id = id;
	}

	/**
	* This function allows setting a value for accessPermissionRules
	* @param accessPermissionRules desired value to be set
	* @return Builder object with new value for accessPermissionRules
	*/
	final public AccessControlBuilder accessPermissionRules(List<AccessPermissionRule> accessPermissionRules) {
		this.defaultAccessControl.accessPermissionRules = accessPermissionRules;
		return this;
	}


	/**
	* This function allows setting a value for selectableSubjectAttributes
	* @param selectableSubjectAttributes desired value to be set
	* @return Builder object with new value for selectableSubjectAttributes
	*/
	final public AccessControlBuilder selectableSubjectAttributes(Reference selectableSubjectAttributes) {
		this.defaultAccessControl.selectableSubjectAttributes = selectableSubjectAttributes;
		return this;
	}


	/**
	* This function allows setting a value for defaultSubjectAttributes
	* @param defaultSubjectAttributes desired value to be set
	* @return Builder object with new value for defaultSubjectAttributes
	*/
	final public AccessControlBuilder defaultSubjectAttributes(Reference defaultSubjectAttributes) {
		this.defaultAccessControl.defaultSubjectAttributes = defaultSubjectAttributes;
		return this;
	}


	/**
	* This function allows setting a value for selectablePermissions
	* @param selectablePermissions desired value to be set
	* @return Builder object with new value for selectablePermissions
	*/
	final public AccessControlBuilder selectablePermissions(Reference selectablePermissions) {
		this.defaultAccessControl.selectablePermissions = selectablePermissions;
		return this;
	}


	/**
	* This function allows setting a value for defaultPermissions
	* @param defaultPermissions desired value to be set
	* @return Builder object with new value for defaultPermissions
	*/
	final public AccessControlBuilder defaultPermissions(Reference defaultPermissions) {
		this.defaultAccessControl.defaultPermissions = defaultPermissions;
		return this;
	}


	/**
	* This function allows setting a value for selectableEnvironmentAttributes
	* @param selectableEnvironmentAttributes desired value to be set
	* @return Builder object with new value for selectableEnvironmentAttributes
	*/
	final public AccessControlBuilder selectableEnvironmentAttributes(Reference selectableEnvironmentAttributes) {
		this.defaultAccessControl.selectableEnvironmentAttributes = selectableEnvironmentAttributes;
		return this;
	}


	/**
	* This function allows setting a value for defaultEnvironmentAttributes
	* @param defaultEnvironmentAttributes desired value to be set
	* @return Builder object with new value for defaultEnvironmentAttributes
	*/
	final public AccessControlBuilder defaultEnvironmentAttributes(Reference defaultEnvironmentAttributes) {
		this.defaultAccessControl.defaultEnvironmentAttributes = defaultEnvironmentAttributes;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public AccessControl build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultAccessControl);
		return defaultAccessControl;
	}
}
