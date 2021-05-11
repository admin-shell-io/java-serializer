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

public class EntityBuilder {

	private DefaultEntity defaultEntity;

	public EntityBuilder() {
		defaultEntity = new DefaultEntity();
	}

	public EntityBuilder(URI id) {
		this();
		defaultEntity.id = id;
	}

	/**
	* This function allows setting a value for globalAssetId
	* @param globalAssetId desired value to be set
	* @return Builder object with new value for globalAssetId
	*/
	final public EntityBuilder globalAssetId(Reference globalAssetId) {
		this.defaultEntity.globalAssetId = globalAssetId;
		return this;
	}


	/**
	* This function allows setting a value for externalAssetId
	* @param externalAssetId desired value to be set
	* @return Builder object with new value for externalAssetId
	*/
	final public EntityBuilder externalAssetId(IdentifierKeyValuePair externalAssetId) {
		this.defaultEntity.externalAssetId = externalAssetId;
		return this;
	}


	/**
	* This function allows setting a value for entityType
	* @param entityType desired value to be set
	* @return Builder object with new value for entityType
	*/
	final public EntityBuilder entityType(EntityType entityType) {
		this.defaultEntity.entityType = entityType;
		return this;
	}


	/**
	* This function allows setting a value for statements
	* @param statements desired value to be set
	* @return Builder object with new value for statements
	*/
	final public EntityBuilder statements(List<SubmodelElement> statements) {
		this.defaultEntity.statements = statements;
		return this;
	}



	/**
	* This function allows setting a value for referableCategories
	* @param referableCategories desired value to be set
	* @return Builder object with new value for referableCategories
	*/
	final public EntityBuilder referableCategories(List<String> referableCategories) {
		this.defaultEntity.referableCategories = referableCategories;
		return this;
	}


	/**
	* This function allows setting a value for descriptions
	* @param descriptions desired value to be set
	* @return Builder object with new value for descriptions
	*/
	final public EntityBuilder descriptions(List<TypedLiteral> descriptions) {
		this.defaultEntity.descriptions = descriptions;
		return this;
	}


	/**
	* This function allows setting a value for displayName
	* @param displayName desired value to be set
	* @return Builder object with new value for displayName
	*/
	final public EntityBuilder displayName(TypedLiteral displayName) {
		this.defaultEntity.displayName = displayName;
		return this;
	}


	/**
	* This function allows setting a value for idShort
	* @param idShort desired value to be set
	* @return Builder object with new value for idShort
	*/
	final public EntityBuilder idShort(String idShort) {
		this.defaultEntity.idShort = idShort;
		return this;
	}


	/**
	* This function allows setting a value for qualifiers
	* @param qualifiers desired value to be set
	* @return Builder object with new value for qualifiers
	*/
	final public EntityBuilder qualifiers(List<Constraint> qualifiers) {
		this.defaultEntity.qualifiers = qualifiers;
		return this;
	}


	/**
	* This function allows setting a value for dataSpecifications
	* @param dataSpecifications desired value to be set
	* @return Builder object with new value for dataSpecifications
	*/
	final public EntityBuilder dataSpecifications(List<Reference> dataSpecifications) {
		this.defaultEntity.dataSpecifications = dataSpecifications;
		return this;
	}


	/**
	* This function allows setting a value for kind
	* @param kind desired value to be set
	* @return Builder object with new value for kind
	*/
	final public EntityBuilder kind(ModelingKind kind) {
		this.defaultEntity.kind = kind;
		return this;
	}


	/**
	* This function allows setting a value for semanticId
	* @param semanticId desired value to be set
	* @return Builder object with new value for semanticId
	*/
	final public EntityBuilder semanticId(Reference semanticId) {
		this.defaultEntity.semanticId = semanticId;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public Entity build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultEntity);
		return defaultEntity;
	}
}
