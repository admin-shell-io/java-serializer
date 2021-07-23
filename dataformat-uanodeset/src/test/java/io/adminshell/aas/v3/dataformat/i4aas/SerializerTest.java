package io.adminshell.aas.v3.dataformat.i4aas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import io.adminshell.aas.v3.dataformat.SerializationException;
import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.MappingContext;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShellEnvironment;

public class SerializerTest {

	@BeforeClass
	public static void before() {
		MappingContext.setModelNamespaceNamingStrategy(nodeset -> "http://example.org/SerializerTest");
	}

	@Test
	public void testEmpty() throws SerializationException {
		I4AASSerializer i4aasSerializer = new I4AASSerializer();
		String write = i4aasSerializer.write(new DefaultAssetAdministrationShellEnvironment());
		System.out.println(write);
	}

	@Test
	public void testSimple() throws SerializationException, IllegalArgumentException, IllegalAccessException {
		I4AASSerializer i4aasSerializer = new I4AASSerializer();
		String write = i4aasSerializer.write(AASSimple.ENVIRONMENT);
		for (String toCheck : AASSimple.getContainedStrings()) {
			if (toCheck.toLowerCase().contains("thumbnail")) {
				//gets remapped to DefaultThumbnail
				toCheck = "DefaultThumbnail";
			}
			if (toCheck.equals("integer") || toCheck.equals("langString")) {
				//ignore, gets mapped to enum index values
				continue;
			}
			Assert.assertTrue("contains " + toCheck, write.contains(toCheck));
		}
		System.out.println(write);
	}

	@Test
	public void testSimpleToFile() throws SerializationException, IOException {
		Path createTempFile = Files.createTempFile("testSimpleToFile", ".xml");
		I4AASSerializer i4aasSerializer = new I4AASSerializer();
		i4aasSerializer.write(createTempFile.toFile(), AASSimple.ENVIRONMENT);
		System.out.println(createTempFile.toAbsolutePath().toString());
	}

	@Test
	public void testFull() throws SerializationException {
		I4AASSerializer i4aasSerializer = new I4AASSerializer();
		String write = i4aasSerializer.write(AASFull.ENVIRONMENT);
		System.out.println(write);
	}

}
