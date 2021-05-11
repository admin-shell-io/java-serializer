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

public class ConceptDescriptionBuilder {

	private DefaultConceptDescription defaultConceptDescription;

	public ConceptDescriptionBuilder() {
		defaultConceptDescription = new DefaultConceptDescription();
	}

	public ConceptDescriptionBuilder(URI id) {
		this();
		defaultConceptDescription.id = id;
	}

	/**
	* This function allows setting a value for contents
	* @param contents desired value to be set
	* @return Builder object with new value for contents
	*/
	final public ConceptDescriptionBuilder contents(List<DataSpecificationContent> contents) {
		this.defaultConceptDescription.contents = contents;
		return this;
	}


	/**
	* This function allows setting a value for isCaseOfs
	* @param isCaseOfs desired value to be set
	* @return Builder object with new value for isCaseOfs
	*/
	final public ConceptDescriptionBuilder isCaseOfs(List<Reference> isCaseOfs) {
		this.defaultConceptDescription.isCaseOfs = isCaseOfs;
		return this;
	}


	/**
	* This function allows setting a value for dataSpecifications
	* @param dataSpecifications desired value to be set
	* @return Builder object with new value for dataSpecifications
	*/
	final public ConceptDescriptionBuilder dataSpecifications(List<Reference> dataSpecifications) {
		this.defaultConceptDescription.dataSpecifications = dataSpecifications;
		return this;
	}


	/**
	* This function allows setting a value for administration
	* @param administration desired value to be set
	* @return Builder object with new value for administration
	*/
	final public ConceptDescriptionBuilder administration(AdministrativeInformation administration) {
		this.defaultConceptDescription.administration = administration;
		return this;
	}


	/**
	* This function allows setting a value for identification
	* @param identification desired value to be set
	* @return Builder object with new value for identification
	*/
	final public ConceptDescriptionBuilder identification(Identifier identification) {
		this.defaultConceptDescription.identification = identification;
		return this;
	}


	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public ConceptDescriptionBuilder referableCategories(List<String> referableCategories) {
		this.defaultConceptDescription.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public ConceptDescriptionBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultConceptDescription.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public ConceptDescriptionBuilder displayName(TypedLiteral displayName) {
		this.defaultConceptDescription.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public ConceptDescriptionBuilder idShort(String idShort) {
		this.defaultConceptDescription.idShort = idShort;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public ConceptDescription build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultConceptDescription);
		return defaultConceptDescription;
	}
}
