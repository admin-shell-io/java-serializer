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

public class ReferenceElementBuilder {

	private DefaultReferenceElement defaultReferenceElement;

	public ReferenceElementBuilder() {
		defaultReferenceElement = new DefaultReferenceElement();
	}

	public ReferenceElementBuilder(URI id) {
		this();
		defaultReferenceElement.id = id;
	}

	/**
	* This function allows setting a value for value
	* @param value desired value to be set
	* @return Builder object with new value for value
	*/
	final public ReferenceElementBuilder value(Reference value) {
		this.defaultReferenceElement.value = value;
		return this;
	}




	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public ReferenceElementBuilder referableCategories(List<String> referableCategories) {
		this.defaultReferenceElement.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public ReferenceElementBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultReferenceElement.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public ReferenceElementBuilder displayName(TypedLiteral displayName) {
		this.defaultReferenceElement.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public ReferenceElementBuilder idShort(String idShort) {
		this.defaultReferenceElement.idShort = idShort;
		return this;
	}


	/**
	* This function allows setting a value for qualifiers
	* @param qualifiers desired value to be set
	* @return Builder object with new value for qualifiers
	*/
	final public ReferenceElementBuilder qualifiers(List<Constraint> qualifiers) {
		this.defaultReferenceElement.qualifiers = qualifiers;
		return this;
	}


	/**
	* This function allows setting a value for dataSpecifications
	* @param dataSpecifications desired value to be set
	* @return Builder object with new value for dataSpecifications
	*/
	final public ReferenceElementBuilder dataSpecifications(List<Reference> dataSpecifications) {
		this.defaultReferenceElement.dataSpecifications = dataSpecifications;
		return this;
	}


	/**
	* This function allows setting a value for kind
	* @param kind desired value to be set
	* @return Builder object with new value for kind
	*/
	final public ReferenceElementBuilder kind(ModelingKind kind) {
		this.defaultReferenceElement.kind = kind;
		return this;
	}


	/**
	* This function allows setting a value for semanticId
	* @param semanticId desired value to be set
	* @return Builder object with new value for semanticId
	*/
	final public ReferenceElementBuilder semanticId(Reference semanticId) {
		this.defaultReferenceElement.semanticId = semanticId;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public ReferenceElement build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultReferenceElement);
		return defaultReferenceElement;
	}
}
