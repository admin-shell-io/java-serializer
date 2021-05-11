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

public class AssetAdministrationShellEnvironmentBuilder {

	private DefaultAssetAdministrationShellEnvironment defaultAssetAdministrationShellEnvironment;

	public AssetAdministrationShellEnvironmentBuilder() {
		defaultAssetAdministrationShellEnvironment = new DefaultAssetAdministrationShellEnvironment();
	}

	public AssetAdministrationShellEnvironmentBuilder(URI id) {
		this();
		defaultAssetAdministrationShellEnvironment.id = id;
	}

	/**
	* This function allows setting a value for assetAdministrationShells
	* @param assetAdministrationShells desired value to be set
	* @return Builder object with new value for assetAdministrationShells
	*/
	final public AssetAdministrationShellEnvironmentBuilder assetAdministrationShells(List<AssetAdministrationShell> assetAdministrationShells) {
		this.defaultAssetAdministrationShellEnvironment.assetAdministrationShells = assetAdministrationShells;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public AssetAdministrationShellEnvironment build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultAssetAdministrationShellEnvironment);
		return defaultAssetAdministrationShellEnvironment;
	}
}
