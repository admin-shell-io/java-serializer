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

public class FileBuilder {

	private DefaultFile defaultFile;

	public FileBuilder() {
		defaultFile = new DefaultFile();
	}

	public FileBuilder(URI id) {
		this();
		defaultFile.id = id;
	}

	/**
	* This function allows setting a value for mimeType
	* @param mimeType desired value to be set
	* @return Builder object with new value for mimeType
	*/
	final public FileBuilder mimeType(String mimeType) {
		this.defaultFile.mimeType = mimeType;
		return this;
	}


	/**
	* This function allows setting a value for value
	* @param value desired value to be set
	* @return Builder object with new value for value
	*/
	final public FileBuilder value(String value) {
		this.defaultFile.value = value;
		return this;
	}



	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public FileBuilder referableCategories(List<String> referableCategories) {
		this.defaultFile.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public FileBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultFile.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public FileBuilder displayName(TypedLiteral displayName) {
		this.defaultFile.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public FileBuilder idShort(String idShort) {
		this.defaultFile.idShort = idShort;
		return this;
	}


	/**
	* This function allows setting a value for qualifiers
	* @param qualifiers desired value to be set
	* @return Builder object with new value for qualifiers
	*/
	final public FileBuilder qualifiers(List<Constraint> qualifiers) {
		this.defaultFile.qualifiers = qualifiers;
		return this;
	}


	/**
	* This function allows setting a value for dataSpecifications
	* @param dataSpecifications desired value to be set
	* @return Builder object with new value for dataSpecifications
	*/
	final public FileBuilder dataSpecifications(List<Reference> dataSpecifications) {
		this.defaultFile.dataSpecifications = dataSpecifications;
		return this;
	}


	/**
	* This function allows setting a value for kind
	* @param kind desired value to be set
	* @return Builder object with new value for kind
	*/
	final public FileBuilder kind(ModelingKind kind) {
		this.defaultFile.kind = kind;
		return this;
	}


	/**
	* This function allows setting a value for semanticId
	* @param semanticId desired value to be set
	* @return Builder object with new value for semanticId
	*/
	final public FileBuilder semanticId(Reference semanticId) {
		this.defaultFile.semanticId = semanticId;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public File build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultFile);
		return defaultFile;
	}
}
