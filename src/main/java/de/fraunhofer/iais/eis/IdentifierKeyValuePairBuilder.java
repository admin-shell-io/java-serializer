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

public class IdentifierKeyValuePairBuilder {

	private DefaultIdentifierKeyValuePair defaultIdentifierKeyValuePair;

	public IdentifierKeyValuePairBuilder() {
		defaultIdentifierKeyValuePair = new DefaultIdentifierKeyValuePair();
	}

	public IdentifierKeyValuePairBuilder(URI id) {
		this();
		defaultIdentifierKeyValuePair.id = id;
	}

	/**
	* This function allows setting a value for key
	* @param key desired value to be set
	* @return Builder object with new value for key
	*/
	final public IdentifierKeyValuePairBuilder key(String key) {
		this.defaultIdentifierKeyValuePair.key = key;
		return this;
	}


	/**
	* This function allows setting a value for value
	* @param value desired value to be set
	* @return Builder object with new value for value
	*/
	final public IdentifierKeyValuePairBuilder value(String value) {
		this.defaultIdentifierKeyValuePair.value = value;
		return this;
	}


	/**
	* This function allows setting a value for externalSubjectId
	* @param externalSubjectId desired value to be set
	* @return Builder object with new value for externalSubjectId
	*/
	final public IdentifierKeyValuePairBuilder externalSubjectId(Reference externalSubjectId) {
		this.defaultIdentifierKeyValuePair.externalSubjectId = externalSubjectId;
		return this;
	}


	/**
	* This function allows setting a value for semanticId
	* @param semanticId desired value to be set
	* @return Builder object with new value for semanticId
	*/
	final public IdentifierKeyValuePairBuilder semanticId(Reference semanticId) {
		this.defaultIdentifierKeyValuePair.semanticId = semanticId;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public IdentifierKeyValuePair build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultIdentifierKeyValuePair);
		return defaultIdentifierKeyValuePair;
	}
}
