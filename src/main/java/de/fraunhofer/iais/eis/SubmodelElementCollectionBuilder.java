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

public class SubmodelElementCollectionBuilder {

	private DefaultSubmodelElementCollection defaultSubmodelElementCollection;

	public SubmodelElementCollectionBuilder() {
		defaultSubmodelElementCollection = new DefaultSubmodelElementCollection();
	}

	public SubmodelElementCollectionBuilder(URI id) {
		this();
		defaultSubmodelElementCollection.id = id;
	}

	/**
	* This function allows setting a value for allowDuplicates
	* @param allowDuplicates desired value to be set
	* @return Builder object with new value for allowDuplicates
	*/
	final public SubmodelElementCollectionBuilder allowDuplicates(boolean allowDuplicates) {
		this.defaultSubmodelElementCollection.allowDuplicates = allowDuplicates;
		return this;
	}


	/**
	* This function allows setting a value for ordered
	* @param ordered desired value to be set
	* @return Builder object with new value for ordered
	*/
	final public SubmodelElementCollectionBuilder ordered(boolean ordered) {
		this.defaultSubmodelElementCollection.ordered = ordered;
		return this;
	}


	/**
	* This function allows setting a value for values
	* @param values desired value to be set
	* @return Builder object with new value for values
	*/
	final public SubmodelElementCollectionBuilder values(List<SubmodelElement> values) {
		this.defaultSubmodelElementCollection.values = values;
		return this;
	}



	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public SubmodelElementCollectionBuilder referableCategories(List<String> referableCategories) {
		this.defaultSubmodelElementCollection.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public SubmodelElementCollectionBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultSubmodelElementCollection.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public SubmodelElementCollectionBuilder displayName(TypedLiteral displayName) {
		this.defaultSubmodelElementCollection.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public SubmodelElementCollectionBuilder idShort(String idShort) {
		this.defaultSubmodelElementCollection.idShort = idShort;
		return this;
	}


	/**
	* This function allows setting a value for qualifiers
	* @param qualifiers desired value to be set
	* @return Builder object with new value for qualifiers
	*/
	final public SubmodelElementCollectionBuilder qualifiers(List<Constraint> qualifiers) {
		this.defaultSubmodelElementCollection.qualifiers = qualifiers;
		return this;
	}


	/**
	* This function allows setting a value for dataSpecifications
	* @param dataSpecifications desired value to be set
	* @return Builder object with new value for dataSpecifications
	*/
	final public SubmodelElementCollectionBuilder dataSpecifications(List<Reference> dataSpecifications) {
		this.defaultSubmodelElementCollection.dataSpecifications = dataSpecifications;
		return this;
	}


	/**
	* This function allows setting a value for kind
	* @param kind desired value to be set
	* @return Builder object with new value for kind
	*/
	final public SubmodelElementCollectionBuilder kind(ModelingKind kind) {
		this.defaultSubmodelElementCollection.kind = kind;
		return this;
	}


	/**
	* This function allows setting a value for semanticId
	* @param semanticId desired value to be set
	* @return Builder object with new value for semanticId
	*/
	final public SubmodelElementCollectionBuilder semanticId(Reference semanticId) {
		this.defaultSubmodelElementCollection.semanticId = semanticId;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public SubmodelElementCollection build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultSubmodelElementCollection);
		return defaultSubmodelElementCollection;
	}
}
