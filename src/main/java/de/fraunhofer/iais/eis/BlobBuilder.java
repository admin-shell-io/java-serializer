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

public class BlobBuilder {

	private DefaultBlob defaultBlob;

	public BlobBuilder() {
		defaultBlob = new DefaultBlob();
	}

	public BlobBuilder(URI id) {
		this();
		defaultBlob.id = id;
	}

	/**
	* This function allows setting a value for mimeType
	* @param mimeType desired value to be set
	* @return Builder object with new value for mimeType
	*/
	final public BlobBuilder mimeType(String mimeType) {
		this.defaultBlob.mimeType = mimeType;
		return this;
	}


	/**
	* This function allows setting a value for value
	* @param value desired value to be set
	* @return Builder object with new value for value
	*/
	final public BlobBuilder value(byte[] value) {
		this.defaultBlob.value = value;
		return this;
	}




	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public BlobBuilder referableCategories(List<String> referableCategories) {
		this.defaultBlob.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public BlobBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultBlob.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public BlobBuilder displayName(TypedLiteral displayName) {
		this.defaultBlob.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public BlobBuilder idShort(String idShort) {
		this.defaultBlob.idShort = idShort;
		return this;
	}


	/**
	* This function allows setting a value for qualifiers
	* @param qualifiers desired value to be set
	* @return Builder object with new value for qualifiers
	*/
	final public BlobBuilder qualifiers(List<Constraint> qualifiers) {
		this.defaultBlob.qualifiers = qualifiers;
		return this;
	}


	/**
	* This function allows setting a value for dataSpecifications
	* @param dataSpecifications desired value to be set
	* @return Builder object with new value for dataSpecifications
	*/
	final public BlobBuilder dataSpecifications(List<Reference> dataSpecifications) {
		this.defaultBlob.dataSpecifications = dataSpecifications;
		return this;
	}


	/**
	* This function allows setting a value for kind
	* @param kind desired value to be set
	* @return Builder object with new value for kind
	*/
	final public BlobBuilder kind(ModelingKind kind) {
		this.defaultBlob.kind = kind;
		return this;
	}


	/**
	* This function allows setting a value for semanticId
	* @param semanticId desired value to be set
	* @return Builder object with new value for semanticId
	*/
	final public BlobBuilder semanticId(Reference semanticId) {
		this.defaultBlob.semanticId = semanticId;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public Blob build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultBlob);
		return defaultBlob;
	}
}
