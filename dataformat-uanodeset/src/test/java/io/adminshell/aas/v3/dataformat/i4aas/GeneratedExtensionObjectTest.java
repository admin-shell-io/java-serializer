package io.adminshell.aas.v3.dataformat.i4aas;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;
import org.opcfoundation.ua._2008._02.types.ListOfExtensionObject;
import org.opcfoundation.ua._2011._03.uanodeset.UANode;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;
import org.opcfoundation.ua.i4aas.types.AASKeyDataType;
import org.opcfoundation.ua.i4aas.types.AASKeyTypeDataType;
import org.w3c.dom.Node;

public class GeneratedExtensionObjectTest {

	@Test
	public void testJAXBunmarshalling() throws JAXBException {
		
		//needed context to unmarshal down to the custom defined types
		JAXBContext jaxbCtx = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[] {UANodeSet.class, ListOfExtensionObject.class, AASKeyDataType.class}, null);
		
		InputStream resourceAsStream = GeneratedExtensionObjectTest.class
				.getResourceAsStream("/aasdatatypekey_example.xml");
		UANodeSet uanodeset = (UANodeSet) jaxbCtx.createUnmarshaller().unmarshal(resourceAsStream);
		List<UANode> uaObjectOrUAVariableOrUAMethod = uanodeset.getUAObjectOrUAVariableOrUAMethod();
		for (UANode uaNode : uaObjectOrUAVariableOrUAMethod) {
			if (uaNode.getNodeId().equals("ns=1;i=6002")) {
				UAVariable uaNodeAsVar = (UAVariable) uaNode;
				Object genericExtension = uaNodeAsVar.getValue().getAny();
				
				Assert.assertEquals(javax.xml.bind.JAXBElement.class.getName(), genericExtension.getClass().getName());
				
				JAXBElement<ListOfExtensionObject> asJaxb = (JAXBElement<ListOfExtensionObject>) genericExtension;
				Object anyAASDataTypeKey = asJaxb.getValue().getExtensionObject().get(0).getBody().getValue().getAny();

				Assert.assertTrue(anyAASDataTypeKey instanceof Node);				
				
				JAXBElement<AASKeyDataType> aasKey = jaxbCtx.createUnmarshaller().unmarshal((Node) anyAASDataTypeKey, AASKeyDataType.class);
				
				Assert.assertEquals(AASKeyTypeDataType.ID_SHORT_0, aasKey.getValue().getIdType());
			}
		}
	}

}
