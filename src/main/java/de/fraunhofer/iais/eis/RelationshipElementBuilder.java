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

public class RelationshipElementBuilder {

	private DefaultRelationshipElement defaultRelationshipElement;

	public RelationshipElementBuilder() {
		defaultRelationshipElement = new DefaultRelationshipElement();
	}

	public RelationshipElementBuilder(URI id) {
		this();
		defaultRelationshipElement.id = id;
	}

	/**
	* This function allows setting a value for first
	* @param first desired value to be set
	* @return Builder object with new value for first
	*/
	final public RelationshipElementBuilder first(Reference first) {
		this.defaultRelationshipElement.first = first;
		return this;
	}


	/**
	* This function allows setting a value for second
	* @param second desired value to be set
	* @return Builder object with new value for second
	*/
	final public RelationshipElementBuilder second(Reference second) {
		this.defaultRelationshipElement.second = second;
		return this;
	}



	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public RelationshipElementBuilder referableCategories(List<String> referableCategories) {
		this.defaultRelationshipElement.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public RelationshipElementBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultRelationshipElement.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public RelationshipElementBuilder displayName(TypedLiteral displayName) {
		this.defaultRelationshipElement.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public RelationshipElementBuilder idShort(String idShort) {
		this.defaultRelationshipElement.idShort = idShort;
		return this;
	}


	/**
	* This function allows setting a value for qualifiers
	* @param qualifiers desired value to be set
	* @return Builder object with new value for qualifiers
	*/
	final public RelationshipElementBuilder qualifiers(List<Constraint> qualifiers) {
		this.defaultRelationshipElement.qualifiers = qualifiers;
		return this;
	}


	/**
	* This function allows setting a value for dataSpecifications
	* @param dataSpecifications desired value to be set
	* @return Builder object with new value for dataSpecifications
	*/
	final public RelationshipElementBuilder dataSpecifications(List<Reference> dataSpecifications) {
		this.defaultRelationshipElement.dataSpecifications = dataSpecifications;
		return this;
	}


	/**
	* This function allows setting a value for kind
	* @param kind desired value to be set
	* @return Builder object with new value for kind
	*/
	final public RelationshipElementBuilder kind(ModelingKind kind) {
		this.defaultRelationshipElement.kind = kind;
		return this;
	}


	/**
	* This function allows setting a value for semanticId
	* @param semanticId desired value to be set
	* @return Builder object with new value for semanticId
	*/
	final public RelationshipElementBuilder semanticId(Reference semanticId) {
		this.defaultRelationshipElement.semanticId = semanticId;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public RelationshipElement build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultRelationshipElement);
		return defaultRelationshipElement;
	}
}
