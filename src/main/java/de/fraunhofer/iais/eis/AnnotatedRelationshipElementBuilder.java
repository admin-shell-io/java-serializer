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

public class AnnotatedRelationshipElementBuilder {

	private DefaultAnnotatedRelationshipElement defaultAnnotatedRelationshipElement;

	public AnnotatedRelationshipElementBuilder() {
		defaultAnnotatedRelationshipElement = new DefaultAnnotatedRelationshipElement();
	}

	public AnnotatedRelationshipElementBuilder(URI id) {
		this();
		defaultAnnotatedRelationshipElement.id = id;
	}

	/**
	* This function allows setting a value for annotations
	* @param annotations desired value to be set
	* @return Builder object with new value for annotations
	*/
	final public AnnotatedRelationshipElementBuilder annotations(List<Reference> annotations) {
		this.defaultAnnotatedRelationshipElement.annotations = annotations;
		return this;
	}


	/**
	* This function allows setting a value for first
	* @param first desired value to be set
	* @return Builder object with new value for first
	*/
	final public AnnotatedRelationshipElementBuilder first(Reference first) {
		this.defaultAnnotatedRelationshipElement.first = first;
		return this;
	}


	/**
	* This function allows setting a value for second
	* @param second desired value to be set
	* @return Builder object with new value for second
	*/
	final public AnnotatedRelationshipElementBuilder second(Reference second) {
		this.defaultAnnotatedRelationshipElement.second = second;
		return this;
	}



	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public AnnotatedRelationshipElementBuilder referableCategories(List<String> referableCategories) {
		this.defaultAnnotatedRelationshipElement.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public AnnotatedRelationshipElementBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultAnnotatedRelationshipElement.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public AnnotatedRelationshipElementBuilder displayName(TypedLiteral displayName) {
		this.defaultAnnotatedRelationshipElement.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public AnnotatedRelationshipElementBuilder idShort(String idShort) {
		this.defaultAnnotatedRelationshipElement.idShort = idShort;
		return this;
	}


	/**
	* This function allows setting a value for qualifiers
	* @param qualifiers desired value to be set
	* @return Builder object with new value for qualifiers
	*/
	final public AnnotatedRelationshipElementBuilder qualifiers(List<Constraint> qualifiers) {
		this.defaultAnnotatedRelationshipElement.qualifiers = qualifiers;
		return this;
	}


	/**
	* This function allows setting a value for dataSpecifications
	* @param dataSpecifications desired value to be set
	* @return Builder object with new value for dataSpecifications
	*/
	final public AnnotatedRelationshipElementBuilder dataSpecifications(List<Reference> dataSpecifications) {
		this.defaultAnnotatedRelationshipElement.dataSpecifications = dataSpecifications;
		return this;
	}


	/**
	* This function allows setting a value for kind
	* @param kind desired value to be set
	* @return Builder object with new value for kind
	*/
	final public AnnotatedRelationshipElementBuilder kind(ModelingKind kind) {
		this.defaultAnnotatedRelationshipElement.kind = kind;
		return this;
	}


	/**
	* This function allows setting a value for semanticId
	* @param semanticId desired value to be set
	* @return Builder object with new value for semanticId
	*/
	final public AnnotatedRelationshipElementBuilder semanticId(Reference semanticId) {
		this.defaultAnnotatedRelationshipElement.semanticId = semanticId;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public AnnotatedRelationshipElement build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultAnnotatedRelationshipElement);
		return defaultAnnotatedRelationshipElement;
	}
}
