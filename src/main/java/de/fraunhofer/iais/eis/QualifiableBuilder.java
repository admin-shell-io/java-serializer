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

public class QualifiableBuilder {

	private DefaultQualifiable defaultQualifiable;

	public QualifiableBuilder() {
		defaultQualifiable = new DefaultQualifiable();
	}

	public QualifiableBuilder(URI id) {
		this();
		defaultQualifiable.id = id;
	}

	/**
	* This function allows setting a value for qualifiers
	* @param qualifiers desired value to be set
	* @return Builder object with new value for qualifiers
	*/
	final public QualifiableBuilder qualifiers(List<Constraint> qualifiers) {
		this.defaultQualifiable.qualifiers = qualifiers;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public Qualifiable build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultQualifiable);
		return defaultQualifiable;
	}
}
