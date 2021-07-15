package io.adminshell.aas.v3.dataformat.i4aas.mappers.utils;

public enum UaId implements BasicId  {
	
	Boolean("Boolean", 1),
	String("String", 12),
	DateTime("DateTime", 13),
	Organizes("Organizes", 35),
	HasTypeDefinition("HasTypeDefinition", 40),
	HasProperty("HasProperty", 46),
	HasComponent("HasComponent", 47),
	PropertyType("PropertyType", 68),
	IdType("IdType", 256),
	NumericRange("NumericRange", 291),
	HasDictionaryEntry("HasDictionaryEntry", 17597);

	
	private String name;
	private Integer id;

	private UaId(String name, Integer id) {
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
