package io.adminshell.aas.v3.dataformat.json;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.adminshell.aas.v3.dataformat.Deserializer;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.Property;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.impl.DefaultProperty;
import io.adminshell.aas.v3.model.impl.DefaultSubmodel;

public class JsonDeserializerTest {

	private static final Logger logger = LoggerFactory.getLogger(JsonDeserializerTest.class);
	private static final Deserializer deserializer = new JsonDeserializer();

	@Test
	public void testReadFromFile() throws Exception {
		deserializer.read(AASSimple.FILE);
	}

	@Test
	public void testSimpleExample() throws Exception {
		AssetAdministrationShellEnvironment env = deserializer.read(AASSimple.FILE);
		assertEquals(env, AASSimple.ENVIRONMENT);
	}

	@Test
	public void testCustomImplementationClass() throws Exception {
		String json = new JsonSerializer().write(AASSimple.ENVIRONMENT);
		Deserializer deserializer = new JsonDeserializer();
		AssetAdministrationShellEnvironment environment = deserializer.read(json);
		checkImplementationClasses(environment, DefaultSubmodel.class, DefaultProperty.class);
		deserializer.useImplementation(Submodel.class, CustomSubmodel.class);
		deserializer.useImplementation(Property.class, CustomProperty.class);
		environment = deserializer.read(json);
		checkImplementationClasses(environment, CustomSubmodel.class, CustomProperty.class);
		deserializer.useImplementation(Submodel.class, CustomSubmodel2.class);
		environment = deserializer.read(json);
		checkImplementationClasses(environment, CustomSubmodel2.class, CustomProperty.class);
	}

	private void checkImplementationClasses(AssetAdministrationShellEnvironment environment,
			Class<? extends Submodel> submodelImpl, Class<? extends Property> propertyImpl) {
		environment.getSubmodels().forEach(submodel -> {
			assertEquals(submodel.getClass(), submodelImpl);
			submodel.getSubmodelElements().stream()
					.filter(element -> Property.class.isAssignableFrom(element.getClass()))
					.forEach(element -> assertEquals(element.getClass(), propertyImpl));
		});
	}

	@Test
	public void testFullExample() throws Exception {
		AssetAdministrationShellEnvironment env = deserializer.read(AASFull.FILE);
		assertEquals(env, AASFull.ENVIRONMENT);
	}

	@Test
	public void testFullExampleSubmodels() throws Exception {
		AssetAdministrationShellEnvironment env = deserializer.read(AASFull.FILE);
		AssetAdministrationShellEnvironment src_env = AASFull.ENVIRONMENT;

		assertEquals(env.getSubmodels(), src_env.getSubmodels());
	}

	@Test
	public void testFullExampleAssets() throws Exception {
		AssetAdministrationShellEnvironment env = deserializer.read(AASFull.FILE);
		AssetAdministrationShellEnvironment src_env = AASFull.ENVIRONMENT;

		assertEquals(env.getAssets(), src_env.getAssets());

	}

	@Test
	public void testFullExampleConceptDescriptions() throws Exception {
		AssetAdministrationShellEnvironment env = deserializer.read(AASFull.FILE);
		AssetAdministrationShellEnvironment src_env = AASFull.ENVIRONMENT;

		assertEquals(env.getConceptDescriptions(), src_env.getConceptDescriptions());

	}

	@Test
	public void testFullExampleAAS() throws Exception {
		AssetAdministrationShellEnvironment env = deserializer.read(AASFull.FILE);
		AssetAdministrationShellEnvironment src_env = AASFull.ENVIRONMENT;

		assertEquals(env.getAssetAdministrationShells(), src_env.getAssetAdministrationShells());

	}

}
