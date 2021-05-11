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
* "Identifier"
* "Used to uniquely identify an entity by using an identifier."@en 
*/

public class DefaultIdentifier implements Identifier {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Identifier", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("Used to uniquely identify an entity by using an identifier.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "has idType"
	* "Type of the Identifier, e.g. IRI, IRDI etc. The supported Identifier types are defined in the enumeration \'IdentifierType\'."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Identifier/idType")
	protected IdentifierType idType;


	/**
	* "has identification"
	* "A globally unique identifier which might not be a URI. Its type is defined in idType."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Identifier/identifier")
	protected String identifier;


	// no manual construction
	protected DefaultIdentifier() {
		id = VocabUtil.getInstance().createRandomUrl("identifier");
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
		return Objects.hash(new Object[]{this.identifier,
			this.idType});
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
			DefaultIdentifier other = (DefaultIdentifier) obj;
			return Objects.equals(this.identifier, other.identifier) &&
				Objects.equals(this.idType, other.idType);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/Identifier/identifier")
	final public String getIdentifier() {
		return identifier;
	}
	
	final public void setIdentifier (String identifier) {
		this.identifier = identifier;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/Identifier/idType")
	final public IdentifierType getIdType() {
		return idType;
	}
	
	final public void setIdType (IdentifierType idType) {
		this.idType = idType;
	}
}
