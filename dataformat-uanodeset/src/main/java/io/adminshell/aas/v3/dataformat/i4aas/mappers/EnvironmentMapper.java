package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.util.List;

import javax.xml.bind.JAXBElement;

import org.opcfoundation.ua._2008._02.types.ObjectFactory;
import org.opcfoundation.ua._2011._03.uanodeset.ListOfReferences;
import org.opcfoundation.ua._2011._03.uanodeset.LocalizedText;
import org.opcfoundation.ua._2011._03.uanodeset.Reference;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable.Value;
import org.opcfoundation.ua.i4aas.types.AASIdentifierTypeDataType;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject.Builder;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.Identifiable;
import io.adminshell.aas.v3.model.Identifier;
import io.adminshell.aas.v3.model.IdentifierType;
import io.adminshell.aas.v3.model.Referable;
import io.adminshell.aas.v3.model.Submodel;

public class EnvironmentMapper {

	private MappingContext ctx;
	private AssetAdministrationShellEnvironment aasEnvironment;

	public EnvironmentMapper(AssetAdministrationShellEnvironment aasEnvironment, MappingContext ctx) {
		this.aasEnvironment = aasEnvironment;
		this.ctx = ctx;
	}

	public UANodeSet toI4AAS() {
		List<AssetAdministrationShell> assetAdministrationShells = aasEnvironment.getAssetAdministrationShells();
		for (AssetAdministrationShell assetAdministrationShell : assetAdministrationShells) {
			
			UAObject aasUaObject = new AssetAdministrationShellMapper(assetAdministrationShell, ctx).map();
			aasUaObject.getSymbolicName().add(assetAdministrationShell.getIdShort());
			Reference orgaRef = new Reference();
			orgaRef.setReferenceType(UaId.Organizes.getName());
			orgaRef.setIsForward(false);
			orgaRef.setValue("i=85");
			aasUaObject.getReferences().getReference().add(orgaRef);
		}
		return ctx.getNodeSet();
	}
}
