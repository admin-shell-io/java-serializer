package io.adminshell.aas.v3.dataformat.i4aas;

import static org.junit.Assert.*;

import org.junit.Test;

import io.adminshell.aas.v3.dataformat.SerializationException;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShellEnvironment;

public class SerialzerTest {

	@Test
	public void testEmpty() throws SerializationException {
		I4AASSerializer i4aasSerializer = new I4AASSerializer();
		String write = i4aasSerializer.write(new DefaultAssetAdministrationShellEnvironment());
		System.out.println(write);
		
	}

}
