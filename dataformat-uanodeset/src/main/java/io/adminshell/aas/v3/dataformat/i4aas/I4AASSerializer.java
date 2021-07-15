package io.adminshell.aas.v3.dataformat.i4aas;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.opcfoundation.ua._2011._03.uanodeset.UANode;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;

import io.adminshell.aas.v3.dataformat.SerializationException;
import io.adminshell.aas.v3.dataformat.Serializer;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.EnvironmentMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;

public class I4AASSerializer implements Serializer {

	@Override
	public String write(AssetAdministrationShellEnvironment aasEnvironment) throws SerializationException {
		MappingContext mappingContext = new MappingContext(aasEnvironment);
		EnvironmentMapper environmentMapper = new EnvironmentMapper(mappingContext.getEnvironment(), mappingContext);
		
		//map action
		UANodeSet nodeset = environmentMapper.toI4AAS();
		
		try {
			JAXBContext jaxbCtx = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[] {UANodeSet.class}, null);
			Marshaller marshaller = jaxbCtx.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", true);
			StringWriter stringWriter = new StringWriter();
			marshaller.marshal(nodeset, stringWriter);
			return stringWriter.toString();
		} catch (JAXBException e) {
			throw new SerializationException("Serialization failed due to a JAXBException.", e);
		}

	}

}
