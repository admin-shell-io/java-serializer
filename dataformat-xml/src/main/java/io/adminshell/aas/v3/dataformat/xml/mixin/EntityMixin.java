package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.serialization.SubmodelElementsSerializer;
import io.adminshell.aas.v3.model.EntityType;
import io.adminshell.aas.v3.model.IdentifierKeyValuePair;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.SubmodelElement;

@JsonPropertyOrder({ "extensions",  "idShort", "displayNames", "category", "descriptions",
	"kind", "semanticId", "qualifiers", "dataSpecifications", "globalAssetId", "externalAssetId", "entityType", "statements" })
public interface EntityMixin {
	
	@JacksonXmlProperty(localName = "aas:globalAssetId")
	public Reference getGlobalAssetId();
	
	@JacksonXmlProperty(localName = "aas:specificAssetId")
	public IdentifierKeyValuePair getExternalAssetId();

	@JacksonXmlProperty(localName = "aas:entityType")
    public EntityType getEntityType();
	
    @JacksonXmlProperty(localName = "aas:statements")
    @JsonSerialize(using = SubmodelElementsSerializer.class)
	public List<SubmodelElement> getStatements();	

}