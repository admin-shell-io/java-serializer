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
* "Key Elements"
* "Enumeration of different key value types within a key. Contains KeyElements, ReferableElements, and IdentifiableElements."@en 
*/
public enum KeyElements {

	/** 
	* "Asset"
	*/
	ASSET("https://admin-shell.io/aas/3/0/RC01/IdentifiableElements/ASSET", Arrays.asList(new TypedLiteral("Asset", "")), Collections.emptyList()),

	/** 
	* "Asset Administration Shell"
	*/
	ASSET_ADMINISTRATION_SHELL("https://admin-shell.io/aas/3/0/RC01/IdentifiableElements/ASSET_ADMINISTRATION_SHELL", Arrays.asList(new TypedLiteral("Asset Administration Shell", "")), Collections.emptyList()),

	/** 
	* "Concept Description"
	*/
	CONCEPT_DESCRIPTION("https://admin-shell.io/aas/3/0/RC01/IdentifiableElements/CONCEPT_DESCRIPTION", Arrays.asList(new TypedLiteral("Concept Description", "")), Collections.emptyList()),

	/** 
	* "Submodel"
	*/
	SUBMODEL("https://admin-shell.io/aas/3/0/RC01/IdentifiableElements/SUBMODEL", Arrays.asList(new TypedLiteral("Submodel", "")), Collections.emptyList()),

	/** 
	* "Fragement Reference"
	*/
	FRAGMENT_REFERENCE("https://admin-shell.io/aas/3/0/RC01/KeyElements/FRAGMENT_REFERENCE", Arrays.asList(new TypedLiteral("Fragement Reference", "")), Collections.emptyList()),

	/** 
	* "Gobal Reference"
	* "reference to an element not belonging to an asset administration shell"@en
	*/
	GLOBAL_REFERENCE("https://admin-shell.io/aas/3/0/RC01/KeyElements/GLOBAL_REFERENCE", Arrays.asList(new TypedLiteral("Gobal Reference", "")), Arrays.asList(new TypedLiteral("reference to an element not belonging to an asset administration shell", "en"))),

	/** 
	* "Access Permission Rule"
	*/
	ACCESS_PERMISSION_RULE("https://admin-shell.io/aas/3/0/RC01/ReferableElements/ACCESS_PERMISSION_RULE", Arrays.asList(new TypedLiteral("Access Permission Rule", "")), Collections.emptyList()),

	/** 
	* "Annotated relationship element"
	*/
	ANNOTATED_RELATIONSHIP_ELEMENT("https://admin-shell.io/aas/3/0/RC01/ReferableElements/ANNOTATED_RELATIONSHIP_ELEMENT", Arrays.asList(new TypedLiteral("Annotated relationship element", "")), Collections.emptyList()),

	/** 
	* "Basic Event"
	*/
	BASIC_EVENT("https://admin-shell.io/aas/3/0/RC01/ReferableElements/BASIC_EVENT", Arrays.asList(new TypedLiteral("Basic Event", "")), Collections.emptyList()),

	/** 
	* "Blob"
	*/
	BLOB("https://admin-shell.io/aas/3/0/RC01/ReferableElements/BLOB", Arrays.asList(new TypedLiteral("Blob", "")), Collections.emptyList()),

	/** 
	* "Capability"
	*/
	CAPABILITY("https://admin-shell.io/aas/3/0/RC01/ReferableElements/CAPABILITY", Arrays.asList(new TypedLiteral("Capability", "")), Collections.emptyList()),

	/** 
	* "Concept Dictionary"
	*/
	CONCEPT_DICTIONARY("https://admin-shell.io/aas/3/0/RC01/ReferableElements/CONCEPT_DICTIONARY", Arrays.asList(new TypedLiteral("Concept Dictionary", "")), Collections.emptyList()),

	/** 
	* "Data Element"
	* "Data Element is abstract, i.e. if a key uses \'DataElement\' the reference may be a Property, a File etc."@en
	*/
	DATA_ELEMENT("https://admin-shell.io/aas/3/0/RC01/ReferableElements/DATA_ELEMENT", Arrays.asList(new TypedLiteral("Data Element", "")), Collections.emptyList()),

	/** 
	* "Entity"
	*/
	ENTITY("https://admin-shell.io/aas/3/0/RC01/ReferableElements/ENTITY", Arrays.asList(new TypedLiteral("Entity", "")), Collections.emptyList()),

	/** 
	* "Event"
	* "Event is abstract"@en
	*/
	EVENT("https://admin-shell.io/aas/3/0/RC01/ReferableElements/EVENT", Arrays.asList(new TypedLiteral("Event", "")), Collections.emptyList()),

	/** 
	* "Multi-language Property"
	* "Property with a value that can be provided in multiple languages."@en
	*/
	MULTI_LANGUAGE_PROPERTY("https://admin-shell.io/aas/3/0/RC01/ReferableElements/MULTI_LANGUAGE_PROPERTY", Arrays.asList(new TypedLiteral("Multi-language Property", "")), Arrays.asList(new TypedLiteral("Property with a value that can be provided in multiple languages.", "en"))),

	/** 
	* "Operation"
	*/
	OPERATION("https://admin-shell.io/aas/3/0/RC01/ReferableElements/OPERATION", Arrays.asList(new TypedLiteral("Operation", "")), Collections.emptyList()),

	/** 
	* "Property"
	*/
	PROPERTY("https://admin-shell.io/aas/3/0/RC01/ReferableElements/PROPERTY", Arrays.asList(new TypedLiteral("Property", "")), Collections.emptyList()),

	/** 
	* "Range"
	*/
	RANGE("https://admin-shell.io/aas/3/0/RC01/ReferableElements/RANGE", Arrays.asList(new TypedLiteral("Range", "")), Collections.emptyList()),

	/** 
	* "Reference Element"
	*/
	REFERENCE_ELEMENT("https://admin-shell.io/aas/3/0/RC01/ReferableElements/REFERENCE_ELEMENT", Arrays.asList(new TypedLiteral("Reference Element", "")), Collections.emptyList()),

	/** 
	* "Relationship Element"
	*/
	RELATIONSHIPT_ELEMENT("https://admin-shell.io/aas/3/0/RC01/ReferableElements/RELATIONSHIPT_ELEMENT", Arrays.asList(new TypedLiteral("Relationship Element", "")), Collections.emptyList()),

	/** 
	* "Submodel Element"
	* "Submodel Element is abstract, i.e. if a key uses \'SubmodelElement\' the reference may be a Property, a SubmodelElementCollection, an Operation etc."@en
	*/
	SUBMODEL_ELEMENT("https://admin-shell.io/aas/3/0/RC01/ReferableElements/SUBMODEL_ELEMENT", Arrays.asList(new TypedLiteral("Submodel Element", "")), Collections.emptyList()),

	/** 
	* "Submodel Element Collection"
	* "Collection of Submodel Elements"@en
	*/
	SUBMODEL_ELEMENT_COLLECTION("https://admin-shell.io/aas/3/0/RC01/ReferableElements/SUBMODEL_ELEMENT_COLLECTION", Arrays.asList(new TypedLiteral("Submodel Element Collection", "")), Arrays.asList(new TypedLiteral("Collection of Submodel Elements", "en"))),

	/** 
	* "View"
	*/
	VIEW("https://admin-shell.io/aas/3/0/RC01/ReferableElements/VIEW", Arrays.asList(new TypedLiteral("View", "")), Collections.emptyList());

	private static final Map<String,KeyElements> uriInstanceMapping;
	static {
		uriInstanceMapping = new HashMap<>();
		uriInstanceMapping.putAll(Stream.of(values()).collect(Collectors.toMap(instance -> instance.toString(), instance -> instance)));
		uriInstanceMapping.putAll(Stream.of(values()).collect(Collectors.toMap(instance -> instance.getSerializedId().toString(), instance -> instance)));
	}

	private URI id;
	private List<TypedLiteral> labels;
	private List<TypedLiteral> comments;


	KeyElements(String id, List<TypedLiteral> labels, List<TypedLiteral> comments) {
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
