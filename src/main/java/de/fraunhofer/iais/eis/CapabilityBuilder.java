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

public class CapabilityBuilder {

	private DefaultCapability defaultCapability;

	public CapabilityBuilder() {
		defaultCapability = new DefaultCapability();
	}

	public CapabilityBuilder(URI id) {
		this();
		defaultCapability.id = id;
	}



	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public CapabilityBuilder referableCategories(List<String> referableCategories) {
		this.defaultCapability.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public CapabilityBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultCapability.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public CapabilityBuilder displayName(TypedLiteral displayName) {
		this.defaultCapability.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public CapabilityBuilder idShort(String idShort) {
		this.defaultCapability.idShort = idShort;
		return this;
	}


	/**
	* This function allows setting a value for qualifiers
	* @param qualifiers desired value to be set
	* @return Builder object with new value for qualifiers
	*/
	final public CapabilityBuilder qualifiers(List<Constraint> qualifiers) {
		this.defaultCapability.qualifiers = qualifiers;
		return this;
	}


	/**
	* This function allows setting a value for dataSpecifications
	* @param dataSpecifications desired value to be set
	* @return Builder object with new value for dataSpecifications
	*/
	final public CapabilityBuilder dataSpecifications(List<Reference> dataSpecifications) {
		this.defaultCapability.dataSpecifications = dataSpecifications;
		return this;
	}


	/**
	* This function allows setting a value for kind
	* @param kind desired value to be set
	* @return Builder object with new value for kind
	*/
	final public CapabilityBuilder kind(ModelingKind kind) {
		this.defaultCapability.kind = kind;
		return this;
	}


	/**
	* This function allows setting a value for semanticId
	* @param semanticId desired value to be set
	* @return Builder object with new value for semanticId
	*/
	final public CapabilityBuilder semanticId(Reference semanticId) {
		this.defaultCapability.semanticId = semanticId;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public Capability build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultCapability);
		return defaultCapability;
	}
}
