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

public class KeyBuilder {

	private DefaultKey defaultKey;

	public KeyBuilder() {
		defaultKey = new DefaultKey();
	}

	public KeyBuilder(URI id) {
		this();
		defaultKey.id = id;
	}

	/**
	* This function allows setting a value for idType
	* @param idType desired value to be set
	* @return Builder object with new value for idType
	*/
	final public KeyBuilder idType(KeyType idType) {
		this.defaultKey.idType = idType;
		return this;
	}


	/**
	* This function allows setting a value for type
	* @param type desired value to be set
	* @return Builder object with new value for type
	*/
	final public KeyBuilder type(KeyElements type) {
		this.defaultKey.type = type;
		return this;
	}


	/**
	* This function allows setting a value for value
	* @param value desired value to be set
	* @return Builder object with new value for value
	*/
	final public KeyBuilder value(String value) {
		this.defaultKey.value = value;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public Key build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultKey);
		return defaultKey;
	}
}
