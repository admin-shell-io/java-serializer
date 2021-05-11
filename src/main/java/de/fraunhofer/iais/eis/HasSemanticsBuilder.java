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

public class HasSemanticsBuilder {

	private DefaultHasSemantics defaultHasSemantics;

	public HasSemanticsBuilder() {
		defaultHasSemantics = new DefaultHasSemantics();
	}

	public HasSemanticsBuilder(URI id) {
		this();
		defaultHasSemantics.id = id;
	}

	/**
	* This function allows setting a value for semanticId
	* @param semanticId desired value to be set
	* @return Builder object with new value for semanticId
	*/
	final public HasSemanticsBuilder semanticId(Reference semanticId) {
		this.defaultHasSemantics.semanticId = semanticId;
		return this;
	}
	/**
	* This function takes the values that were set previously via the other functions of this class and turns them into a Java bean.
	* @return Bean with specified values
	* @throws ConstraintViolationException This exception is thrown, if a validator is used and a violation is found.
	*/

	final public HasSemantics build() throws ConstraintViolationException {
		VocabUtil.getInstance().validate(defaultHasSemantics);
		return defaultHasSemantics;
	}
}
