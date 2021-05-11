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

public class AccessPermissionRuleBuilder {

	private DefaultAccessPermissionRule defaultAccessPermissionRule;

	public AccessPermissionRuleBuilder() {
		defaultAccessPermissionRule = new DefaultAccessPermissionRule();
	}

	public AccessPermissionRuleBuilder(URI id) {
		this();
		defaultAccessPermissionRule.id = id;
	}

	/**
	* This function allows setting a value for permissionsPerObjects
	* @param permissionsPerObjects desired value to be set
	* @return Builder object with new value for permissionsPerObjects
	*/
	final public AccessPermissionRuleBuilder permissionsPerObjects(List<PermissionsPerObject> permissionsPerObjects) {
		this.defaultAccessPermissionRule.permissionsPerObjects = permissionsPerObjects;
		return this;
	}


	/**
	* This function allows setting a value for targetSubjectAttributes
	* @param targetSubjectAttributes desired value to be set
	* @return Builder object with new value for targetSubjectAttributes
	*/
	final public AccessPermissionRuleBuilder targetSubjectAttributes(SubjectAttributes targetSubjectAttributes) {
		this.defaultAccessPermissionRule.targetSubjectAttributes = targetSubjectAttributes;
		return this;
	}


	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public AccessPermissionRuleBuilder referableCategories(List<String> referableCategories) {
		this.defaultAccessPermissionRule.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public AccessPermissionRuleBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultAccessPermissionRule.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public AccessPermissionRuleBuilder displayName(TypedLiteral displayName) {
		this.defaultAccessPermissionRule.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public AccessPermissionRuleBuilder idShort(String idShort) {
		this.defaultAccessPermissionRule.idShort = idShort;
		return this;
	}


	/**
	* This function allows setting a value for qualifiers
	* @param qualifiers desired value to be set
	* @return Builder object with new value for qualifiers
	*/
	final public AccessPermissionRuleBuilder qualifiers(List<Constraint> qualifiers) {
		this.defaultAccessPermissionRule.qualifiers = qualifiers;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public AccessPermissionRule build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultAccessPermissionRule);
		return defaultAccessPermissionRule;
	}
}
