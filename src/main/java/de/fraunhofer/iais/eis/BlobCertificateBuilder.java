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

public class BlobCertificateBuilder {

	private DefaultBlobCertificate defaultBlobCertificate;

	public BlobCertificateBuilder() {
		defaultBlobCertificate = new DefaultBlobCertificate();
	}

	public BlobCertificateBuilder(URI id) {
		this();
		defaultBlobCertificate.id = id;
	}

	/**
	* This function allows setting a value for blobCertificate
	* @param blobCertificate desired value to be set
	* @return Builder object with new value for blobCertificate
	*/
	final public BlobCertificateBuilder blobCertificate(Blob blobCertificate) {
		this.defaultBlobCertificate.blobCertificate = blobCertificate;
		return this;
	}


	/**
	* This function allows setting a value for containedExtensions
	* @param containedExtensions desired value to be set
	* @return Builder object with new value for containedExtensions
	*/
	final public BlobCertificateBuilder containedExtensions(List<Reference> containedExtensions) {
		this.defaultBlobCertificate.containedExtensions = containedExtensions;
		return this;
	}


	/**
	* This function allows setting a value for lastCertificate
	* @param lastCertificate desired value to be set
	* @return Builder object with new value for lastCertificate
	*/
	final public BlobCertificateBuilder lastCertificate(boolean lastCertificate) {
		this.defaultBlobCertificate.lastCertificate = lastCertificate;
		return this;
	}


	/**
	* This function allows setting a value for policyAdministrationPoint
	* @param policyAdministrationPoint desired value to be set
	* @return Builder object with new value for policyAdministrationPoint
	*/
	final public BlobCertificateBuilder policyAdministrationPoint(PolicyAdministrationPoint policyAdministrationPoint) {
		this.defaultBlobCertificate.policyAdministrationPoint = policyAdministrationPoint;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public BlobCertificate build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultBlobCertificate);
		return defaultBlobCertificate;
	}
}
