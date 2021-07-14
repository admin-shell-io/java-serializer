package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import java.time.LocalDateTime;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.opcfoundation.ua._2011._03.uanodeset.AliasTable;
import org.opcfoundation.ua._2011._03.uanodeset.ModelTable;
import org.opcfoundation.ua._2011._03.uanodeset.ModelTableEntry;
import org.opcfoundation.ua._2011._03.uanodeset.NodeIdAlias;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;
import org.opcfoundation.ua._2011._03.uanodeset.UriTable;

import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.Identifier;
import io.adminshell.aas.v3.model.Key;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.Submodel;

public class MappingContext {

	private final UANodeSet nodeset;
	private final AssetAdministrationShellEnvironment aasEnvironment;
	private final String modelNamspace;

	private final DatatypeFactory datatypeFactory = DatatypeFactory.newDefaultInstance();

	private int modelNsIndex;
	private int i4aasNsIndex;

	public MappingContext(AssetAdministrationShellEnvironment aasEnvironment) {
		this.aasEnvironment = aasEnvironment;
		nodeset = new UANodeSet();
		modelNamspace = I4AASUtils.generateRandomNamespace();
		initNodeset();
	}

	private void initNodeset() {
		initNamespace();
		initModelTable();
		initAliases();
	}

	private void initAliases() {
		nodeset.setAliases(new AliasTable());
		List<NodeIdAlias> aliases = nodeset.getAliases().getAlias();
		//add default aliases
		for (UaId uaId : UaId.values()) {			
			NodeIdAlias nodeIdAlias = new NodeIdAlias();
			nodeIdAlias.setAlias(uaId.getName());
			nodeIdAlias.setValue("i=" + uaId.getId());
			aliases.add(nodeIdAlias);
		}
		// add I4AAS Aliases
		for (I4aasId i4aasId : I4aasId.values()) {			
			NodeIdAlias nodeIdAlias = new NodeIdAlias();
			nodeIdAlias.setAlias(i4aasId.getName());
			nodeIdAlias.setValue("ns=" + getI4aasNsIndex() + ";i=" + i4aasId.getId());
			aliases.add(nodeIdAlias);
		}
	}

	private void initModelTable() {
		nodeset.setModels(new ModelTable());
		ModelTableEntry tableEntry = new ModelTableEntry();
		tableEntry.setModelUri(modelNamspace);
		XMLGregorianCalendar gregorianCalendar = datatypeFactory
				.newXMLGregorianCalendar(LocalDateTime.now().toString());
		tableEntry.setPublicationDate(gregorianCalendar);
		tableEntry.setVersion("1.0.0");

		ModelTableEntry uaRequiredEntry = new ModelTableEntry();
		uaRequiredEntry.setModelUri(I4AASConstants.UA_MODEL_URI);
		uaRequiredEntry.setPublicationDate(datatypeFactory.newXMLGregorianCalendar(I4AASConstants.UA_PUBDATE));
		uaRequiredEntry.setVersion(I4AASConstants.UA_VERSION);
		tableEntry.getRequiredModel().add(uaRequiredEntry);

		ModelTableEntry i4aasRequiredEntry = new ModelTableEntry();
		i4aasRequiredEntry.setModelUri(I4AASConstants.I4AAS_MODEL_URI);
		i4aasRequiredEntry.setPublicationDate(datatypeFactory.newXMLGregorianCalendar(I4AASConstants.I4AAS_PUBDATE));
		i4aasRequiredEntry.setVersion(I4AASConstants.I4AAS_VERSION);
		tableEntry.getRequiredModel().add(i4aasRequiredEntry);

		nodeset.getModels().getModel().add(tableEntry);
	}

	private void initNamespace() {
		nodeset.setNamespaceUris(new UriTable());
		nodeset.getNamespaceUris().getUri().add(modelNamspace);
		modelNsIndex = 1;
		nodeset.getNamespaceUris().getUri().add(I4AASConstants.I4AAS_MODEL_URI);
		i4aasNsIndex = 2;
	}

	public AssetAdministrationShellEnvironment getEnvironment() {
		return aasEnvironment;
	}

	public UANodeSet getNodeSet() {
		return nodeset;
	}

	public static void main(String[] args) {

	}

	public int getModelNsIndex() {
		return modelNsIndex;
	}

	public int getI4aasNsIndex() {
		return i4aasNsIndex;
	}

	private int nodeIdCounter = 1;

	public String newNodeId() {
		return "ns=" + getModelNsIndex() + ";i=" + nodeIdCounter++;
	}

	public String getI4aasNodeId(I4aasId id) {
		return getI4aasNodeId(id.getId());
	}
	
	public String getI4aasNodeId(Integer id) {
		return "ns=" + getI4aasNsIndex() + ";i=" + id;
	}
	
	public Submodel resolveSubmodelReference(io.adminshell.aas.v3.model.Reference aasRef) {
		List<Key> keys = aasRef.getKeys();
		if (keys.get(0).getType() == KeyElements.SUBMODEL) {
			List<Submodel> submodels = aasEnvironment.getSubmodels();
			for (Submodel submodel : submodels) {
				Identifier ident = submodel.getIdentification();
				if (ident != null && ident.getIdentifier() != null && ident.getIdentifier().equals(keys.get(0).getValue())) {
					return submodel;
				}
			}
		}
		return null;
	}

}
