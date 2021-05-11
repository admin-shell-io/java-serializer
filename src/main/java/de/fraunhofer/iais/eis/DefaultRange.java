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
* "Range"
* "An element that is referable by its idShort. This id is not globally unique. This id is unique within the name space of the element."@en
* "Constraint AASd-053: The semanticId of a Range submodel element shall only reference a ConceptDescription with the category PROPERTY."@en
* "Constraint AASd-068: If the semanticId of a  Range references a ConceptDescription then DataSpecificationIEC61360/dataType shall be a numerical one, i.e. REAL_* or RATIONAL_*."@en
* "Constraint AASd-069: If the semanticId of a  Range references a ConceptDescription then DataSpecificationIEC61360/levelType shall be identical to the set {Min,Max}."@en 
*/

public class DefaultRange implements Range {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Range", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("An element that is referable by its idShort. This id is not globally unique. This id is unique within the name space of the element.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "has Data Specification"
	* "Global reference to the data specification template used by the element."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/HasDataSpecification/dataSpecification")
	protected List<Reference> dataSpecifications;


	/**
	* "has kind"
	* "ModelingKind of the element: either type or instance."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/HasKind/kind")
	protected ModelingKind kind;


	/**
	* "has semantic ID"
	* "Points to the Expression Semantic of the Submodels"@en
	* "The semantic id might refer to an external information source, which explains the formulation of the submodel (for example an PDF if a standard)."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/HasSemantics/semanticId")
	protected Reference semanticId;


	/**
	* "has qualifier"
	* "Additional qualification of a qualifiable element."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Qualifiable/qualifier")
	protected List<Constraint> qualifiers;


	/**
	* "has maximum value"
	* "The maximum value of the range."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Range/max")
	protected TypedLiteral max;


	/**
	* "has minimum value"
	* "The minimum value of the range."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Range/min")
	protected TypedLiteral min;


	/**
	* "has value type of range"
	* "Data type of the min and max."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Range/valueType")
	protected String valueType;


	/**
	* "has description"
	* "Description or comments on the element. The description can be provided in several languages."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Referable/description")
	protected List<TypedLiteral> descriptions;


	/**
	* "has display name"
	* "Display name. Can be provided in several languages."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Referable/displayName")
	protected TypedLiteral displayName;


	/**
	* "has short id"
	* "Identifying string of the element within its name space."@en
	* "Constraint AASd-002: idShort shall only feature letters, digits, underscore (\'_\'); starting with a small letter. I.e. [a-z][a-zA-Z0-9_]+."@en
	* "Constraint AASd-003: idShort shall be matched case-insensitive."@en
	* "Constraint AASd-022: idShort of non-identifiable referables shall be unqiue in its namespace."@en
	* "Note: In case the element is a property and the property has a semantic definition (HasSemantics) the idShort is typically identical to the short name in English. "@en
	* "Note: In case of an identifiable element idShort is optional but recommended to be defined. It can be used for unique reference in its name space and thus allows better usability and a more performant implementation. In this case it is similar to the \'BrowserPath\' in OPC UA."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Referable/idShort")
	protected String idShort;


	/**
	* "has category"
	* "The category is a value that gives further meta information w.r.t. to the class of the element. It affects the expected existence of attributes and the applicability of constraints."@en
	*/
	@IRI("https://admin-shell.io/aas/3/0/RC01/Referable/referableCategory")
	protected List<String> referableCategories;


	// no manual construction
	protected DefaultRange() {
		id = VocabUtil.getInstance().createRandomUrl("range");
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
		return Objects.hash(new Object[]{this.valueType,
			this.max,
			this.min,
			this.referableCategories,
			this.descriptions,
			this.displayName,
			this.idShort,
			this.qualifiers,
			this.dataSpecifications,
			this.kind,
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
			DefaultRange other = (DefaultRange) obj;
			return Objects.equals(this.valueType, other.valueType) &&
				Objects.equals(this.max, other.max) &&
				Objects.equals(this.min, other.min) &&
				Objects.equals(this.referableCategories, other.referableCategories) &&
				Objects.equals(this.descriptions, other.descriptions) &&
				Objects.equals(this.displayName, other.displayName) &&
				Objects.equals(this.idShort, other.idShort) &&
				Objects.equals(this.qualifiers, other.qualifiers) &&
				Objects.equals(this.dataSpecifications, other.dataSpecifications) &&
				Objects.equals(this.kind, other.kind) &&
				Objects.equals(this.semanticId, other.semanticId);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/aas/3/0/RC01/Range/valueType")
	final public String getValueType() {
		return valueType;
	}
	
	final public void setValueType (String valueType) {
		this.valueType = valueType;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/Range/max")
	final public TypedLiteral getMax() {
		return max;
	}
	
	final public void setMax (TypedLiteral max) {
		this.max = max;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/Range/min")
	final public TypedLiteral getMin() {
		return min;
	}
	
	final public void setMin (TypedLiteral min) {
		this.min = min;
	}



	@IRI("https://admin-shell.io/aas/3/0/RC01/Referable/referableCategory")
	final public List<String> getReferableCategories() {
		return referableCategories;
	}
	
	final public void setReferableCategories (List<String> referableCategories) {
		this.referableCategories = referableCategories;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/Referable/description")
	final public List<TypedLiteral> getDescriptions() {
		return descriptions;
	}
	
	final public void setDescriptions (List<TypedLiteral> descriptions) {
		this.descriptions = descriptions;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/Referable/displayName")
	final public TypedLiteral getDisplayName() {
		return displayName;
	}
	
	final public void setDisplayName (TypedLiteral displayName) {
		this.displayName = displayName;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/Referable/idShort")
	final public String getIdShort() {
		return idShort;
	}
	
	final public void setIdShort (String idShort) {
		this.idShort = idShort;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/Qualifiable/qualifier")
	final public List<Constraint> getQualifiers() {
		return qualifiers;
	}
	
	final public void setQualifiers (List<Constraint> qualifiers) {
		this.qualifiers = qualifiers;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/HasDataSpecification/dataSpecification")
	final public List<Reference> getDataSpecifications() {
		return dataSpecifications;
	}
	
	final public void setDataSpecifications (List<Reference> dataSpecifications) {
		this.dataSpecifications = dataSpecifications;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/HasKind/kind")
	final public ModelingKind getKind() {
		return kind;
	}
	
	final public void setKind (ModelingKind kind) {
		this.kind = kind;
	}

	@IRI("https://admin-shell.io/aas/3/0/RC01/HasSemantics/semanticId")
	final public Reference getSemanticId() {
		return semanticId;
	}
	
	final public void setSemanticId (Reference semanticId) {
		this.semanticId = semanticId;
	}
}
