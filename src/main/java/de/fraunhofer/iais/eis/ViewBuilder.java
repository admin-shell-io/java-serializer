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

public class ViewBuilder {

	private DefaultView defaultView;

	public ViewBuilder() {
		defaultView = new DefaultView();
	}

	public ViewBuilder(URI id) {
		this();
		defaultView.id = id;
	}

	/**
	* This function allows setting a value for containedElements
	* @param containedElements desired value to be set
	* @return Builder object with new value for containedElements
	*/
	final public ViewBuilder containedElements(List<Reference> containedElements) {
		this.defaultView.containedElements = containedElements;
		return this;
	}


	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public ViewBuilder referableCategories(List<String> referableCategories) {
		this.defaultView.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public ViewBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultView.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public ViewBuilder displayName(TypedLiteral displayName) {
		this.defaultView.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public ViewBuilder idShort(String idShort) {
		this.defaultView.idShort = idShort;
		return this;
	}


	/**
	* This function allows setting a value for dataSpecifications
	* @param dataSpecifications desired value to be set
	* @return Builder object with new value for dataSpecifications
	*/
	final public ViewBuilder dataSpecifications(List<Reference> dataSpecifications) {
		this.defaultView.dataSpecifications = dataSpecifications;
		return this;
	}


	/**
	* This function allows setting a value for semanticId
	* @param semanticId desired value to be set
	* @return Builder object with new value for semanticId
	*/
	final public ViewBuilder semanticId(Reference semanticId) {
		this.defaultView.semanticId = semanticId;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public View build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultView);
		return defaultView;
	}
}
