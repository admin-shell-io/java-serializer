package de.fraunhofer.iais.eis.ids;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fraunhofer.iais.eis.*;
import de.fraunhofer.iais.eis.ids.jsonld.Serializer;
import de.fraunhofer.iais.eis.ids.jsonld.preprocessing.JsonPreprocessor;
import de.fraunhofer.iais.eis.ids.jsonld.preprocessing.TypeNamePreprocessor;
import de.fraunhofer.iais.eis.util.PlainLiteral;
import de.fraunhofer.iais.eis.util.TypedLiteral;
import de.fraunhofer.iais.eis.util.Util;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFLanguages;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class SerializerTest { 

	private static ConnectorUpdateMessage basicInstance;
	private static Connector nestedInstance;
	private static RejectionMessage enumInstance;
	private static Connector securityProfileInstance;
	private static Serializer serializer;
	private static XMLGregorianCalendar now;

	private static final String imVersion = "4.0.1";


	private void readToModel(Model targetModel, String rdfInput)
	{
		RDFDataMgr.read(targetModel, new ByteArrayInputStream(rdfInput.getBytes()), RDFLanguages.JSONLD);
	}

	@BeforeClass
	public static void setUp() throws ConstraintViolationException, DatatypeConfigurationException, URISyntaxException, MalformedURLException {
		serializer = new Serializer();

		// object with only basic types
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		now = DatatypeFactory.newInstance().newXMLGregorianCalendar(c).normalize();

		basicInstance = new ConnectorUpdateMessageBuilder()
				._issued_(now)
				._modelVersion_(imVersion)
				._issuerConnector_(new URL("http://iais.fraunhofer.de/connectorIssuer").toURI())
				._senderAgent_(URI.create("http://example.org/senderAgent"))
				._affectedConnector_(URI.create("http://example.org/someConnector"))
				._securityToken_(new DynamicAttributeTokenBuilder()._tokenFormat_(TokenFormat.JWT)._tokenValue_("test1234").build())
				.build();

		ArrayList<Resource> resources = new ArrayList<>();
		resources.add(new ResourceBuilder()._version_(imVersion)._contentStandard_(new URL("http://iais.fraunhofer.de/contentStandard1").toURI()).build());
		resources.add(new ResourceBuilder()._version_(imVersion)._contentStandard_(new URL("http://iais.fraunhofer.de/contentStandard2").toURI()).build());

		// connector -> object with nested types
		ResourceCatalog catalog = new ResourceCatalogBuilder()
				._offeredResource_(resources)
				.build();

		nestedInstance = new BaseConnectorBuilder()
				._maintainer_(new URL("http://iais.fraunhofer.de/connectorMaintainer").toURI())
				._version_(imVersion)
				._resourceCatalog_(Util.asList(catalog))
				.build();

		// object with enum
		enumInstance = new RejectionMessageBuilder()
				._issuerConnector_(new URL("http://iais.fraunhofer.de/connectorIssuer").toURI())
				._modelVersion_(imVersion)
				._rejectionReason_(RejectionReason.METHOD_NOT_SUPPORTED)
				._senderAgent_(URI.create("http://example.org/senderAgent"))
				._correlationMessage_(URI.create("http://example.org/theMessageYouJustSent"))
				._issued_(now)
				._securityToken_(new DynamicAttributeTokenBuilder()._tokenFormat_(TokenFormat.JWT)._tokenValue_("test1234").build())
				.build();

		securityProfileInstance = new BaseConnectorBuilder()
				._maintainer_(new URL("http://iais.fraunhofer.de/connectorMaintainer").toURI())
				._version_(imVersion)
				._resourceCatalog_(Util.asList(catalog))
				//                ._securityProfile_(SecurityProfile.BASE_CONNECTOR_SECURITY_PROFILE)
				.build();
		
	}

	@Test
	public void jsonldSerialize_Basic() throws IOException {
		String connectorAvailableMessage = serializer.serialize(basicInstance);
		Assert.assertNotNull(connectorAvailableMessage);
		Model model = ModelFactory.createDefaultModel();
		readToModel(model, connectorAvailableMessage);

		Assert.assertNotNull(model);

		ConnectorUpdateMessage deserializedConnectorAvailableMessage = serializer.deserialize(connectorAvailableMessage, ConnectorUpdateMessageImpl.class);

		Assert.assertEquals(basicInstance.getId(), deserializedConnectorAvailableMessage.getId());
		Assert.assertNotNull(deserializedConnectorAvailableMessage);

		Assert.assertEquals(basicInstance.getContentVersion(), deserializedConnectorAvailableMessage.getContentVersion());
		Assert.assertEquals(basicInstance.getModelVersion(), deserializedConnectorAvailableMessage.getModelVersion());
		Assert.assertEquals(basicInstance.getIssuerConnector(), deserializedConnectorAvailableMessage.getIssuerConnector());
		Assert.assertEquals(basicInstance.getSenderAgent(), deserializedConnectorAvailableMessage.getSenderAgent());
		Assert.assertEquals(basicInstance.getSecurityToken().getTokenValue(), deserializedConnectorAvailableMessage.getSecurityToken().getTokenValue());
		Assert.assertEquals(basicInstance.getCorrelationMessage(), deserializedConnectorAvailableMessage.getCorrelationMessage());
		Assert.assertEquals(basicInstance.getAffectedConnector(), deserializedConnectorAvailableMessage.getAffectedConnector());

	}

	@Test
	@Ignore //TODO
	public void jsonldSerialize_Nested() throws IOException, NoSuchFieldException, IllegalAccessException {
		String connector = serializer.serialize(nestedInstance, RDFLanguages.JSONLD);
		Assert.assertNotNull(connector);

		Model model = ModelFactory.createDefaultModel();
		readToModel(model, connector);
		Assert.assertNotNull(model);

		Connector deserializedConnector = serializer.deserialize(connector, BaseConnectorImpl.class);
		Assert.assertNotNull(deserializedConnector);

		Field properties = BaseConnectorImpl.class.getDeclaredField("properties");
		properties.setAccessible(true);
		properties.set(deserializedConnector, null); // Serialiser creates an empty HashMap, which kills the following equality check

		Assert.assertTrue(EqualsBuilder.reflectionEquals(nestedInstance, deserializedConnector, true, Object.class, true));
	}

	@Test
	public void jsonldSerialize_Enum() throws IOException {
		String rejectionMessage = serializer.serialize(enumInstance, RDFLanguages.JSONLD);
		Assert.assertNotNull(rejectionMessage);

		Model model = ModelFactory.createDefaultModel();
		readToModel(model, rejectionMessage);
		Assert.assertNotNull(model);

		RejectionMessage deserializedRejectionMessage = serializer.deserialize(rejectionMessage, RejectionMessage.class);
		Assert.assertNotNull(deserializedRejectionMessage);

		Assert.assertEquals(enumInstance.getContentVersion(), deserializedRejectionMessage.getContentVersion());
		Assert.assertEquals(enumInstance.getModelVersion(), deserializedRejectionMessage.getModelVersion());
		Assert.assertEquals(enumInstance.getIssuerConnector(), deserializedRejectionMessage.getIssuerConnector());
		Assert.assertEquals(enumInstance.getSenderAgent(), deserializedRejectionMessage.getSenderAgent());
		Assert.assertEquals(enumInstance.getSecurityToken().getTokenValue(), deserializedRejectionMessage.getSecurityToken().getTokenValue());
		Assert.assertEquals(enumInstance.getRejectionReason(), deserializedRejectionMessage.getRejectionReason());
		Assert.assertEquals(enumInstance.getCorrelationMessage(), deserializedRejectionMessage.getCorrelationMessage());
	}

	@Test
	public void jsonldSerialize_SecurityProfile() throws IOException {
		String connector = serializer.serialize(securityProfileInstance, RDFLanguages.JSONLD);
		Assert.assertNotNull(connector);

		Model model = ModelFactory.createDefaultModel();
		readToModel(model, connector);
		Assert.assertNotNull(model);
	}

	@Test
	public void jsonldSerialize_Literal() throws ConstraintViolationException, IOException {
		Resource resource = new ResourceBuilder()
				._description_(Util.asList(new TypedLiteral("literal no langtag"), new TypedLiteral("english literal", "en")))
				.build();

		String serialized = serializer.serialize(resource);
		Assert.assertNotNull(serialized);

		Model model = ModelFactory.createDefaultModel();
		readToModel(model, serialized);
		Assert.assertNotNull(model);

		// do not use reflective equals here as ArrayList comparison fails due to different modCount
		Resource deserializedResource = serializer.deserialize(serialized, ResourceImpl.class);
		Assert.assertEquals(2, deserializedResource.getDescription().size());
		Iterator<? extends TypedLiteral> names = deserializedResource.getDescription().iterator();
		int ctrNull = 0;
		int ctrLang = 0;
		while(names.hasNext())
		{
			TypedLiteral literal = names.next();
			if(literal.getLanguage() == null)
			{
				ctrNull++;
			}
			else
			{
				ctrLang++;
			}
		}
		Assert.assertEquals(1, ctrLang);
		Assert.assertEquals(1, ctrNull);
	}

	@Ignore //This test does not work anymore. The .json files (not .jsonld) do not contain any context or whatever. This causes Jena to be unable to retrieve the required info
	@Test
	public void legacySerializationsJson_validate() {
		Connector connector = null;
		Connector connector_update = null;
		try {
			connector = serializer.deserialize(SerializerUtil.readResourceToString("Connector1.json"), Connector.class);
			connector_update = serializer.deserialize(SerializerUtil.readResourceToString("Connector1_update.json"), Connector.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(connector);
		Assert.assertNotNull(connector_update);
	}

	@Test
	public void legacySerializationsJsonld_validate() throws IOException {
		Connector connector = null;
		Connector connector2 = null;
		try {
			serializer.addPreprocessor(new TypeNamePreprocessor());
			connector = serializer.deserialize(SerializerUtil.readResourceToString("Connector1.jsonld"), Connector.class);
			connector2 = serializer.deserialize(SerializerUtil.readResourceToString("Connector2.jsonld"), Connector.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(connector);
		Assert.assertNotNull(connector2);


		Model model = ModelFactory.createDefaultModel();
		readToModel(model, SerializerUtil.readResourceToString("Connector1.jsonld"));

		Assert.assertNotNull(model);

		model = ModelFactory.createDefaultModel();
		readToModel(model, SerializerUtil.readResourceToString("Connector2.jsonld"));
		Assert.assertNotNull(model);
	}

	@Test
	public void calendarSerialization() throws IOException {
		String time = serializer.serialize(now);
		System.out.println(time);
	}

	@Test
	@Ignore // TODO enable this test as soon as we can work with unknown namespaces
	public void serializeForeignProperties() throws Exception {
		serializer.addPreprocessor(new TypeNamePreprocessor());
		String serialized = "{\n" +
				"  \"@context\" : \"https://w3id.org/idsa/contexts/3.0.0/context.jsonld\",\n" +
				"  \"@type\" : \"ids:Broker\",\n" +
				"  \"inboundModelVersion\" : [ \"" + imVersion+ "\" ],\n" +
				"  \"@id\" : \"https://w3id.org/idsa/autogen/broker/5b9170a7-73fd-466e-89e4-83cedfe805aa\",\n" +
				"  \"http://xmlns.com/foaf/0.1/name\" : \"https://iais.fraunhofer.de/eis/ids/broker1/frontend\",\n" +
				"  \"http://xmlns.com/foaf/0.1/homepage\" : {\n  \"https://example.de/key\" : \"https://example.de/value\"\n}" +
				"}";
		Broker broker = serializer.deserialize(serialized, Broker.class);
		String originalSimplified = SerializerUtil.stripWhitespaces(serialized);
		String reserializedSimplified = SerializerUtil.stripWhitespaces(serializer.serialize(broker, RDFLanguages.JSONLD));
		Assert.assertEquals(originalSimplified, reserializedSimplified);
	}

	@Test
	public void deserializeSingleValueAsArray() {
		ContractOffer contractOffer = null;
		try {
			contractOffer = serializer.deserialize(SerializerUtil.readResourceToString("ContractOfferValueForArray.jsonld"), ContractOffer.class);
		} catch(IOException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(contractOffer);
	}

	@Test
	public void serializePlainJson() throws IOException {
		DescriptionRequestMessage sdr = new DescriptionRequestMessageBuilder()
				._contentVersion_("test")
				._issued_(now)
				._modelVersion_(imVersion)
				._issuerConnector_(URI.create("http://iais.fraunhofer.de/connectorIssuer"))
				._senderAgent_(URI.create("http://example.org/senderAgent"))
				._securityToken_(new DynamicAttributeTokenBuilder()._tokenFormat_(TokenFormat.JWT)._tokenValue_("test1234").build())
				.build();
		Serializer s = new Serializer();
		s.serialize(sdr);
	}

	@Test
	public void deserializeThroughInheritanceChain() throws IOException {

		DescriptionRequestMessage sdr = new DescriptionRequestMessageBuilder()
				._contentVersion_("test")
				._issued_(now)
				._modelVersion_(imVersion)
				._issuerConnector_(URI.create("http://iais.fraunhofer.de/connectorIssuer"))
				._senderAgent_(URI.create("http://example.org/senderAgent"))
				._securityToken_(new DynamicAttributeTokenBuilder()._tokenFormat_(TokenFormat.JWT)._tokenValue_("test1234").build())
				.build();
		String serialized = serializer.serialize(sdr);
		Message m = serializer.deserialize(serialized, Message.class);

		Assert.assertEquals(sdr.getContentVersion(), m.getContentVersion());
		Assert.assertEquals(sdr.getModelVersion(), m.getModelVersion());
		Assert.assertEquals(sdr.getIssuerConnector(), m.getIssuerConnector());
		Assert.assertEquals(sdr.getSenderAgent(), m.getSenderAgent());
		Assert.assertEquals(sdr.getSecurityToken().getTokenValue(), m.getSecurityToken().getTokenValue());

		//Field properties = DescriptionRequestMessageImpl.class.getDeclaredField("properties");
		//properties.setAccessible(true);
		//properties.set(m, null); // Serializer creates an empty HashMap, which kills the following equality check

		//reflectionEquals cannot be used reliably anymore, as the process of putting incoming RDF into a model and extracting it can transform the values legally
		//E.g., it is perfectly valid to transform a timestamp 2020-01-01T10:00:00+02:00 to 2020-01-01T:08:00:00Z (i.e. getting rid of the offset)
		//Such transformations kill the assertion though...
		//Assert.assertTrue(EqualsBuilder.reflectionEquals(sdr, m, true, Object.class, true));
	}

	@Test
	public void deserializeWithAndWithoutTypePrefix() {
		String withIdsPrefix = "{\n" +
				"  \"@context\" : \"https://w3id.org/idsa/contexts/3.0.0/context.jsonld\",\n" +
				"  \"@type\" : \"ids:TextResource\",\n" +
				"  \"@id\" : \"https://creativecommons.org/licenses/by-nc/4.0/legalcode\"\n" +
				"}";
		String withAbsoluteURI = "{\n" +
				"  \"@type\" : \"https://w3id.org/idsa/core/TextResource\",\n" +
				"  \"@id\" : \"https://creativecommons.org/licenses/by-nc/4.0/legalcode\"\n" +
				"}";

		String withoutExplicitPrefix = "{\n" +
				"  \"@context\" : \"https://w3id.org/idsa/contexts/3.0.0/context.jsonld\",\n" +
				"  \"@type\" : \"TextResource\",\n" +
				"  \"@id\" : \"https://creativecommons.org/licenses/by-nc/4.0/legalcode\"\n" +
				"}";

		try {

			Object defaultDeserialization = serializer.deserialize(withIdsPrefix, TextResource.class);

			JsonPreprocessor preprocessor = new TypeNamePreprocessor();
			serializer.addPreprocessor(preprocessor, true);

			Object deserializedWithIdsPrefix = serializer.deserialize(withIdsPrefix, TextResource.class);
			Object deserializedWithAbsoluteURI = serializer.deserialize(withAbsoluteURI, TextResource.class);
			Object deserializedWithoutExplicitPrefix = serializer.deserialize(withoutExplicitPrefix, TextResource.class);

			Field properties = TextResourceImpl.class.getDeclaredField("properties");
			properties.setAccessible(true);
			properties.set(defaultDeserialization, null); // Serialiser creates an empty HashMap, which kills the following equality check
			properties.set(deserializedWithIdsPrefix, null); // Serialiser creates an empty HashMap, which kills the following equality check
			properties.set(deserializedWithAbsoluteURI, null); // Serialiser creates an empty HashMap, which kills the following equality check
			properties.set(deserializedWithoutExplicitPrefix, null); // Serialiser creates an empty HashMap, which kills the following equality check


			serializer.removePreprocessor(preprocessor);

			//These asserts cause illegal reflective access operations
			//TODO: Add other assertions
			//Assert.assertTrue(EqualsBuilder.reflectionEquals(defaultDeserialization, deserializedWithIdsPrefix, true, Object.class, true));
			//Assert.assertTrue(EqualsBuilder.reflectionEquals(defaultDeserialization, deserializedWithAbsoluteURI, true, Object.class, true));
			//Assert.assertTrue(EqualsBuilder.reflectionEquals(defaultDeserialization, deserializedWithoutExplicitPrefix, true, Object.class, true));
		} catch (IOException | NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}


	@Ignore // This test intends to check a single date, not embedded in any enclosing JSON-LD. This does not make sense with the latest serializer version.
	@Test
	public void stableCalendarFormat() throws IOException {
		String serialized = "2019-07-24T17:29:18.908+02:00";

		XMLGregorianCalendar xgc = serializer.deserialize(serialized, XMLGregorianCalendar.class);
		String reserialized = serializer.serialize(xgc);
		Assert.assertEquals(serialized, reserialized);
	}

	/**
	 * lists have to be serialized with a @context element in each child
	 * otherwise, RDF4j does not correctly parse the data resulting in empty model and empty Turtle serialization
	 * @throws IOException on serialization failure
	 */
	@Test
	public void listWithContext() throws IOException {
		ContractOffer contractOffer1 = new ContractOfferBuilder().build();
		ContractOffer contractOffer2 = new ContractOfferBuilder().build();
		//TODO: This test is rather minimalistic now. Used to be more complex
		String serializedList = serializer.serialize(Util.asList(contractOffer1, contractOffer2));

		Model model = ModelFactory.createDefaultModel();
		readToModel(model, serializedList);
		Assert.assertEquals(2, model.size());

		String ttl = serializer.convertJsonLdToOtherRdfFormat(serializedList, RDFLanguages.TURTLE);
		Assert.assertFalse(ttl.isEmpty());
	}


	@Test
	public void testJwtAttributesInContext() throws IOException {
		DatPayload datPayload = new DatPayloadBuilder()
				._exp_(new BigInteger(String.valueOf(12)))
				._aud_(Audience.IDS_CONNECTOR_ATTRIBUTES_ALL)
				.build();

		String serialized = serializer.serialize(datPayload);

		Model model = ModelFactory.createDefaultModel();
		readToModel(model, serialized);
		//Rio.parse(new StringReader(serialized), "http://example.org/rdf#", RDFFormat.JSONLD); // ensure that valid JSON-LD is serialized
		Assert.assertTrue(serialized.contains("\"exp\" : \"ids:exp\"")); // ensure DatPayload fields are added to the context
	}



	@Test
	public void rightOperandTest() throws IOException, URISyntaxException {

		Constraint constraint = new ConstraintBuilder()
				._leftOperand_(LeftOperand.PAY_AMOUNT)
				._operator_(BinaryOperator.EQ)
				._rightOperand_(new TypedLiteral("5", new URI("http://www.w3.org/2001/XMLSchema#string")))
				.build();
		serializer.serialize(constraint);


		String constraintString = "{\r\n" + 
				"  \"@context\" : {\r\n" + 
				"    \"ids\" : \"https://w3id.org/idsa/core/\",\r\n" + 
				"    \"idsc\" : \"https://w3id.org/idsa/code/\"\r\n" + 
				"  },\r\n" + 
				"  \"@type\" : \"ids:Constraint\",\r\n" + 
				"  \"@id\" : \"https://w3id.org/idsa/autogen/constraint/69755e0f-bf2f-4f62-b14d-6837a1cf1f6a\",\r\n" + 
				"  \"ids:leftOperand\" : {\r\n" + 
				"    \"@id\" : \"idsc:PAY_AMOUNT\"\r\n" + 
				"  },\r\n" + 
				"  \"ids:operator\" : {\r\n" + 
				"    \"@id\" : \"idsc:EQ\"\r\n" + 
				"  },\r\n" + 
				"  \"ids:rightOperand\" : {\r\n" + 
				"    \"@value\" : \"5\",\r\n" + 
				"    \"@type\" : \"http://www.w3.org/2001/XMLSchema#string\"\r\n" +
				//"    \"@language\" : \"en\"\r\n" +
				"  }\r\n" + 
				"}";
		serializer.deserialize(constraintString, Constraint.class);

	}

	@Test
	public void serializingListOfUrisTest() throws IOException, DatatypeConfigurationException {

		DynamicAttributeToken token = new DynamicAttributeTokenBuilder()
				._tokenFormat_(TokenFormat.JWT)
				._tokenValue_("sampleToken")
				.build();

		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		XMLGregorianCalendar now = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

		ResponseMessage message = new ResponseMessageBuilder()
				._securityToken_(token)
				._correlationMessage_(URI.create("http://example.com"))
				._issued_(now)
				._issuerConnector_(URI.create("http://example.com"))
				._modelVersion_(imVersion)
				._senderAgent_(URI.create("http://example.com"))
				._recipientConnector_(Util.asList(URI.create("http://example.com"), URI.create("http://anotherExample.com")))
				//._recipientAgent_(Util.asList(URI.create("example.com")))
				.build();

		String s = serializer.serialize(message);
		//System.out.println(s);
		serializer.deserialize(s, ResponseMessage.class);
	}


	@Test
	public void plainLiteralParseTest() throws IOException {

		Serializer localSerializer = new Serializer();

		// Prepare the test data
		String jsonLd1 = "{\r\n" + 
				"  \"@context\" : {\r\n" + 
				"    \"ids\" : \"https://w3id.org/idsa/core/\",\r\n" + 
				"    \"idsc\" : \"https://w3id.org/idsa/code/\"\r\n" + 
				"  },\r\n" + 
				"  \"@type\" : \"ids:Resource\",\r\n" + 
				"  \"@id\" : \"https://w3id.org/idsa/autogen/resource/69755e0f-bf2f-4f62-b14d-6837a1cf1f6a\",\r\n" + 
				"  \"ids:description\" : \"a description 1\",\r\n" + // plain 
				"  \"ids:keyword\" : \"keyword1\"\r\n" + // plain 
				"}";
		String jsonLd2 = "{\r\n" + 
				"  \"@context\" : {\r\n" + 
				"    \"ids\" : \"https://w3id.org/idsa/core/\",\r\n" + 
				"    \"idsc\" : \"https://w3id.org/idsa/code/\"\r\n" + 
				"  },\r\n" + 
				"  \"@type\" : \"ids:Resource\",\r\n" + 
				"  \"@id\" : \"https://w3id.org/idsa/autogen/resource/69755e0f-bf2f-4f62-b14d-6837a1cf1f6a\",\r\n" + 
				"  \"ids:description\" : {\r\n" + 
				"    \"@value\" : \"a description 2\"\r\n" + // with no tag
				"  },\r\n" + 
				"  \"ids:keyword\" : {\r\n" + 
				"    \"@value\" : \"keyword2\"\r\n"  + // with no tag
				"  }\r\n" + 
				"}";
		String jsonLd3 = "{\r\n" + 
				"  \"@context\" : {\r\n" + 
				"    \"ids\" : \"https://w3id.org/idsa/core/\",\r\n" + 
				"    \"idsc\" : \"https://w3id.org/idsa/code/\"\r\n" + 
				"  },\r\n" + 
				"  \"@type\" : \"ids:Resource\",\r\n" + 
				"  \"@id\" : \"https://w3id.org/idsa/autogen/resource/69755e0f-bf2f-4f62-b14d-6837a1cf1f6a\",\r\n" + 
				"  \"ids:description\" : {\r\n" + 
				"    \"@value\" : \"a description 3\",\r\n" + 
				"    \"@language\" : \"en\"\r\n" + // with language tag
				"  },\r\n" + 
				"  \"ids:keyword\" : {\r\n" + 
				"    \"@value\" : \"keyword3\",\r\n" + 
				"    \"@language\" : \"en\"\r\n" + // with language tag
				"  }\r\n" + 
				"}";
		String jsonLd4 = "{\r\n" + 
				"  \"@context\" : {\r\n" + 
				"    \"ids\" : \"https://w3id.org/idsa/core/\",\r\n" + 
				"    \"idsc\" : \"https://w3id.org/idsa/code/\"\r\n" + 
				"  },\r\n" + 
				"  \"@type\" : \"ids:Resource\",\r\n" + 
				"  \"@id\" : \"https://w3id.org/idsa/autogen/resource/69755e0f-bf2f-4f62-b14d-6837a1cf1f6a\",\r\n" + 
				"  \"ids:description\" : {\r\n" + 
				"    \"@value\" : \"a description 4\",\r\n" + 
				"    \"@type\" : \"http://www.w3.org/2001/XMLSchema#string\"\r\n" + // with string type
				"  },\r\n" + 
				"  \"ids:keyword\" : {\r\n" + 
				"    \"@value\" : \"keyword4\",\r\n" + 
				"    \"@type\" : \"http://www.w3.org/2001/XMLSchema#string\"\r\n" + // with string type
				"  }\r\n" + 
				"}";
		String jsonLd5 = "{\r\n" + 
				"  \"@context\" : {\r\n" + 
				"    \"ids\" : \"https://w3id.org/idsa/core/\",\r\n" + 
				"    \"idsc\" : \"https://w3id.org/idsa/code/\"\r\n" + 
				"  },\r\n" + 
				"  \"@type\" : \"ids:Resource\",\r\n" + 
				"  \"@id\" : \"https://w3id.org/idsa/autogen/resource/69755e0f-bf2f-4f62-b14d-6837a1cf1f6a\",\r\n" + 
				"  \"ids:description\" : {\r\n" + 
				"    \"@value\" : \"a description 5\",\r\n" + 
				"    \"@type\" : \"xsd:string\"\r\n" + // with string type and xsd: prefix. Note that this is an unknown namespace and therefore invalid! The serializer will throw a warning
				"  },\r\n" + 
				"  \"ids:keyword\" : {\r\n" + 
				"    \"@value\" : \"keyword5\",\r\n" + 
				"    \"@type\" : \"xsd:string\"\r\n" + // with string type and xsd: prefix Note that this is an unknown namespace and therefore invalid! The serializer will throw a warning
				"  }\r\n" + 
				"}";


		String[] jsonLds = new String[] {jsonLd1, jsonLd2, jsonLd3, jsonLd4, jsonLd5};


		for (int i = 0; i < jsonLds.length; i++) {
			String jsonLD = jsonLds[i];

			Resource fromJSONLD = localSerializer.deserialize(jsonLD, Resource.class);

			Resource resource = new ResourceBuilder()
					._description_(Util.asList(new TypedLiteral("a description " + (i+1), "en")))
					._keyword_(Util.asList(new TypedLiteral("keyword" + (i+1))))
					.build();
			serializer.serialize(resource);

			TypedLiteral descriptionJSONLD = fromJSONLD.getDescription().get(0);
			TypedLiteral description = resource.getDescription().get(0);

			assertTrue(descriptionJSONLD.getValue().equalsIgnoreCase(description.getValue()));
			if (i==2) assertTrue(descriptionJSONLD.getLanguage().equalsIgnoreCase(description.getLanguage()));
		}
	}

	@Test
	public void getLabelAndCommentsTest() throws IOException {
		SecurityProfile profile = SecurityProfile.BASE_SECURITY_PROFILE;
		Assert.assertFalse(profile.getComment().isEmpty());
		Assert.assertFalse(profile.getLabel().isEmpty());
		String rdfProfile = serializer.serialize(profile);
		if(rdfProfile.contains("label\"") || rdfProfile.contains("comment\""))
		{
			Assert.fail();
		}
	}


	@SuppressWarnings("deprecation")
	@Test
	public void typedLiteralSerialiseTest() throws IOException, de.fraunhofer.iais.eis.util.ConstraintViolationException, URISyntaxException {
		Resource resource1 = new ResourceBuilder()
				._description_(Util.asList(new PlainLiteral("a description 1")))
				._keyword_(Util.asList(new PlainLiteral("keyword1")))
				.build();
		Resource resource2 = new ResourceBuilder()
				._description_(Util.asList(new PlainLiteral("\"a description 2\"^^http://www.w3.org/2001/XMLSchema#string")))
				._keyword_(Util.asList(new PlainLiteral("\"keyword2\"^^http://www.w3.org/2001/XMLSchema#string")))
				.build();
		Resource resource3 = new ResourceBuilder()
				._description_(Util.asList(new PlainLiteral("a description 3@en")))
				._keyword_(Util.asList(new PlainLiteral("keyword3@en")))
				.build();
		Resource resource4 = new ResourceBuilder()
				._description_(Util.asList(new PlainLiteral("a description 4", "en")))
				._keyword_(Util.asList(new PlainLiteral("keyword4", "en")))
				.build();
		Resource resource5 = new ResourceBuilder()
				._description_(Util.asList(new TypedLiteral("a description 5", "en")))
				._keyword_(Util.asList(new TypedLiteral("keyword5", "en")))
				.build();
		Resource resource6 = new ResourceBuilder()
				._description_(Util.asList(new TypedLiteral("a description 6", new URI("http://www.w3.org/2001/XMLSchema#string"))))
				._keyword_(Util.asList(new TypedLiteral("keyword6", new URI("http://www.w3.org/2001/XMLSchema#string"))))
				.build();
		Resource resource7 = new ResourceBuilder()
				._description_(Util.asList(new TypedLiteral("\"a description 7\"^^http://www.w3.org/2001/XMLSchema#string")))
				._keyword_(Util.asList(new TypedLiteral("\"keyword7\"^^http://www.w3.org/2001/XMLSchema#string")))
				.build();
		//Note that the following resource uses the unknown namespace "xsd:" -- the serializer will throw a warning here
		Resource resource8 = new ResourceBuilder()
				._description_(Util.asList(new TypedLiteral("\"a description 8\"^^xsd:string")))
				._keyword_(Util.asList(new TypedLiteral("\"keyword8\"^^xsd:string")))
				.build();
		
		Resource[] resources = new Resource[] { resource1, resource2, resource3, resource4, resource5, resource6, resource7, resource8};
		Serializer localSerializer = new Serializer();
		
		for (Resource resource : resources ) {
			String resourceAsJsonLD = localSerializer.serialize(resource);
			try {
				Resource parsedResource = localSerializer.deserialize(resourceAsJsonLD, Resource.class);
				assertEquals(resource.getDescription().get(0).getValue(), parsedResource.getDescription().get(0).getValue());
				assertEquals(resource.getDescription().get(0).getLanguage(), parsedResource.getDescription().get(0).getLanguage());
			}
			catch (IOException e)
			{
				System.out.println(resourceAsJsonLD);
				Assert.fail();
			}
		}
	}


	/* trash?
	@Test
	public void testRio() throws IOException {
		String jsonld = SerializerUtil.readResourceToString("ContractOfferValueForArray.jsonld");

		Serializer localSerializer = new Serializer();
		localSerializer.deserialize(jsonld, Contract.class);

		RDFParser rdfParser = Rio.createParser(RDFFormat.JSONLD);
		Model model = new LinkedHashModel();
		rdfParser.setRDFHandler(new StatementCollector(model));

		rdfParser.parse(new StringReader(jsonld), "http://example.org/rdf#");

	}
	 */
	
	/**
	 * This test is based on a ticket and bugfix received on 15.05.2020
	 * see Erik van den Akker's email (Infomodel Serializer: NullpointerException)
	 * @author sbader
	 * @throws IOException if serialization fails
	 * @throws ConstraintViolationException in case of mandatory fields missing (should not happen here, as all fields are hard coded)
	 */
	@Test 
	public void testArraysWithUris() throws IOException, de.fraunhofer.iais.eis.util.ConstraintViolationException {
		Serializer serializer = new Serializer();
		
	    DynamicAttributeToken token = new DynamicAttributeTokenBuilder()
	            ._tokenFormat_(TokenFormat.JWT)
	            ._tokenValue_("sampleToken")
	            .build();

	    ResponseMessage message = new ResponseMessageBuilder()
	            ._securityToken_(token)
	            ._correlationMessage_(URI.create("http://example.com"))
	            ._issued_(now)
	            ._issuerConnector_(URI.create("http://example.com"))
	            ._modelVersion_(imVersion)
	            ._senderAgent_(URI.create("http://example.com"))
	            ._recipientAgent_(new ArrayList<>(Util.asList(URI.create("http://example.com"))))
	            ._recipientConnector_(new ArrayList<>(Util.asList(URI.create("http://example.com"))))
	            .build();

	    serializer.serialize(message);
	}


	/**
	 * This test checks whether different date formulations are treated accordingly
	 *
	 */
	@Test
	public void testDateTimeStamp() throws IOException, ConstraintViolationException {
		String jsonld1 = "{\n" +
				"  \"@context\" : {\n" +
				"    \"ids\" : \"https://w3id.org/idsa/core/\",\n" +
				"    \"idsc\" : \"https://w3id.org/idsa/code/\"\n" +
				"  },\n" +
				"  \"@type\" : \"ids:ConnectorUpdateMessage\",\n" +
				"  \"@id\" : \"https://w3id.org/idsa/autogen/connectorAvailableMessage/777e9303-a8f1-4f00-b1d0-2910c01b2d53\",\n" +
				"  \"ids:issuerConnector\" : {\n" +
				"    \"@id\" : \"http://iais.fraunhofer.de/connectorIssuer\"\n" +
				"  },\n" +
				"  \"ids:modelVersion\" : \"" + imVersion + "\",\n" +
				"  \"ids:issued\" : {\n" +
				"      \"@value\" : \"2020-03-18T12:45:11.682Z\",\n" +
				"      \"@type\" : \"http://www.w3.org/2001/XMLSchema#dateTimeStamp\"\n" +
				"    },\n" +
				"  \"ids:securityToken\" : {\n" +
				"    \"@type\" : \"ids:DynamicAttributeToken\",\n" +
				"    \"@id\" : \"https://ac65d3c7-a09f-44a7-bfbf-813d8bfb5239\",\n" +
				"    \"ids:tokenValue\" : \"eyJhbGandsoon\",\n" +
				"    \"ids:tokenFormat\" : {\n" +
				"      \"@id\" : \"idsc:JWT\"\n" +
				"    }\n" +
				"  },\n" +
				"  \"ids:senderAgent\" : {\"@id\" : \"https://www.iais.fraunhofer.de\" },\n" +
				"  \"ids:affectedConnector\" : {\"@id\" : \"https://broker.ids.isst.fraunhofer.de/\" }\n" +
				"}";
		String jsonld2 = "{\n" +
				"  \"@context\" : {\n" +
				"    \"ids\" : \"https://w3id.org/idsa/core/\",\n" +
				"    \"idsc\" : \"https://w3id.org/idsa/code/\"  },\n" +
				"  \"@type\" : \"ids:ConnectorUpdateMessage\",\n" +
				"  \"@id\" : \"https://w3id.org/idsa/autogen/connectorAvailableMessage/777e9303-a8f1-4f00-b1d0-2910c01b2d53\",\n" +
				"  \"ids:issuerConnector\" : {\n" +
				"    \"@id\" : \"http://iais.fraunhofer.de/connectorIssuer\"\n" +
				"  },\n" +
				"  \"ids:modelVersion\" : \"" + imVersion + "\",\n" +
				"  \"ids:issued\" : {\n" +
				"      \"@value\" : \"2020-03-18T12:45:11.682Z\",\n" +
				"      \"@type\" : \"http://www.w3.org/2001/XMLSchema#dateTimeStamp\"\n" +
				"    },\n" +
				"  \"ids:securityToken\" : {\n" +
				"    \"@type\" : \"ids:DynamicAttributeToken\",\n" +
				"    \"@id\" : \"https://ac65d3c7-a09f-44a7-bfbf-813d8bfb5239\",\n" +
				"    \"ids:tokenValue\" : \"eyJhbGandsoon\",\n" +
				"    \"ids:tokenFormat\" : {\n" +
				"      \"@id\" : \"idsc:JWT\"\n" +
				"    }\n" +
				"  },\n" +
				"  \"ids:senderAgent\" : {\"@id\" : \"https://www.iais.fraunhofer.de\" },\n" +
				"  \"ids:affectedConnector\" : {\"@id\" : \"https://broker.ids.isst.fraunhofer.de/\" }\n" +
				"}";
		/*	String jsonld3 = "{\r\n" + 
				"  \"@context\" : {\r\n" + 
				"    \"ids\" : \"https://w3id.org/idsa/core/\"\r\n" + 
				"  },\r\n" + 
				"  \"@type\" : \"ids:ConnectorAvailableMessage\",\r\n" + 
				"  \"@id\" : \"https://w3id.org/idsa/autogen/connectorAvailableMessage/777e9303-a8f1-4f00-b1d0-2910c01b2d53\",\r\n" + 
				"  \"ids:issuerConnector\" : {\r\n" + 
				"    \"@id\" : \"http://iais.fraunhofer.de/connectorIssuer\"\r\n" + 
				"  },\r\n" + 
				"  \"ids:modelVersion\" : \"2.0.0\",\r\n" + 
				"  \"ids:issued\" : {\r\n" + 
				"    \"@value\" : \"2020-03-31T03:03:03.003+03:00\"\r\n" + 
				"  }" +
				"}"; */ // TODO ... "ids:issued" : {"@value" : "2020-03-31T03:03:03.003+03:00"} does not work
		String jsonld4 = "{\r\n" + 
				"  \"@context\" : {\r\n" + 
				"    \"ids\" : \"https://w3id.org/idsa/core/\",\r\n" +
				"    \"idsc\" : \"https://w3id.org/idsa/code/\"" +
				"  },\r\n" + 
				"  \"@type\" : \"ids:ConnectorUpdateMessage\",\r\n" +
				"  \"@id\" : \"https://w3id.org/idsa/autogen/connectorAvailableMessage/777e9303-a8f1-4f00-b1d0-2910c01b2d53\",\r\n" + 
				"  \"ids:issuerConnector\" : {\r\n" + 
				"    \"@id\" : \"http://iais.fraunhofer.de/connectorIssuer\"\r\n" + 
				"  },\r\n" + 
				"  \"ids:modelVersion\" : \"" + imVersion + "\",\r\n" +
				"  \"ids:issued\" : {\r\n" + 
				"    \"@value\" : \"2020-03-31T04:04:04.004+04:00\",\r\n" + 
				"    \"@type\" : \"http://www.w3.org/2001/XMLSchema#dateTimeStamp\"\r\n" + 
				"  }," +
				"  \"ids:securityToken\" : {\n" +
				"    \"@type\" : \"ids:DynamicAttributeToken\",\n" +
				"    \"@id\" : \"https://ac65d3c7-a09f-44a7-bfbf-813d8bfb5239\",\n" +
				"    \"ids:tokenValue\" : \"eyJhbGandsoon\",\n" +
				"    \"ids:tokenFormat\" : {\n" +
				"      \"@id\" : \"idsc:JWT\"\n" +
				"    }\r\n" +
				"  },\r\n" +
				"  \"ids:senderAgent\" : \"https://www.iais.fraunhofer.de\",\r\n" +
				"  \"ids:affectedConnector\" : \"https://broker.ids.isst.fraunhofer.de/\"\r\n" +
				"}";


		ConnectorUpdateMessage basicInstance = new ConnectorUpdateMessageBuilder()
				._issued_(now)
				._modelVersion_(imVersion)
				._issuerConnector_(URI.create("http://iais.fraunhofer.de/connectorIssuer"))
				._securityToken_(new DynamicAttributeTokenBuilder()._tokenFormat_(TokenFormat.JWT)._tokenValue_("test1234").build())
				._senderAgent_(URI.create("http://someSenderAgent.org/"))
				._affectedConnector_(URI.create("http://iais.fraunhofer.de/connectorIssuer"))
				.build();
		String jsonld5 = serializer.serialize(basicInstance);

		String[] jsonlds = new String[]{ jsonld1, jsonld2, jsonld4, jsonld5 };


		for (String jsonld : jsonlds) {
			// validate JSON-LD
			Model model = ModelFactory.createDefaultModel();
			readToModel(model, jsonld);

			// parse JSON-LD
			ConnectorUpdateMessage msg = serializer.deserialize(jsonld, ConnectorUpdateMessage.class);
			serializer.serialize(msg);
		}
	}

	@Test
	public void SerializeConfigModelTest() throws IOException {
		String configModelAsString = SerializerUtil.readResourceToString("ConfigModel.json");
		ObjectMapper mapper = new ObjectMapper();
		Serializer serializer = new Serializer();
		ConfigurationModel configurationModel = mapper.readValue(configModelAsString, ConfigurationModelImpl.class);
		for(int i = 0; i < 10; i++) //TODO: Some bug appeared (missing context) when the serializer was used in a multi threaded approach. Do this in multiple threads
		{
			String reSerialized = serializer.serialize(configurationModel);
			Assert.assertTrue(reSerialized.contains("@context"));
		}
	}

	/**
	 * This test makes sure that, in case one violates the "at most once" constraint, an appropriate exception is thrown
	 * @throws IOException thrown in case the loading of the resource fails
	 */
	@Test
	public void SerializeConnectorMultipleResources() throws IOException {
		String connectorAsString = SerializerUtil.readResourceToString("Connector4.jsonld");
		try {
			//This connector has two resources, which link to the same representation (same URI). However, the representation has different mediaTypes
			//In summary, one ends up with one representation with two mediaTypes, which is illegal and throws an Exception
			Connector baseConnector = new Serializer().deserialize(connectorAsString, Connector.class);
		}
		catch (IOException e)
		{
			//Make sure that the exception indicates that the mediaType is the violating constraint
			Assert.assertTrue(e.getMessage().contains("has multiple values for mediaType"));
			return;
		}
		Assert.fail();
	}




}
