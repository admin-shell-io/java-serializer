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

public class IdentifiableBuilder {

	private DefaultIdentifiable defaultIdentifiable;

	public IdentifiableBuilder() {
		defaultIdentifiable = new DefaultIdentifiable();
	}

	public IdentifiableBuilder(URI id) {
		this();
		defaultIdentifiable.id = id;
	}

	/**
	* This function allows setting a value for administration
	* @param administration desired value to be set
	* @return Builder object with new value for administration
	*/
	final public IdentifiableBuilder administration(AdministrativeInformation administration) {
		this.defaultIdentifiable.administration = administration;
		return this;
	}


	/**
	* This function allows setting a value for identification
	* @param identification desired value to be set
	* @return Builder object with new value for identification
	*/
	final public IdentifiableBuilder identification(Identifier identification) {
		this.defaultIdentifiable.identification = identification;
		return this;
	}


	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public IdentifiableBuilder referableCategories(List<String> referableCategories) {
		this.defaultIdentifiable.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public IdentifiableBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultIdentifiable.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public IdentifiableBuilder displayName(TypedLiteral displayName) {
		this.defaultIdentifiable.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public IdentifiableBuilder idShort(String idShort) {
		this.defaultIdentifiable.idShort = idShort;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public Identifiable build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultIdentifiable);
		return defaultIdentifiable;
	}
}
