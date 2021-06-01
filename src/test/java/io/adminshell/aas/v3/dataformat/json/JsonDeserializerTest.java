package io.adminshell.aas.v3.dataformat.json;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fraunhofer.iais.eis.AssetAdministrationShellEnvironment;
import de.fraunhofer.iais.eis.DefaultProperty;
import de.fraunhofer.iais.eis.DefaultSubmodel;
import de.fraunhofer.iais.eis.Property;
import de.fraunhofer.iais.eis.Submodel;
import java.io.File;

import static org.junit.Assert.assertEquals;

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

    private void checkImplementationClasses(AssetAdministrationShellEnvironment environment, Class<? extends Submodel> submodelImpl, Class<? extends Property> propertyImpl) {
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
}
