package io.adminshell.aas.v3.dataformat.i4aas.mappers;

public enum I4aasId {

	AASAssetAdministrationShellType("AASAssetAdministrationShellType", 1002),
	AASViewType("AASViewType", 1003),
	AASReferenceType("AASReferenceType", 1004),
	AASAssetType("AASAssetType", 1005),
	AASSubmodelType("AASSubmodelType", 1006),
	AASConceptDictionaryType("AASConceptDictionaryType", 1007),
	AASSubmodelElementType("AASSubmodelElementType", 1009),
	AASSubmodelElementCollectionType("AASSubmodelElementCollectionType", 1010),
	AASOrderedSubmodelElementCollectionType("AASOrderedSubmodelElementCollectionType", 1011),
	AASMultiLanguagePropertyType("AASMultiLanguagePropertyType", 1012),
	AASPropertyType("AASPropertyType", 1013),
	AASCapabilityType("AASCapabilityType", 1014),
	AASOperationType("AASOperationType", 1015),
	AASBlobType("AASBlobType", 1016),
	AASFileType("AASFileType", 1017),
	AASRelationshipElementType("AASRelationshipElementType", 1018),
	AASAnnotatedRelationshipElementType("AASAnnotatedRelationshipElementType", 1019),
	AASReferenceElementType("AASReferenceElementType", 1020),
	AASEventType("AASEventType", 1021),
	AASEntityType("AASEntityType", 1022),
	AASRangeType("AASRangeType", 1023),
	AASIrdiConceptDescriptionType("AASIrdiConceptDescriptionType", 1024),
	AASIriConceptDescriptionType("AASIriConceptDescriptionType", 1025),
	AASCustomConceptDescriptionType("AASCustomConceptDescriptionType", 1026),
	AASDataSpecificationType("AASDataSpecificationType", 1027),
	AASDataSpecificationIEC61360Type("AASDataSpecificationIEC61360Type", 1028),
	AASIdentifierType("AASIdentifierType", 1029),
	AASAdministrativeInformationType("AASAdministrativeInformationType", 1030),
	ValueListType("ValueListType", 1031),
	AASQualifierType("AASQualifierType", 1032),
	IAASReferableType("IAASReferableType", 1033),
	IAASIdentifiableType("IAASIdentifiableType", 1034),
	AASKeyTypeDataType("AASKeyTypeDataType", 3002),
	AASAssetKindDataType("AASAssetKindDataType", 3003),
	AASValueTypeDataType("AASValueTypeDataType", 3004),
	AASPathDataType("AASPathDataType", 3005),
	AASEntityTypeDataType("AASEntityTypeDataType", 3006),
	AASCategoryDataType("AASCategoryDataType", 3007),
	AASDataTypeIEC61360DataType("AASDataTypeIEC61360DataType", 3008),
	AASLevelTypeDataType("AASLevelTypeDataType", 3009),
	AASIdentifierTypeDataType("AASIdentifierTypeDataType", 3010),
	AASKeyDataType("AASKeyDataType", 3011),
	AASKeyElementsDataType("AASKeyElementsDataType", 3012),
	AASQualifierDataType("AASQualifierDataType", 3013),
	AASPropertyValueDataType("AASPropertyValueDataType", 3014),
	AASModelingKindDataType("AASModelingKindDataType", 3015),
	AASMimeDataType("AASMimeDataType", 3016),
	HasInterface("HasInterface", 4002),
	AASReference("AASReference", 4003),
;

	private String name;
	private Integer id;

	private I4aasId(String name, Integer id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

}
