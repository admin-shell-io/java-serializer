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

public class ExtensionBuilder {

	private DefaultExtension defaultExtension;

	public ExtensionBuilder() {
		defaultExtension = new DefaultExtension();
	}

	public ExtensionBuilder(URI id) {
		this();
		defaultExtension.id = id;
	}

	/**
	* This function allows setting a value for name
	* @param name desired value to be set
	* @return Builder object with new value for name
	*/
	final public ExtensionBuilder name(String name) {
		this.defaultExtension.name = name;
		return this;
	}


	/**
	* This function allows setting a value for valueType
	* @param valueType desired value to be set
	* @return Builder object with new value for valueType
	*/
	final public ExtensionBuilder valueType(String valueType) {
		this.defaultExtension.valueType = valueType;
		return this;
	}


	/**
	* This function allows setting a value for value
	* @param value desired value to be set
	* @return Builder object with new value for value
	*/
	final public ExtensionBuilder value(String value) {
		this.defaultExtension.value = value;
		return this;
	}


	/**
	* This function allows setting a value for refersTo
	* @param refersTo desired value to be set
	* @return Builder object with new value for refersTo
	*/
	final public ExtensionBuilder refersTo(Reference refersTo) {
		this.defaultExtension.refersTo = refersTo;
		return this;
	}


	/**
	* This function allows setting a value for semanticId
	* @param semanticId desired value to be set
	* @return Builder object with new value for semanticId
	*/
	final public ExtensionBuilder semanticId(Reference semanticId) {
		this.defaultExtension.semanticId = semanticId;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public Extension build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultExtension);
		return defaultExtension;
	}
}
