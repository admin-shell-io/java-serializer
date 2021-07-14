package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Identifiers {

	public static Integer AASAssetAdministrationShellType = 1002;
	public static Integer AASViewType = 1003;
	public static Integer AASReferenceType = 1004;
	public static Integer AASAssetType = 1005;
	public static Integer AASSubmodelType = 1006;
	public static Integer AASConceptDictionaryType = 1007;
	public static Integer AASSubmodelElementType = 1009;
	public static Integer AASSubmodelElementCollectionType = 1010;
	public static Integer AASOrderedSubmodelElementCollectionType = 1011;
	public static Integer AASMultiLanguagePropertyType = 1012;
	public static Integer AASPropertyType = 1013;
	public static Integer AASCapabilityType = 1014;
	public static Integer AASOperationType = 1015;
	public static Integer AASBlobType = 1016;
	public static Integer AASFileType = 1017;
	public static Integer AASRelationshipElementType = 1018;
	public static Integer AASAnnotatedRelationshipElementType = 1019;
	public static Integer AASReferenceElementType = 1020;
	public static Integer AASEventType = 1021;
	public static Integer AASEntityType = 1022;
	public static Integer AASRangeType = 1023;
	public static Integer AASIrdiConceptDescriptionType = 1024;
	public static Integer AASIriConceptDescriptionType = 1025;
	public static Integer AASCustomConceptDescriptionType = 1026;
	public static Integer AASDataSpecificationType = 1027;
	public static Integer AASDataSpecificationIEC61360Type = 1028;
	public static Integer AASIdentifierType = 1029;
	public static Integer AASAdministrativeInformationType = 1030;
	public static Integer ValueListType = 1031;
	public static Integer AASQualifierType = 1032;
	public static Integer IAASReferableType = 1033;
	public static Integer IAASIdentifiableType = 1034;
	public static Integer AASKeyTypeDataType = 3002;
	public static Integer AASAssetKindDataType = 3003;
	public static Integer AASValueTypeDataType = 3004;
	public static Integer AASPathDataType = 3005;
	public static Integer AASEntityTypeDataType = 3006;
	public static Integer AASCategoryDataType = 3007;
	public static Integer AASDataTypeIEC61360DataType = 3008;
	public static Integer AASLevelTypeDataType = 3009;
	public static Integer AASIdentifierTypeDataType = 3010;
	public static Integer AASKeyDataType = 3011;
	public static Integer AASKeyElementsDataType = 3012;
	public static Integer AASQualifierDataType = 3013;
	public static Integer AASPropertyValueDataType = 3014;
	public static Integer AASModelingKindDataType = 3015;
	public static Integer AASMimeDataType = 3016;
	public static Integer HasInterface = 4002;
	public static Integer AASReference = 4003;
	
	public static Map<String, Integer> name2IdMap = new TreeMap<>();
	public static Map<Integer, String> id2NameMap = new TreeMap<>();
	
	static {
		name2IdMap.put("AASAssetAdministrationShellType", 1002);
		name2IdMap.put("AASViewType", 1003);
		name2IdMap.put("AASReferenceType", 1004);
		name2IdMap.put("AASAssetType", 1005);
		name2IdMap.put("AASSubmodelType", 1006);
		name2IdMap.put("AASConceptDictionaryType", 1007);
		name2IdMap.put("AASSubmodelElementType", 1009);
		name2IdMap.put("AASSubmodelElementCollectionType", 1010);
		name2IdMap.put("AASOrderedSubmodelElementCollectionType", 1011);
		name2IdMap.put("AASMultiLanguagePropertyType", 1012);
		name2IdMap.put("AASPropertyType", 1013);
		name2IdMap.put("AASCapabilityType", 1014);
		name2IdMap.put("AASOperationType", 1015);
		name2IdMap.put("AASBlobType", 1016);
		name2IdMap.put("AASFileType", 1017);
		name2IdMap.put("AASRelationshipElementType", 1018);
		name2IdMap.put("AASAnnotatedRelationshipElementType", 1019);
		name2IdMap.put("AASReferenceElementType", 1020);
		name2IdMap.put("AASEventType", 1021);
		name2IdMap.put("AASEntityType", 1022);
		name2IdMap.put("AASRangeType", 1023);
		name2IdMap.put("AASIrdiConceptDescriptionType", 1024);
		name2IdMap.put("AASIriConceptDescriptionType", 1025);
		name2IdMap.put("AASCustomConceptDescriptionType", 1026);
		name2IdMap.put("AASDataSpecificationType", 1027);
		name2IdMap.put("AASDataSpecificationIEC61360Type", 1028);
		name2IdMap.put("AASIdentifierType", 1029);
		name2IdMap.put("AASAdministrativeInformationType", 1030);
		name2IdMap.put("ValueListType", 1031);
		name2IdMap.put("AASQualifierType", 1032);
		name2IdMap.put("IAASReferableType", 1033);
		name2IdMap.put("IAASIdentifiableType", 1034);
		name2IdMap.put("AASKeyTypeDataType", 3002);
		name2IdMap.put("AASAssetKindDataType", 3003);
		name2IdMap.put("AASValueTypeDataType", 3004);
		name2IdMap.put("AASPathDataType", 3005);
		name2IdMap.put("AASEntityTypeDataType", 3006);
		name2IdMap.put("AASCategoryDataType", 3007);
		name2IdMap.put("AASDataTypeIEC61360DataType", 3008);
		name2IdMap.put("AASLevelTypeDataType", 3009);
		name2IdMap.put("AASIdentifierTypeDataType", 3010);
		name2IdMap.put("AASKeyDataType", 3011);
		name2IdMap.put("AASKeyElementsDataType", 3012);
		name2IdMap.put("AASQualifierDataType", 3013);
		name2IdMap.put("AASPropertyValueDataType", 3014);
		name2IdMap.put("AASModelingKindDataType", 3015);
		name2IdMap.put("AASMimeDataType", 3016);
		name2IdMap.put("HasInterface", 4002);
		name2IdMap.put("AASReference", 4003);
		//flip for other map
		name2IdMap.forEach((s,i)->id2NameMap.put(i, s));
	}

}
