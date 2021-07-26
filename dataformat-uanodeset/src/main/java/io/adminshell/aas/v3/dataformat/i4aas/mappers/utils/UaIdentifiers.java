/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.adminshell.aas.v3.dataformat.i4aas.mappers.utils;

import java.util.Map;
import java.util.TreeMap;

public class UaIdentifiers {

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
