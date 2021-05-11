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

public class AssetInformationBuilder {

	private DefaultAssetInformation defaultAssetInformation;

	public AssetInformationBuilder() {
		defaultAssetInformation = new DefaultAssetInformation();
	}

	public AssetInformationBuilder(URI id) {
		this();
		defaultAssetInformation.id = id;
	}

	/**
	* This function allows setting a value for assetKind
	* @param assetKind desired value to be set
	* @return Builder object with new value for assetKind
	*/
	final public AssetInformationBuilder assetKind(AssetKind assetKind) {
		this.defaultAssetInformation.assetKind = assetKind;
		return this;
	}


	/**
	* This function allows setting a value for globalAssetId
	* @param globalAssetId desired value to be set
	* @return Builder object with new value for globalAssetId
	*/
	final public AssetInformationBuilder globalAssetId(Reference globalAssetId) {
		this.defaultAssetInformation.globalAssetId = globalAssetId;
		return this;
	}


	/**
	* This function allows setting a value for specificAssetIds
	* @param specificAssetIds desired value to be set
	* @return Builder object with new value for specificAssetIds
	*/
	final public AssetInformationBuilder specificAssetIds(List<IdentifierKeyValuePair> specificAssetIds) {
		this.defaultAssetInformation.specificAssetIds = specificAssetIds;
		return this;
	}


	/**
	* This function allows setting a value for billOfMaterials
	* @param billOfMaterials desired value to be set
	* @return Builder object with new value for billOfMaterials
	*/
	final public AssetInformationBuilder billOfMaterials(List<Reference> billOfMaterials) {
		this.defaultAssetInformation.billOfMaterials = billOfMaterials;
		return this;
	}


	/**
	* This function allows setting a value for defaultThumbnail
	* @param defaultThumbnail desired value to be set
	* @return Builder object with new value for defaultThumbnail
	*/
	final public AssetInformationBuilder defaultThumbnail(File defaultThumbnail) {
		this.defaultAssetInformation.defaultThumbnail = defaultThumbnail;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public AssetInformation build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultAssetInformation);
		return defaultAssetInformation;
	}
}
