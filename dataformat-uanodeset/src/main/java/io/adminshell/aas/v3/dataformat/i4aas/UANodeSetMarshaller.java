package io.adminshell.aas.v3.dataformat.i4aas;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.opcfoundation.ua._2008._02.types.ListOfExtensionObject;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;
import org.opcfoundation.ua.i4aas.types.AASKeyDataType;

public class UANodeSetMarshaller {

	private UANodeSet nodeset;

	public UANodeSetMarshaller(UANodeSet nodeset) {
		this.nodeset = nodeset;
	}

	public String marshallAsString() throws JAXBException {
		JAXBContext jaxbCtx = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[] {UANodeSet.class, ListOfExtensionObject.class, AASKeyDataType.class}, null);
		Marshaller marshaller = jaxbCtx.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", true);
		StringWriter stringWriter = new StringWriter();
		marshaller.marshal(nodeset, stringWriter);
		return stringWriter.toString();
	}

}
