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

/** 
* "Data Specification Content"
* "DataSpecificationContent contains the additional attributes to be added to the element instance that references the data specification template and meta information about the template itself."@en 
*/

public class DefaultDataSpecificationContent implements DataSpecificationContent {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Data Specification Content", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("DataSpecificationContent contains the additional attributes to be added to the element instance that references the data specification template and meta information about the template itself.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology


	// no manual construction
	protected DefaultDataSpecificationContent() {
		id = VocabUtil.getInstance().createRandomUrl("dataSpecificationContent");
	}

	final public URI getId() {
		return id;
	}

	public List<TypedLiteral> getLabels() {
		return this.labels;
	}

	public List<TypedLiteral> getComments() {
		return this.comments;
	}

	@Override
	public int hashCode() {
		return Objects.hash(new Object[]{super.hashCode()});
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (this.getClass() != obj.getClass()) {
			return false;
		}
		return true;
	}

}
