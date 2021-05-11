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

public class PropertyBuilder {

	private DefaultProperty defaultProperty;

	public PropertyBuilder() {
		defaultProperty = new DefaultProperty();
	}

	public PropertyBuilder(URI id) {
		this();
		defaultProperty.id = id;
	}

	/**
	* This function allows setting a value for valueType
	* @param valueType desired value to be set
	* @return Builder object with new value for valueType
	*/
	final public PropertyBuilder valueType(String valueType) {
		this.defaultProperty.valueType = valueType;
		return this;
	}


	/**
	* This function allows setting a value for value
	* @param value desired value to be set
	* @return Builder object with new value for value
	*/
	final public PropertyBuilder value(TypedLiteral value) {
		this.defaultProperty.value = value;
		return this;
	}


	/**
	* This function allows setting a value for valueId
	* @param valueId desired value to be set
	* @return Builder object with new value for valueId
	*/
	final public PropertyBuilder valueId(Reference valueId) {
		this.defaultProperty.valueId = valueId;
		return this;
	}




	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public PropertyBuilder referableCategories(List<String> referableCategories) {
		this.defaultProperty.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public PropertyBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultProperty.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public PropertyBuilder displayName(TypedLiteral displayName) {
		this.defaultProperty.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public PropertyBuilder idShort(String idShort) {
		this.defaultProperty.idShort = idShort;
		return this;
	}


	/**
	* This function allows setting a value for qualifiers
	* @param qualifiers desired value to be set
	* @return Builder object with new value for qualifiers
	*/
	final public PropertyBuilder qualifiers(List<Constraint> qualifiers) {
		this.defaultProperty.qualifiers = qualifiers;
		return this;
	}


	/**
	* This function allows setting a value for dataSpecifications
	* @param dataSpecifications desired value to be set
	* @return Builder object with new value for dataSpecifications
	*/
	final public PropertyBuilder dataSpecifications(List<Reference> dataSpecifications) {
		this.defaultProperty.dataSpecifications = dataSpecifications;
		return this;
	}


	/**
	* This function allows setting a value for kind
	* @param kind desired value to be set
	* @return Builder object with new value for kind
	*/
	final public PropertyBuilder kind(ModelingKind kind) {
		this.defaultProperty.kind = kind;
		return this;
	}


	/**
	* This function allows setting a value for semanticId
	* @param semanticId desired value to be set
	* @return Builder object with new value for semanticId
	*/
	final public PropertyBuilder semanticId(Reference semanticId) {
		this.defaultProperty.semanticId = semanticId;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public Property build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultProperty);
		return defaultProperty;
	}
}
