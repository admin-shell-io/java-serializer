package io.adminshell.aas.v3.dataformat.i4aas;

import javax.xml.bind.JAXBException;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.SerializationException;
import io.adminshell.aas.v3.dataformat.Serializer;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.EnvironmentMapper;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;

public class I4AASSerializer implements Serializer {

	@Override
	public String write(AssetAdministrationShellEnvironment aasEnvironment) throws SerializationException {
		MappingContext mappingContext = new MappingContext(aasEnvironment);
		UAObject uaEnv = new EnvironmentMapper(mappingContext.getEnvironment(), mappingContext).map();
		
		//map action
		UANodeSet nodeset = mappingContext.getNodeSet();
		
		try {
			return new UANodeSetMarshaller(nodeset).marshallAsString();
		} catch (JAXBException e) {
			throw new SerializationException("Serialization failed due to a JAXBException.", e);
		}

	}

}
