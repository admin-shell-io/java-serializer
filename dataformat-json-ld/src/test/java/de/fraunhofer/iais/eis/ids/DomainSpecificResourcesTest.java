package de.fraunhofer.iais.eis.ids;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;


import de.fraunhofer.iais.eis.util.TypedLiteral;
import org.junit.Test;

import de.fraunhofer.iais.eis.DataResource;
import de.fraunhofer.iais.eis.Resource;
import de.fraunhofer.iais.eis.ids.jsonld.Serializer;



public class DomainSpecificResourcesTest {

	/**
	 * This test loads a JSON-LD with unknown (neither ids, idsc, fhg digital etc.) namespaces and checks whether some
	 * of them survived the parsing/serialization
	 */
	@Test
	public void test() throws IOException {
		
		byte[] encoded = Files.readAllBytes(Paths.get("src/test/resources/Life-Expectancy.jsonld"));
		
		String resource =  new String(encoded);
		
		Serializer serializer = new Serializer();
		Resource res = serializer.deserialize(resource, DataResource.class);

		String serialized_resource = serializer.serialize(res);
		//System.out.println(serialized_resource);
		assertTrue(serialized_resource.contains("\"http://rdfs.org/ns/void#distinctObjects\" :"));
		assertTrue(res.getProperties().containsKey("http://rdfs.org/ns/void#distinctObjects"));
		Object o = res.getProperties().get("http://rdfs.org/ns/void#distinctObjects");
		assertTrue(o instanceof TypedLiteral);
		assertEquals("111", ((TypedLiteral) o).getValue());
		assertEquals("http://www.w3.org/2001/XMLSchema#integer", ((TypedLiteral) o).getType());
		assertTrue(res.getProperties().containsKey("http://rdfs.org/ns/void#propertyPartition"));
		Object o2 = res.getProperties().get("http://rdfs.org/ns/void#propertyPartition");
		assertTrue(o2 instanceof ArrayList);
		assertTrue(serialized_resource.contains("\"http://rdfs.org/ns/void#triples\" :"));
		assertTrue(serialized_resource.contains("\"http://rdfs.org/ns/void#property\" :"));
		assertTrue(serialized_resource.contains("\"@id\" : \"http://dbpedia.org/resource/Year\""));

	}

	/**
	 * This test parses, serializes, parses, and serializes a data resource in order to see if things change.
	 */
	@Test
	public void testSerializationChain() throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get("src/test/resources/Life-Expectancy.jsonld"));
		String source =  new String(encoded);

		Serializer serializer = new Serializer();

		Resource res1 = serializer.deserialize(source, DataResource.class);
		String serialized1 = serializer.serialize(res1);

		Resource res2 = serializer.deserialize(serialized1, DataResource.class);
		String serialized2 = serializer.serialize(res2);

		//Make sure they have the same key set
		for(Map.Entry<String, Object> entry : res1.getProperties().entrySet())
		{
			assertTrue(res2.getProperties().containsKey(entry.getKey()));
		}
		for(Map.Entry<String, Object> entry : res2.getProperties().entrySet())
		{
			assertTrue(res1.getProperties().containsKey(entry.getKey()));
		}

		assertTrue(res1.getProperties().size() > 10);


		//assertTrue(serialized1.equalsIgnoreCase(serialized2));
	}

	/* This test requires to switch the information model to the FhG version (which is commented out in the pom.xml file)
	@Test
	public void implClassesFhgDigital() throws IOException {
		Serializer s = new Serializer();
		Serializer.addKnownNamespace("info", "http://www.fraunhofer.de/fraunhofer-digital/infomodell/");
		Serializer.addKnownNamespace("kdsf", "http://kerndatensatz-forschung.de/version1/technisches_datenmodell/owl/Basis#");
		Serializer.addKnownNamespace("foaf", "http://xmlns.com/foaf/0.1/");

		String fhgDigitalConnectorAsString = SerializerUtil.readResourceToString("FhGDigitalConnector1.jsonld");
		Connector conn = s.deserialize(fhgDigitalConnectorAsString, Connector.class);
		for(Class<?> c : allClasses)
		{
			System.out.println(c.getName());
		}

	}
	 */

}
