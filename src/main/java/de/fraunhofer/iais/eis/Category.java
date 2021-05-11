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
import com.fasterxml.jackson.databind.JsonNode;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** 
* "Category"
* "A enumeration for data elements except for files and blobs."@en 
*/
public enum Category {

	/** 
	* "Constant"
	* "A constant property is a property with a value that does not change over time. In eCl@ss this kind of category has the category \'Coded Value\'."@en
	*/
	CONSTANT("https://admin-shell.io/aas/3/0/RC01/Category/CONSTANT", Arrays.asList(new TypedLiteral("Constant", "")), Arrays.asList(new TypedLiteral("A constant property is a property with a value that does not change over time. In eCl@ss this kind of category has the category 'Coded Value'.", "en"))),

	/** 
	* "Parameter"
	* "A parameter property is a property that is once set and then typically does not change over time. This is for example the case for configuration parameters."@en
	*/
	PARAMETER("https://admin-shell.io/aas/3/0/RC01/Category/PARAMETER", Arrays.asList(new TypedLiteral("Parameter", "")), Arrays.asList(new TypedLiteral("A parameter property is a property that is once set and then typically does not change over time. This is for example the case for configuration parameters.", "en"))),

	/** 
	* "Variable"
	* "A variable property is a property that is calculated during runtime, i.e. its value is a runtime value."@en
	*/
	VARIABLE("https://admin-shell.io/aas/3/0/RC01/Category/VARIABLE", Arrays.asList(new TypedLiteral("Variable", "")), Arrays.asList(new TypedLiteral("A variable property is a property that is calculated during runtime, i.e. its value is a runtime value.", "en")));

	private static final Map<String,Category> uriInstanceMapping;
	static {
		uriInstanceMapping = new HashMap<>();
		uriInstanceMapping.putAll(Stream.of(values()).collect(Collectors.toMap(instance -> instance.toString(), instance -> instance)));
		uriInstanceMapping.putAll(Stream.of(values()).collect(Collectors.toMap(instance -> instance.getSerializedId().toString(), instance -> instance)));
	}

	private URI id;
	private List<TypedLiteral> labels;
	private List<TypedLiteral> comments;


	Category(String id, List<TypedLiteral> labels, List<TypedLiteral> comments) {
		try {
			this.id = new URI(id);
			this.labels = labels;
			this.comments = comments;
		}
		catch (java.net.URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}
	/**
	* This function retrieves the ID of the current object (can be set via the constructor of the builder class)
	* @return ID of current object as URI
	*/

	@JsonIgnore
	final public URI getId() {
		return id;
	}

	/**
	* This function retrieves a human readable labels about the current class, as defined in the ontology.
	* This label could, for example, be used as a field heading in a user interface
	* @return Human readable labels
	*/
	@JsonIgnore
	final public List<TypedLiteral> getLabels() {
		return labels;
	}

	/**
	* This function retrieves a human readable explanatory comments about the current class, as defined in the ontology.
	* This comment could, for example, be used as a tooltip in a user interface
	* @return Human readable explanatory comments
	*/
	@JsonIgnore
	final public List<TypedLiteral> getComments() {
		return comments;
	}


	final public URI getSerializedId() {
		return id;
	}
	

	@Override
	public String toString() {
		return id.toString();
	}

}
