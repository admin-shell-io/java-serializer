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
* "Kind"
* "Enumeration for denoting whether an element is a type or an instance."@en 
*/
public enum ModelingKind {

	/** 
	* "Instance"
	* "Concrete, clearly identifiable component of a certain template."@en
	* "It becomes an individual entity of a template, for example a device model, by defining specific property values."@en
	* "In an object oriented view, an instance denotes an object (of a template) (class)."@en
	*/
	INSTANCE("https://admin-shell.io/aas/3/0/RC01/ModelingKind/INSTANCE", Arrays.asList(new TypedLiteral("Instance", "")), Arrays.asList(new TypedLiteral("Concrete, clearly identifiable component of a certain template.", "en"))),

	/** 
	* "Template"
	* "Software element which specifies the common attributes shared by all instances of the template."@en
	*/
	TEMPLATE("https://admin-shell.io/aas/3/0/RC01/ModelingKind/TEMPLATE", Arrays.asList(new TypedLiteral("Template", "")), Arrays.asList(new TypedLiteral("Software element which specifies the common attributes shared by all instances of the template.", "en")));

	private static final Map<String,ModelingKind> uriInstanceMapping;
	static {
		uriInstanceMapping = new HashMap<>();
		uriInstanceMapping.putAll(Stream.of(values()).collect(Collectors.toMap(instance -> instance.toString(), instance -> instance)));
		uriInstanceMapping.putAll(Stream.of(values()).collect(Collectors.toMap(instance -> instance.getSerializedId().toString(), instance -> instance)));
	}

	private URI id;
	private List<TypedLiteral> labels;
	private List<TypedLiteral> comments;


	ModelingKind(String id, List<TypedLiteral> labels, List<TypedLiteral> comments) {
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
