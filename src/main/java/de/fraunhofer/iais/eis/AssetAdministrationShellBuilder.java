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

public class AssetAdministrationShellBuilder {

	private DefaultAssetAdministrationShell defaultAssetAdministrationShell;

	public AssetAdministrationShellBuilder() {
		defaultAssetAdministrationShell = new DefaultAssetAdministrationShell();
	}

	public AssetAdministrationShellBuilder(URI id) {
		this();
		defaultAssetAdministrationShell.id = id;
	}

	/**
	* This function allows setting a value for assetInformation
	* @param assetInformation desired value to be set
	* @return Builder object with new value for assetInformation
	*/
	final public AssetAdministrationShellBuilder assetInformation(AssetInformation assetInformation) {
		this.defaultAssetAdministrationShell.assetInformation = assetInformation;
		return this;
	}


	/**
	* This function allows setting a value for derivedFrom
	* @param derivedFrom desired value to be set
	* @return Builder object with new value for derivedFrom
	*/
	final public AssetAdministrationShellBuilder derivedFrom(Reference derivedFrom) {
		this.defaultAssetAdministrationShell.derivedFrom = derivedFrom;
		return this;
	}


	/**
	* This function allows setting a value for security
	* @param security desired value to be set
	* @return Builder object with new value for security
	*/
	final public AssetAdministrationShellBuilder security(Security security) {
		this.defaultAssetAdministrationShell.security = security;
		return this;
	}


	/**
	* This function allows setting a value for submodels
	* @param submodels desired value to be set
	* @return Builder object with new value for submodels
	*/
	final public AssetAdministrationShellBuilder submodels(List<Reference> submodels) {
		this.defaultAssetAdministrationShell.submodels = submodels;
		return this;
	}


	/**
	* This function allows setting a value for views
	* @param views desired value to be set
	* @return Builder object with new value for views
	*/
	final public AssetAdministrationShellBuilder views(List<View> views) {
		this.defaultAssetAdministrationShell.views = views;
		return this;
	}


	/**
	* This function allows setting a value for dataSpecifications
	* @param dataSpecifications desired value to be set
	* @return Builder object with new value for dataSpecifications
	*/
	final public AssetAdministrationShellBuilder dataSpecifications(List<Reference> dataSpecifications) {
		this.defaultAssetAdministrationShell.dataSpecifications = dataSpecifications;
		return this;
	}


	/**
	* This function allows setting a value for administration
	* @param administration desired value to be set
	* @return Builder object with new value for administration
	*/
	final public AssetAdministrationShellBuilder administration(AdministrativeInformation administration) {
		this.defaultAssetAdministrationShell.administration = administration;
		return this;
	}


	/**
	* This function allows setting a value for identification
	* @param identification desired value to be set
	* @return Builder object with new value for identification
	*/
	final public AssetAdministrationShellBuilder identification(Identifier identification) {
		this.defaultAssetAdministrationShell.identification = identification;
		return this;
	}


	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public AssetAdministrationShellBuilder referableCategories(List<String> referableCategories) {
		this.defaultAssetAdministrationShell.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public AssetAdministrationShellBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultAssetAdministrationShell.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public AssetAdministrationShellBuilder displayName(TypedLiteral displayName) {
		this.defaultAssetAdministrationShell.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public AssetAdministrationShellBuilder idShort(String idShort) {
		this.defaultAssetAdministrationShell.idShort = idShort;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public AssetAdministrationShell build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultAssetAdministrationShell);
		return defaultAssetAdministrationShell;
	}
}
