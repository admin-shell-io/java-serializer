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

public class BasicEventBuilder {

	private DefaultBasicEvent defaultBasicEvent;

	public BasicEventBuilder() {
		defaultBasicEvent = new DefaultBasicEvent();
	}

	public BasicEventBuilder(URI id) {
		this();
		defaultBasicEvent.id = id;
	}

	/**
	* This function allows setting a value for observed
	* @param observed desired value to be set
	* @return Builder object with new value for observed
	*/
	final public BasicEventBuilder observed(Reference observed) {
		this.defaultBasicEvent.observed = observed;
		return this;
	}




	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public BasicEventBuilder referableCategories(List<String> referableCategories) {
		this.defaultBasicEvent.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public BasicEventBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultBasicEvent.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public BasicEventBuilder displayName(TypedLiteral displayName) {
		this.defaultBasicEvent.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public BasicEventBuilder idShort(String idShort) {
		this.defaultBasicEvent.idShort = idShort;
		return this;
	}


	/**
	* This function allows setting a value for qualifiers
	* @param qualifiers desired value to be set
	* @return Builder object with new value for qualifiers
	*/
	final public BasicEventBuilder qualifiers(List<Constraint> qualifiers) {
		this.defaultBasicEvent.qualifiers = qualifiers;
		return this;
	}


	/**
	* This function allows setting a value for dataSpecifications
	* @param dataSpecifications desired value to be set
	* @return Builder object with new value for dataSpecifications
	*/
	final public BasicEventBuilder dataSpecifications(List<Reference> dataSpecifications) {
		this.defaultBasicEvent.dataSpecifications = dataSpecifications;
		return this;
	}


	/**
	* This function allows setting a value for kind
	* @param kind desired value to be set
	* @return Builder object with new value for kind
	*/
	final public BasicEventBuilder kind(ModelingKind kind) {
		this.defaultBasicEvent.kind = kind;
		return this;
	}


	/**
	* This function allows setting a value for semanticId
	* @param semanticId desired value to be set
	* @return Builder object with new value for semanticId
	*/
	final public BasicEventBuilder semanticId(Reference semanticId) {
		this.defaultBasicEvent.semanticId = semanticId;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public BasicEvent build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultBasicEvent);
		return defaultBasicEvent;
	}
}
