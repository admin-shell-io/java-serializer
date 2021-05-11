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

public class PermissionsPerObjectBuilder {

	private DefaultPermissionsPerObject defaultPermissionsPerObject;

	public PermissionsPerObjectBuilder() {
		defaultPermissionsPerObject = new DefaultPermissionsPerObject();
	}

	public PermissionsPerObjectBuilder(URI id) {
		this();
		defaultPermissionsPerObject.id = id;
	}

	/**
	* This function allows setting a value for object
	* @param object desired value to be set
	* @return Builder object with new value for object
	*/
	final public PermissionsPerObjectBuilder object(Referable object) {
		this.defaultPermissionsPerObject.object = object;
		return this;
	}


	/**
	* This function allows setting a value for permissions
	* @param permissions desired value to be set
	* @return Builder object with new value for permissions
	*/
	final public PermissionsPerObjectBuilder permissions(List<Permission> permissions) {
		this.defaultPermissionsPerObject.permissions = permissions;
		return this;
	}


	/**
	* This function allows setting a value for targetObjectAttributes
	* @param targetObjectAttributes desired value to be set
	* @return Builder object with new value for targetObjectAttributes
	*/
	final public PermissionsPerObjectBuilder targetObjectAttributes(ObjectAttributes targetObjectAttributes) {
		this.defaultPermissionsPerObject.targetObjectAttributes = targetObjectAttributes;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public PermissionsPerObject build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultPermissionsPerObject);
		return defaultPermissionsPerObject;
	}
}
