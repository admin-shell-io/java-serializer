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

public class PolicyInformationPointsBuilder {

	private DefaultPolicyInformationPoints defaultPolicyInformationPoints;

	public PolicyInformationPointsBuilder() {
		defaultPolicyInformationPoints = new DefaultPolicyInformationPoints();
	}

	public PolicyInformationPointsBuilder(URI id) {
		this();
		defaultPolicyInformationPoints.id = id;
	}

	/**
	* This function allows setting a value for externalInformationPoints
	* @param externalInformationPoints desired value to be set
	* @return Builder object with new value for externalInformationPoints
	*/
	final public PolicyInformationPointsBuilder externalInformationPoints(boolean externalInformationPoints) {
		this.defaultPolicyInformationPoints.externalInformationPoints = externalInformationPoints;
		return this;
	}


	/**
	* This function allows setting a value for internalInformationPoints
	* @param internalInformationPoints desired value to be set
	* @return Builder object with new value for internalInformationPoints
	*/
	final public PolicyInformationPointsBuilder internalInformationPoints(List<Submodel> internalInformationPoints) {
		this.defaultPolicyInformationPoints.internalInformationPoints = internalInformationPoints;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public PolicyInformationPoints build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultPolicyInformationPoints);
		return defaultPolicyInformationPoints;
	}
}
