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

public class RC01Builder {

	private DefaultRC01 defaultRC01;

	public RC01Builder() {
		defaultRC01 = new DefaultRC01();
	}

	public RC01Builder(URI id) {
		this();
		defaultRC01.id = id;
	}

	/**
	* This function allows setting a value for conversionFactors
	* @param conversionFactors desired value to be set
	* @return Builder object with new value for conversionFactors
	*/
	final public RC01Builder conversionFactors(List<String> conversionFactors) {
		this.defaultRC01.conversionFactors = conversionFactors;
		return this;
	}


	/**
	* This function allows setting a value for definitions
	* @param definitions desired value to be set
	* @return Builder object with new value for definitions
	*/
	final public RC01Builder definitions(List<TypedLiteral> definitions) {
		this.defaultRC01.definitions = definitions;
		return this;
	}


	/**
	* This function allows setting a value for dinNotations
	* @param dinNotations desired value to be set
	* @return Builder object with new value for dinNotations
	*/
	final public RC01Builder dinNotations(List<String> dinNotations) {
		this.defaultRC01.dinNotations = dinNotations;
		return this;
	}


	/**
	* This function allows setting a value for eceCodes
	* @param eceCodes desired value to be set
	* @return Builder object with new value for eceCodes
	*/
	final public RC01Builder eceCodes(List<String> eceCodes) {
		this.defaultRC01.eceCodes = eceCodes;
		return this;
	}


	/**
	* This function allows setting a value for eceNames
	* @param eceNames desired value to be set
	* @return Builder object with new value for eceNames
	*/
	final public RC01Builder eceNames(List<String> eceNames) {
		this.defaultRC01.eceNames = eceNames;
		return this;
	}


	/**
	* This function allows setting a value for nistNames
	* @param nistNames desired value to be set
	* @return Builder object with new value for nistNames
	*/
	final public RC01Builder nistNames(List<String> nistNames) {
		this.defaultRC01.nistNames = nistNames;
		return this;
	}


	/**
	* This function allows setting a value for siNames
	* @param siNames desired value to be set
	* @return Builder object with new value for siNames
	*/
	final public RC01Builder siNames(List<String> siNames) {
		this.defaultRC01.siNames = siNames;
		return this;
	}


	/**
	* This function allows setting a value for siNotations
	* @param siNotations desired value to be set
	* @return Builder object with new value for siNotations
	*/
	final public RC01Builder siNotations(List<String> siNotations) {
		this.defaultRC01.siNotations = siNotations;
		return this;
	}


	/**
	* This function allows setting a value for registrationAuthorityIds
	* @param registrationAuthorityIds desired value to be set
	* @return Builder object with new value for registrationAuthorityIds
	*/
	final public RC01Builder registrationAuthorityIds(List<String> registrationAuthorityIds) {
		this.defaultRC01.registrationAuthorityIds = registrationAuthorityIds;
		return this;
	}


	/**
	* This function allows setting a value for suppliers
	* @param suppliers desired value to be set
	* @return Builder object with new value for suppliers
	*/
	final public RC01Builder suppliers(List<String> suppliers) {
		this.defaultRC01.suppliers = suppliers;
		return this;
	}


	/**
	* This function allows setting a value for unitNames
	* @param unitNames desired value to be set
	* @return Builder object with new value for unitNames
	*/
	final public RC01Builder unitNames(List<String> unitNames) {
		this.defaultRC01.unitNames = unitNames;
		return this;
	}


	/**
	* This function allows setting a value for unitSymbols
	* @param unitSymbols desired value to be set
	* @return Builder object with new value for unitSymbols
	*/
	final public RC01Builder unitSymbols(List<String> unitSymbols) {
		this.defaultRC01.unitSymbols = unitSymbols;
		return this;
	}

	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public RC01 build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultRC01);
		return defaultRC01;
	}
}
