package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import javax.xml.bind.JAXBElement;

import org.opcfoundation.ua._2008._02.types.ExtensionObject;
import org.opcfoundation.ua._2008._02.types.ExtensionObject.Body;
import org.opcfoundation.ua._2008._02.types.ListOfExtensionObject;
import org.opcfoundation.ua._2008._02.types.NodeId;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable.Value;
import org.opcfoundation.ua.i4aas.types.AASKeyDataType;
import org.opcfoundation.ua.i4aas.types.AASKeyElementsDataType;
import org.opcfoundation.ua.i4aas.types.AASKeyTypeDataType;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASUtils;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4aasId;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.UaId;
import io.adminshell.aas.v3.model.Key;
import io.adminshell.aas.v3.model.Reference;

public class ReferenceMapper extends I4AASMapper<Reference, UAObject> {

	private String browseName;

	org.opcfoundation.ua._2008._02.types.ObjectFactory extensionObjectFactory = new org.opcfoundation.ua._2008._02.types.ObjectFactory();
	org.opcfoundation.ua.i4aas.types.ObjectFactory i4aasTypesObjectFactory = new org.opcfoundation.ua.i4aas.types.ObjectFactory();

	public ReferenceMapper(Reference src, MappingContext ctx, String browseName) {
		super(src, ctx);
		this.browseName = browseName;
	}

	@Override
	protected UAObject createTargetObject() {
		target = UAObject.builder().withNodeId(ctx.newModelNodeIdAsString()).withBrowseName(browseNameOf(browseName))
				.withDisplayName(I4AASUtils.createLocalizedText(browseName)).build();
		addTypeReference(I4aasId.AASReferenceType);
		return target;
	}

	@Override
	protected void mapAndAttachChildren() {
		UAVariable UAKeyVariable = UAVariable.builder().withNodeId(ctx.newModelNodeIdAsString())
				.withDataType(I4aasId.AASKeyDataType.getName()).withValueRank(1).withArrayDimensions("0")
				.withAccessLevel(3L).withDisplayName(I4AASUtils.createLocalizedText("Keys"))
				.withBrowseName(browseNameOf("Keys")).build();
		addTypeReference(UAKeyVariable, UaId.PropertyType);
		attachAsProperty(target, UAKeyVariable);
		addToNodeset(UAKeyVariable);
		
		if (!source.getKeys().isEmpty()) {			
			UAKeyVariable.setValue(new Value());
			ListOfExtensionObject listOfExtensions = new ListOfExtensionObject();

			for (Key key : source.getKeys()) {
				AASKeyDataType aasKeyDataType = AASKeyDataType.builder().//
				withIdType((AASKeyTypeDataType) I4AASEnumMapper.findMatch(key.getIdType())).//
				withType((AASKeyElementsDataType) I4AASEnumMapper.findMatch(key.getType())).//
				withValue(key.getValue()).build();
				JAXBElement<AASKeyDataType> jaxbAASKeyDataType = i4aasTypesObjectFactory.createAASKeyDataType(aasKeyDataType);
				
				Body uaxBody = extensionObjectFactory.createExtensionObjectBody();
				uaxBody.setAny(jaxbAASKeyDataType);
						
				NodeId typeId = extensionObjectFactory.createNodeId();
				typeId.setIdentifier(extensionObjectFactory.createNodeIdIdentifier(ctx.getI4aasNodeIdAsString(I4aasId.AASKeyDataType_Encoding_DefaultXml)));
				
				ExtensionObject extensionObject = extensionObjectFactory.createExtensionObject();
				extensionObject.setTypeId(extensionObjectFactory.createNodeId(typeId));
				extensionObject.setBody(extensionObjectFactory.createExtensionObjectBody(uaxBody));
				
				listOfExtensions.getExtensionObject().add(extensionObject);
			}			
			UAKeyVariable.getValue().setAny(extensionObjectFactory.createListOfExtensionObject(listOfExtensions));
		}
	}

}
