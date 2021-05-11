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
* "HasExtensions"
* "Element that can be extended by proprietary extensions."@en 
*/

public class DefaultHasExtensions implements HasExtensions {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("HasExtensions", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("Element that can be extended by proprietary extensions.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "has extension"
	* "An extension of the element."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/HasExtensions/extension")
	protected List<Extension> extensions;


	// no manual construction
	protected DefaultHasExtensions() {
		id = VocabUtil.getInstance().createRandomUrl("hasExtensions");
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
		return Objects.hash(new Object[]{this.extensions});
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
			DefaultHasExtensions other = (DefaultHasExtensions) obj;
			return Objects.equals(this.extensions, other.extensions);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/HasExtensions/extension")
	final public List<Extension> getExtensions() {
		return extensions;
	}
	
	final public void setExtensions (List<Extension> extensions) {
		this.extensions = extensions;
	}
}
