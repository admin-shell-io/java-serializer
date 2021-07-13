package io.adminshell.aas.v3.dataformat.i4aas;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.opcfoundation.ua._2011._03.uanodeset.UANode;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;
import org.opcfoundation.ua._2011._03.uanodeset.UAVariable;

public class GeneratedExtensionObjectTest {

	@Test
	public void test() throws JAXBException {
		JAXBContext jaxbCtx = JAXBContext.newInstance(UANodeSet.class);
		InputStream resourceAsStream = GeneratedExtensionObjectTest.class
				.getResourceAsStream("/aasdatatypekey_example.xml");
		UANodeSet uanodeset = (UANodeSet) jaxbCtx.createUnmarshaller().unmarshal(resourceAsStream);
		List<UANode> uaObjectOrUAVariableOrUAMethod = uanodeset.getUAObjectOrUAVariableOrUAMethod();
		for (UANode uaNode : uaObjectOrUAVariableOrUAMethod) {
			if (uaNode.getNodeId().equals("ns=1;i=6002")) {
				UAVariable uaNodeAsVar = (UAVariable) uaNode;
				Object generic = uaNodeAsVar.getValue().getAny();
				System.out.println(generic.getClass().getName());
			}
		}
	}

}
