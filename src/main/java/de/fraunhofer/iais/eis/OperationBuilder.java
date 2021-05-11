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

public class OperationBuilder {

	private DefaultOperation defaultOperation;

	public OperationBuilder() {
		defaultOperation = new DefaultOperation();
	}

	public OperationBuilder(URI id) {
		this();
		defaultOperation.id = id;
	}

	/**
	* This function allows setting a value for inputVariables
	* @param inputVariables desired value to be set
	* @return Builder object with new value for inputVariables
	*/
	final public OperationBuilder inputVariables(List<OperationVariable> inputVariables) {
		this.defaultOperation.inputVariables = inputVariables;
		return this;
	}


	/**
	* This function allows setting a value for inoutputVariables
	* @param inoutputVariables desired value to be set
	* @return Builder object with new value for inoutputVariables
	*/
	final public OperationBuilder inoutputVariables(List<OperationVariable> inoutputVariables) {
		this.defaultOperation.inoutputVariables = inoutputVariables;
		return this;
	}


	/**
	* This function allows setting a value for outputVariables
	* @param outputVariables desired value to be set
	* @return Builder object with new value for outputVariables
	*/
	final public OperationBuilder outputVariables(List<OperationVariable> outputVariables) {
		this.defaultOperation.outputVariables = outputVariables;
		return this;
	}



	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public OperationBuilder referableCategories(List<String> referableCategories) {
		this.defaultOperation.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public OperationBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultOperation.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public OperationBuilder displayName(TypedLiteral displayName) {
		this.defaultOperation.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public OperationBuilder idShort(String idShort) {
		this.defaultOperation.idShort = idShort;
		return this;
	}


	/**
	* This function allows setting a value for qualifiers
	* @param qualifiers desired value to be set
	* @return Builder object with new value for qualifiers
	*/
	final public OperationBuilder qualifiers(List<Constraint> qualifiers) {
		this.defaultOperation.qualifiers = qualifiers;
		return this;
	}


	/**
	* This function allows setting a value for dataSpecifications
	* @param dataSpecifications desired value to be set
	* @return Builder object with new value for dataSpecifications
	*/
	final public OperationBuilder dataSpecifications(List<Reference> dataSpecifications) {
		this.defaultOperation.dataSpecifications = dataSpecifications;
		return this;
	}


	/**
	* This function allows setting a value for kind
	* @param kind desired value to be set
	* @return Builder object with new value for kind
	*/
	final public OperationBuilder kind(ModelingKind kind) {
		this.defaultOperation.kind = kind;
		return this;
	}


	/**
	* This function allows setting a value for semanticId
	* @param semanticId desired value to be set
	* @return Builder object with new value for semanticId
	*/
	final public OperationBuilder semanticId(Reference semanticId) {
		this.defaultOperation.semanticId = semanticId;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public Operation build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultOperation);
		return defaultOperation;
	}
}
