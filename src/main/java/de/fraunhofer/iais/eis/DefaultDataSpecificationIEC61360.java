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
* "Data Specification IEC 61360"
* "Data Specification Template for defining Property Descriptions conformant to IEC 61360."@en
* "Constraint AASd-075: For all ConceptDescriptions using data specification template IEC61360 (http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0) values for the attributes not being marked as mandatory or optional in tables Table 9, Table 10, Table 11 and Table 12.depending on its category are ignored and handled as undefined."@en 
*/

public class DefaultDataSpecificationIEC61360 implements DataSpecificationIEC61360 {

	protected URI id;

	//List of all labels of this class
	@JsonIgnore
	protected List<TypedLiteral> labels = Arrays.asList(new TypedLiteral("Data Specification IEC 61360", ""));

	//List of all comments of this class
	@JsonIgnore
	protected List<TypedLiteral> comments = Arrays.asList(new TypedLiteral("Data Specification Template for defining Property Descriptions conformant to IEC 61360.", "en"));

	// instance fields as derived from the Asset Administration Shell ontology

	/**
	* "has datatype"
	* "Constraint AASd-070: For a ConceptDescription with category PROPERTY or VALUE using data specification template IEC61360 (http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0) -  DataSpecificationIEC61360/dataType is mandatory and shall be defined."@en
	* "Constraint AASd-071: For a ConceptDescription with category REFERENCE using data specification template IEC61360 (http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0) -  DataSpecificationIEC61360/dataType is STRING by default."@en
	* "Constraint AASd-072: For a ConceptDescription with category DOCUMENT using data specification template IEC61360 (http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0) -  DataSpecificationIEC61360/dataType shall be one of the following values: STRING or URL."@en
	* "Constraint AASd-073: For a ConceptDescription with category QUALIFIER using data specification template IEC61360 (http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0) -  DataSpecificationIEC61360/dataType is mandatory and shall be defined."@en
	*/
	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/dataType")
	protected List<DataTypeIEC61360> dataTypes;


	/**
	* "has definition"
	* "Constraint AASd-074: For all ConceptDescriptions except for ConceptDescriptions of category VALUE using data specification template IEC61360 (http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0) -  DataSpecificationIEC61360/definition is mandatory and shall be defined at least in English."@en
	*/
	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/definition")
	protected List<TypedLiteral> definitions;


	/**
	* "has level type"
	*/
	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/levelType")
	protected List<LevelType> levelTypes;


	/**
	* "has preferred name"
	*/
	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/preferredName")
	protected TypedLiteral preferredName;


	/**
	* "has short name"
	*/
	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/shortName")
	protected TypedLiteral shortName;


	/**
	* "has source of definition"
	*/
	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/sourceOfDefinition")
	protected String sourceOfDefinition;


	/**
	* "has symbol"
	*/
	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/symbol")
	protected String symbol;


	/**
	* "has unit"
	*/
	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/unit")
	protected String unit;


	/**
	* "has unit id"
	*/
	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/unitId")
	protected Reference unitId;


	/**
	* "has value"
	*/
	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/value")
	protected String value;


	/**
	* "has value format"
	*/
	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/valueFormat")
	protected String valueFormat;


	/**
	* "has value id"
	*/
	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/valueId")
	protected Reference valueId;


	/**
	* "has value list"
	* "The Type \'ValueList\' lists all the allowed values for a concept description for which the allowed values are listed in an enumeration. The value list is a set of value reference pairs."@en
	*/
	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/valueList")
	protected String valueList;


	// no manual construction
	protected DefaultDataSpecificationIEC61360() {
		id = VocabUtil.getInstance().createRandomUrl("dataSpecificationIEC61360");
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
		return Objects.hash(new Object[]{this.dataTypes,
			this.definitions,
			this.levelTypes,
			this.preferredName,
			this.shortName,
			this.sourceOfDefinition,
			this.symbol,
			this.unit,
			this.unitId,
			this.valueFormat,
			this.value,
			this.valueList,
			this.valueId});
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
			DefaultDataSpecificationIEC61360 other = (DefaultDataSpecificationIEC61360) obj;
			return Objects.equals(this.dataTypes, other.dataTypes) &&
				Objects.equals(this.definitions, other.definitions) &&
				Objects.equals(this.levelTypes, other.levelTypes) &&
				Objects.equals(this.preferredName, other.preferredName) &&
				Objects.equals(this.shortName, other.shortName) &&
				Objects.equals(this.sourceOfDefinition, other.sourceOfDefinition) &&
				Objects.equals(this.symbol, other.symbol) &&
				Objects.equals(this.unit, other.unit) &&
				Objects.equals(this.unitId, other.unitId) &&
				Objects.equals(this.valueFormat, other.valueFormat) &&
				Objects.equals(this.value, other.value) &&
				Objects.equals(this.valueList, other.valueList) &&
				Objects.equals(this.valueId, other.valueId);
		}
	}


	// accessor method implementations as derived from the Asset Administration Shell ontology


	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/dataType")
	final public List<DataTypeIEC61360> getDataTypes() {
		return dataTypes;
	}
	
	final public void setDataTypes (List<DataTypeIEC61360> dataTypes) {
		this.dataTypes = dataTypes;
	}

	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/definition")
	final public List<TypedLiteral> getDefinitions() {
		return definitions;
	}
	
	final public void setDefinitions (List<TypedLiteral> definitions) {
		this.definitions = definitions;
	}

	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/levelType")
	final public List<LevelType> getLevelTypes() {
		return levelTypes;
	}
	
	final public void setLevelTypes (List<LevelType> levelTypes) {
		this.levelTypes = levelTypes;
	}

	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/preferredName")
	final public TypedLiteral getPreferredName() {
		return preferredName;
	}
	
	final public void setPreferredName (TypedLiteral preferredName) {
		this.preferredName = preferredName;
	}

	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/shortName")
	final public TypedLiteral getShortName() {
		return shortName;
	}
	
	final public void setShortName (TypedLiteral shortName) {
		this.shortName = shortName;
	}

	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/sourceOfDefinition")
	final public String getSourceOfDefinition() {
		return sourceOfDefinition;
	}
	
	final public void setSourceOfDefinition (String sourceOfDefinition) {
		this.sourceOfDefinition = sourceOfDefinition;
	}

	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/symbol")
	final public String getSymbol() {
		return symbol;
	}
	
	final public void setSymbol (String symbol) {
		this.symbol = symbol;
	}

	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/unit")
	final public String getUnit() {
		return unit;
	}
	
	final public void setUnit (String unit) {
		this.unit = unit;
	}

	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/unitId")
	final public Reference getUnitId() {
		return unitId;
	}
	
	final public void setUnitId (Reference unitId) {
		this.unitId = unitId;
	}

	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/valueFormat")
	final public String getValueFormat() {
		return valueFormat;
	}
	
	final public void setValueFormat (String valueFormat) {
		this.valueFormat = valueFormat;
	}

	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/value")
	final public String getValue() {
		return value;
	}
	
	final public void setValue (String value) {
		this.value = value;
	}

	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/valueList")
	final public String getValueList() {
		return valueList;
	}
	
	final public void setValueList (String valueList) {
		this.valueList = valueList;
	}

	@IRI("https://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/3/0/RC01/DataSpecificationIEC61360/valueId")
	final public Reference getValueId() {
		return valueId;
	}
	
	final public void setValueId (Reference valueId) {
		this.valueId = valueId;
	}

}
