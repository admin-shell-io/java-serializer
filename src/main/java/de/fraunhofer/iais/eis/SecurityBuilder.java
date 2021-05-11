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

public class SecurityBuilder {

	private DefaultSecurity defaultSecurity;

	public SecurityBuilder() {
		defaultSecurity = new DefaultSecurity();
	}

	public SecurityBuilder(URI id) {
		this();
		defaultSecurity.id = id;
	}

	/**
	* This function allows setting a value for accessControlPolicyPoints
	* @param accessControlPolicyPoints desired value to be set
	* @return Builder object with new value for accessControlPolicyPoints
	*/
	final public SecurityBuilder accessControlPolicyPoints(AccessControlPolicyPoints accessControlPolicyPoints) {
		this.defaultSecurity.accessControlPolicyPoints = accessControlPolicyPoints;
		return this;
	}


	/**
	* This function allows setting a value for certificates
	* @param certificates desired value to be set
	* @return Builder object with new value for certificates
	*/
	final public SecurityBuilder certificates(List<Certificate> certificates) {
		this.defaultSecurity.certificates = certificates;
		return this;
	}


	/**
	* This function allows setting a value for requiredCertificateExtensions
	* @param requiredCertificateExtensions desired value to be set
	* @return Builder object with new value for requiredCertificateExtensions
	*/
	final public SecurityBuilder requiredCertificateExtensions(List<Reference> requiredCertificateExtensions) {
		this.defaultSecurity.requiredCertificateExtensions = requiredCertificateExtensions;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public Security build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultSecurity);
		return defaultSecurity;
	}
}
