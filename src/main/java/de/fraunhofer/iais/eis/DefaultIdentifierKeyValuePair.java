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
* "identifier key value pair"
* "An IdentifierKeyValuePair describes a generic identifier as key-value pair."@en 
*/

public class DefaultIdentifierKeyValuePair implements IdentifierKeyValuePair {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("identifier key value pair", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("An IdentifierKeyValuePair describes a generic identifier as key-value pair.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "has semantic ID"
	* "Points to the Expression Semantic of the Submodels"@en
	* "The semantic id might refer to an external information source, which explains the formulation of the submodel (for example an PDF if a standard)."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/HasSemantics/semanticId")
	protected Reference semanticId;


	/**
	* "has IdentifierKeyValuePair.externalSubjectId"
	* "The (external) subject the key belongs to or has meaning to."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/IdentifierKeyValuePair/externalSubjectId")
	protected Reference externalSubjectId;


	/**
	* "has IdentifierKeyValuePair.key"
	* "Key of the identifier."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/IdentifierKeyValuePair/key")
	protected String key;


	/**
	* "has IdentifierKeyValuePair.value"
	* "The value of the identifier with the corresponding key."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/IdentifierKeyValuePair/value")
	protected String value;


	// no manual construction
	protected DefaultIdentifierKeyValuePair() {
		id = VocabUtil.getInstance().createRandomUrl("identifierKeyValuePair");
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
		return Objects.hash(new Object[]{this.key,
			this.value,
			this.externalSubjectId,
			this.semanticId});
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (this.getClass() != obj.getClass()) {
			return false;
		} else {
			DefaultIdentifierKeyValuePair other = (DefaultIdentifierKeyValuePair) obj;
			return Objects.equals(this.key, other.key) &&
				Objects.equals(this.value, other.value) &&
				Objects.equals(this.externalSubjectId, other.externalSubjectId) &&
				Objects.equals(this.semanticId, other.semanticId);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/IdentifierKeyValuePair/key")
	final public String getKey() {
		return key;
	}
	
	final public void setKey (String key) {
		this.key = key;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/IdentifierKeyValuePair/value")
	final public String getValue() {
		return value;
	}
	
	final public void setValue (String value) {
		this.value = value;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/IdentifierKeyValuePair/externalSubjectId")
	final public Reference getExternalSubjectId() {
		return externalSubjectId;
	}
	
	final public void setExternalSubjectId (Reference externalSubjectId) {
		this.externalSubjectId = externalSubjectId;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/HasSemantics/semanticId")
	final public Reference getSemanticId() {
		return semanticId;
	}
	
	final public void setSemanticId (Reference semanticId) {
		this.semanticId = semanticId;
	}
}
