package io.adminshell.aas.v3.dataformat.i4aas.mappers;

import static org.junit.Assert.*;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;
import org.opcfoundation.ua._2011._03.uanodeset.UAObject;

import io.adminshell.aas.v3.dataformat.i4aas.AASSimple;
import io.adminshell.aas.v3.dataformat.i4aas.UANodeSetMarshaller;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShellEnvironment;

public class SubmodelMapperTest {

	@Test
	public void test() throws JAXBException {
		SubmodelMapper tesling = new SubmodelMapper(AASSimple.SUBMODEL_DOCUMENTATION, new MappingContext(new DefaultAssetAdministrationShellEnvironment()));
		UAObject map = tesling.map();
		UANodeSet nodeSet = tesling.ctx.getNodeSet();
		String marshallAsString = new UANodeSetMarshaller(nodeSet).marshallAsString();
		System.out.println(marshallAsString);
	}

}
