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
			return new UANodeSetMarshaller(nodeset).marshallAsString();
		} catch (JAXBException e) {
			throw new SerializationException("Serialization failed due to a JAXBException.", e);
		}

	}

}
