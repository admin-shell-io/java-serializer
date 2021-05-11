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

public class AdministrativeInformationBuilder {

	private DefaultAdministrativeInformation defaultAdministrativeInformation;

	public AdministrativeInformationBuilder() {
		defaultAdministrativeInformation = new DefaultAdministrativeInformation();
	}

	public AdministrativeInformationBuilder(URI id) {
		this();
		defaultAdministrativeInformation.id = id;
	}

	/**
	* This function allows setting a value for version
	* @param version desired value to be set
	* @return Builder object with new value for version
	*/
	final public AdministrativeInformationBuilder version(String version) {
		this.defaultAdministrativeInformation.version = version;
		return this;
	}


	/**
	* This function allows setting a value for revision
	* @param revision desired value to be set
	* @return Builder object with new value for revision
	*/
	final public AdministrativeInformationBuilder revision(String revision) {
		this.defaultAdministrativeInformation.revision = revision;
		return this;
	}


	/**
	* This function allows setting a value for dataSpecifications
	* @param dataSpecifications desired value to be set
	* @return Builder object with new value for dataSpecifications
	*/
	final public AdministrativeInformationBuilder dataSpecifications(List<Reference> dataSpecifications) {
		this.defaultAdministrativeInformation.dataSpecifications = dataSpecifications;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public AdministrativeInformation build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultAdministrativeInformation);
		return defaultAdministrativeInformation;
	}
}
