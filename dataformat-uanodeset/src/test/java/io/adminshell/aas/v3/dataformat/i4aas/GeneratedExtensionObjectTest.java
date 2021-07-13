package io.adminshell.aas.v3.dataformat.i4aas;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.opcfoundation.ua._2008._02.types.ListOfExtensionObject;
import org.opcfoundation.ua._2011._03.uanodeset.UANode;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;
import org.opcfoundation.ua.i4aas.types.AASKeyDataType;
import org.w3c.dom.Node;

public class GeneratedExtensionObjectTest {

	@Test
	public void test() throws JAXBException {
		
		//needed context to unmarshal down to the custom defined types
		JAXBContext jaxbCtx = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[] {UANodeSet.class}, null);
		
		InputStream resourceAsStream = GeneratedExtensionObjectTest.class
				.getResourceAsStream("/aasdatatypekey_example.xml");
		UANodeSet uanodeset = (UANodeSet) jaxbCtx.createUnmarshaller().unmarshal(resourceAsStream);
		List<UANode> uaObjectOrUAVariableOrUAMethod = uanodeset.getUAObjectOrUAVariableOrUAMethod();
		for (UANode uaNode : uaObjectOrUAVariableOrUAMethod) {
			if (uaNode.getNodeId().equals("ns=1;i=6002")) {
				UAVariable uaNodeAsVar = (UAVariable) uaNode;
				Object genericExtension = uaNodeAsVar.getValue().getAny();
				System.out.println(genericExtension.getClass().getName());
				
				/**
				JAXBElement<ListOfExtensionObject> asJaxb = (JAXBElement<ListOfExtensionObject>) genericExtension;
				Object anyAASDataTypeKey = asJaxb.getValue().getExtensionObject().get(0).getBody().getValue().getAny();
				System.out.println(anyAASDataTypeKey.getClass().getName());

				if (anyAASDataTypeKey instanceof Node) {
					JAXBContext jaxbCtx2 = JAXBContext.newInstance(AASKeyDataType.class);
					//TODO
				}
				**/
			}
		}
	}

}
