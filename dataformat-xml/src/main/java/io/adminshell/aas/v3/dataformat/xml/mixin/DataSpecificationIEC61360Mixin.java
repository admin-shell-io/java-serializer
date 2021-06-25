package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.serialization.LangStringsIEC61360Serializer;
import io.adminshell.aas.v3.dataformat.xml.serialization.ReferenceIEC61360Serializer;
import io.adminshell.aas.v3.model.DataTypeIEC61360;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.LevelType;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.ValueList;

@JsonPropertyOrder({ "preferredName",  "shortName", "unit", "unitId", "sourceOfDefinition",
	"symbol", "dataType", "definition", "valueFormat", "valueList",
	"value", "valueId", "leyelTypes" })
public interface DataSpecificationIEC61360Mixin {

	@JacksonXmlProperty(localName = "IEC61360:preferredName")
	@JsonSerialize(using = LangStringsIEC61360Serializer.class)
	public List<LangString> getPreferredNames();
	
	@JacksonXmlProperty(localName = "IEC61360:shortName")
	@JsonSerialize(using = LangStringsIEC61360Serializer.class)
	public List<LangString> getShortNames();
	
	@JacksonXmlProperty(localName = "IEC61360:unit")
	public String getUnit();
	
	@JacksonXmlProperty(localName = "IEC61360:unitId")
	@JsonSerialize(using = ReferenceIEC61360Serializer.class)
	public Reference getUnitId();
	
	@JacksonXmlProperty(localName = "IEC61360:sourceOfDefinition")
	public String getSourceOfDefinition();
	
	@JacksonXmlProperty(localName = "IEC61360:symbol")
	public String getSymbol();
	
	@JacksonXmlProperty(localName = "IEC61360:dataType")
	public DataTypeIEC61360 getDataType();
	
	@JacksonXmlProperty(localName = "IEC61360:definition")
	@JsonSerialize(using = LangStringsIEC61360Serializer.class)
	public List<LangString> getDefinitions();
	
	@JacksonXmlProperty(localName = "IEC61360:valueFormat")
	public String getValueFormat();
	
	@JacksonXmlProperty(localName = "IEC61360:valueList")
	public ValueList getValueList();
	
	@JacksonXmlProperty(localName = "IEC61360:value")
	public String getValue();
	
	@JacksonXmlProperty(localName = "IEC61360:valueId")
	@JsonSerialize(using = ReferenceIEC61360Serializer.class)
	public Reference getValueId();
	
	@JacksonXmlProperty(localName = "IEC61360:levelType")
	public List<LevelType> getLevelTypes();

}
