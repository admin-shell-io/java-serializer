package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import org.opcfoundation.ua._2011._03.uanodeset.UANode;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;

import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;

public class EnvironmentMapper {
	
	private MappingContext ctx;
	private AssetAdministrationShellEnvironment aasEnvironment;

	public EnvironmentMapper(AssetAdministrationShellEnvironment aasEnvironment, MappingContext ctx) {
		this.aasEnvironment = aasEnvironment;
		this.ctx = ctx;
	}

	public UANode toI4AAS() {
		return null;		
	}

}
