package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.Map;
import java.util.TreeMap;

public class UaDefaultIdentifiers {

	public static Integer Boolean = 1;
	public static Integer String = 12;
	public static Integer DateTime = 13;
	public static Integer Organizes = 35;
	public static Integer HasTypeDefinition = 40;
	public static Integer HasProperty = 46;
	public static Integer HasComponent = 47;
	public static Integer IdType = 256;
	public static Integer NumericRange = 291;
	public static Integer HasDictionaryEntry = 17597;

	public static Map<String, Integer> name2IdMap = new TreeMap<>();
	public static Map<Integer, String> id2NameMap = new TreeMap<>();

	static {
		name2IdMap.put("Boolean", 1);
		name2IdMap.put("String", 12);
		name2IdMap.put("DateTime", 13);
		name2IdMap.put("Organizes", 35);
		name2IdMap.put("HasTypeDefinition", 40);
		name2IdMap.put("HasProperty", 46);
		name2IdMap.put("HasComponent", 47);
		name2IdMap.put("IdType", 256);
		name2IdMap.put("NumericRange", 291);
		name2IdMap.put("HasDictionaryEntry", 17597);
		// flip for other map
		name2IdMap.forEach((s, i) -> id2NameMap.put(i, s));
	}

}
