/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.adminshell.aas.v3.model.validator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.jena.riot.RDFLanguages;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.dataformat.rdf.Serializer;
import io.adminshell.aas.v3.model.AdministrativeInformation;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetKind;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.DataSpecificationIEC61360;
import io.adminshell.aas.v3.model.DataTypeIEC61360;
import io.adminshell.aas.v3.model.EmbeddedDataSpecification;
import io.adminshell.aas.v3.model.EntityType;
import io.adminshell.aas.v3.model.IdentifierKeyValuePair;
import io.adminshell.aas.v3.model.IdentifierType;
import io.adminshell.aas.v3.model.KeyType;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.impl.DefaultAdministrativeInformation;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShell;
import io.adminshell.aas.v3.model.impl.DefaultAssetInformation;
import io.adminshell.aas.v3.model.impl.DefaultConceptDescription;
import io.adminshell.aas.v3.model.impl.DefaultDataSpecificationIEC61360;
import io.adminshell.aas.v3.model.impl.DefaultEmbeddedDataSpecification;
import io.adminshell.aas.v3.model.impl.DefaultEntity;
import io.adminshell.aas.v3.model.impl.DefaultIdentifier;
import io.adminshell.aas.v3.model.impl.DefaultIdentifierKeyValuePair;
import io.adminshell.aas.v3.model.impl.DefaultKey;
import io.adminshell.aas.v3.model.impl.DefaultReference;





public class ShaclValidatorTest {

    Logger logger = LoggerFactory.getLogger(getClass());


    @Test
    public void validationTest() throws ValidationException {
        logger.info("Starting initialization of validator");
        ShaclValidator validator = ShaclValidator.getInstance();
        logger.info("Initialization complete. Building AAS Java object");

        AssetAdministrationShell aas = new DefaultAssetAdministrationShell.Builder()
                .description(new LangString("TestDescription"))
                .identification(new DefaultIdentifier.Builder().identifier("test1234").idType(IdentifierType.CUSTOM).build())
                .build();

        logger.info("Done. Starting validation.");
        ValidationException exception = null;
        try {
            validator.validate(aas);
        }
        catch (ValidationException e)
        {
            exception = e;
        }
        logger.info("Validation complete");
        Assert.assertNotNull(exception);
        Assert.assertTrue(exception.getMessage().contains("Exactly one <id>idShort</id> is required"));

        aas.setIdShort("test");


        aas.setAssetInformation(new DefaultAssetInformation.Builder()
                .assetKind(AssetKind.INSTANCE)
                .build());
        validator.validate(aas);
    }
    
	@Test
	public void testAdmistrativeInformation_AASd_005()
			throws ValidationException, IOException, DeserializationException {

		// Administrative Information without Version should fail the valdiation
		AdministrativeInformation admInfo = new DefaultAdministrativeInformation.Builder()
				.revision("foo")
				.build();

		try {
			ShaclValidator.getInstance().validate(admInfo);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith("A revision requires a version."));
		}

		// Administrative Information with both version and revision
		admInfo.setVersion("foo_bar");
		ShaclValidator.getInstance().validate(admInfo);

		logger.info("[AdminstrativeInformation] AASd-005 constraint validation completed");

	}
	
	@Test
	public void testEntity_AASd_014() throws ValidationException, IOException
	{

		DefaultEntity entity = new DefaultEntity();
		entity.setIdShort("entityIdShort");
		entity.setEntityType(EntityType.SELF_MANAGED_ENTITY);

		// Pass case 1 : With only EntityType = SelfManagedEntity and GlobalAssetId set
		Reference globAssetId = new DefaultReference.Builder()
				.key(new DefaultKey.Builder()
						.idType(KeyType.ID_SHORT)
						.value("foo")
						.build())
				.build();

		entity.setGlobalAssetId(globAssetId);
		ShaclValidator.getInstance().validate(entity);

		// Pass case 2 : With only EntityType = SelfManagedEntity and GlobalAssetId and
		// specificAssetId set
		IdentifierKeyValuePair idKeyPair = new
		DefaultIdentifierKeyValuePair.Builder()
			.externalSubjectId(globAssetId)
			.value("foo").key("foo_key").build();
		
		entity.setSpecificAssetId(idKeyPair);
		//ShaclValidator.getInstance().validate(entity);


		// Fail case 1: gobalAssetId and specificAssetId missing
		entity = new DefaultEntity();
		entity.setIdShort("entityIdShort");
		entity.setEntityType(EntityType.CO_MANAGED_ENTITY);
		// Pass case 3 : With EntityType = CoManagedEntity
		ShaclValidator.getInstance().validate(entity);

		// Fail case 1: With EntityType = CoManagedEntity && GobalAssetId set
		entity.setGlobalAssetId(globAssetId);
		try {
			ShaclValidator.getInstance().validate(entity);
			fail();
		} catch (ValidationException e) {

			assertTrue(e
					.getMessage()
					.contains(
							"Either the attribute globalAssetId or specificAssetId of an Entity must be set if "
							+ "Entity/entityType is set to “SelfManagedEntity”. They are not existing otherwise."));
		}

		// Fail case 2: With EntityType = CoManagedEntity && GobalAssetId set
		entity.setGlobalAssetId(globAssetId);
		entity.setSpecificAssetId(idKeyPair);
		try {
			ShaclValidator.getInstance().validate(entity);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage()
					.contains("Either the attribute globalAssetId or specificAssetId of an Entity must be set if "
							+ "Entity/entityType is set to “SelfManagedEntity”. They are not existing otherwise."));
		}

		logger.info("[Enity] AASd-005 constraint validation completed");
	}
	
	@Test
	public void testConceptDescription_AASd_051() throws ValidationException {
		
		List<String> categories = Arrays.asList("VALUE", "PROPERTY", "REFERENCE", "DOCUMENT",
				"CAPABILITY", "RELATIONSHIP", "COLLECTION", "FUNCTION", "EVENT",
				"ENTITY", "APPLICATION_CLASS", "QUALIFIER", "VIEW", "FAILURE");

		DataSpecificationIEC61360 ds = new DefaultDataSpecificationIEC61360.Builder()
				.dataType(DataTypeIEC61360.STRING)
				.definition(new LangString("some english definition", "EN"))
				.preferredName(new LangString("ds"))
				.build();
		
		EmbeddedDataSpecification eds = new DefaultEmbeddedDataSpecification.Builder()
				.dataSpecificationContent(ds)
				.dataSpecification(
						new DefaultReference.Builder()
						.key(new DefaultKey.Builder()
								.idType(KeyType.CUSTOM)
								.value("foo_key")
								.build())
						.build()
				).build();

		ConceptDescription cd = new DefaultConceptDescription.Builder()
				.description(new LangString("TestDescription"))
				.identification(new DefaultIdentifier.Builder()
						.identifier("some_identifer")
						.idType(IdentifierType.CUSTOM)
						.build())
				.idShort("someidshort")
				.build();
		cd.setEmbeddedDataSpecifications(Arrays.asList(eds));
		
		categories.forEach((String category) -> {
			// Pass cases - only allowed category names
			if (!category.equals("FAILURE")) {
				cd.setCategory(category);

				try {
					ShaclValidator.getInstance().validate(cd);
				} catch (ValidationException e) {
					System.out.println(category);
					 System.out.println(e.getMessage());
					
					throw new RuntimeException(e);
				}

			} else {
				// Fail case - When category == "FAILURE"
				cd.setCategory("FAILURE");
				try {
					ShaclValidator.getInstance().validate(cd);
					fail();
				} catch (ValidationException e) {
					assertTrue(e.getMessage().endsWith(
							"AASd-051 - A ConceptDescription"
									+ " shall have one of the following categories: VALUE, PROPERTY,"
									+ " REFERENCE, DOCUMENT, CAPABILITY, RELATIONSHIP, COLLECTION,"
									+ " FUNCTION, EVENT, ENTITY, APPLICATION_CLASS, QUALIFIER, VIEW "));
				}
			}
		});
		logger.info("[ConceptDescription] AASd-051 constraint validation completed");
	}

	@Test
	public void testConceptDescription_AASd_070_073() {
		List<String> categories = Arrays.asList("VALUE", "PROPERTY", "FAILURE");

		ConceptDescription cd = new DefaultConceptDescription.Builder()
				.description(new LangString("TestDescription"))
				.identification(new DefaultIdentifier.Builder()
						.identifier("some_identifer")
						.idType(IdentifierType.CUSTOM)
						.build())
				.idShort("someidshort")
				.build();

		DataSpecificationIEC61360 ds = new DefaultDataSpecificationIEC61360.Builder()
				.preferredName(new LangString("ds"))
				.definition(new LangString("some english definition", "EN"))
				.build();

		EmbeddedDataSpecification eds = new DefaultEmbeddedDataSpecification.Builder().dataSpecificationContent(ds)
				.dataSpecification(new DefaultReference.Builder()
						.key(new DefaultKey.Builder()
								.idType(KeyType.CUSTOM)
								.value("foo_key")
								.build())
						.build())
				.build();
		cd.setEmbeddedDataSpecifications(Arrays.asList(eds));

		categories.forEach((String category) -> {

			if (!category.equals("FAILURE")) {
				cd.setCategory(category);
				// Fail case
				try {
					ShaclValidator.getInstance().validate(cd);
				} catch (ValidationException e) {
					// TODO sh:message in configured in contraint shape is not thrown in validation,
					// check it
					 assertTrue(e.getMessage()
								.endsWith(
										"(DataSpecificationIEC61360.dataType): Exactly one <i>dataType</i> is required."));

				}
			} else {
				// Pass case
				ds.setDataType(DataTypeIEC61360.BOOLEAN);
				try {
					ShaclValidator.getInstance().validate(cd);
				} catch (ValidationException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
	
	@Test
	public void testConceptDescription_AASd_071() throws ValidationException, IOException {
		ConceptDescription cd = new DefaultConceptDescription.Builder()
				.description(new LangString("TestDescription"))
				.identification(new DefaultIdentifier.Builder()
						.identifier("some_identifer")
						.idType(IdentifierType.CUSTOM)
						.build())
				.category("REFERENCE")
				.idShort("someidshort")
				.build();

		DataSpecificationIEC61360 ds = new DefaultDataSpecificationIEC61360.Builder()
				.preferredName(new LangString("ds"))
				.dataType(DataTypeIEC61360.BOOLEAN)
				.definition(new LangString("some english definition", "EN"))
				.build();

		EmbeddedDataSpecification eds = new DefaultEmbeddedDataSpecification.Builder()
				.dataSpecificationContent(ds)
				.dataSpecification(new DefaultReference.Builder()
						.key(new DefaultKey.Builder()
								.idType(KeyType.CUSTOM)
								.value("foo_key")
								.build())
						.build())
				.build();

		cd.setEmbeddedDataSpecifications(Arrays.asList(eds));
		
		// Fail case : when DataSpecificationIEC61360.dataType != STRING
		try {
			ShaclValidator.getInstance().validate(cd);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"(ConceptDataSpecificationIEC61360Reference) : AASd-071 - For a ConceptDescription "
							+ "with category REFERENCE using data specification template "
							+ "IEC61360 (http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0) - "
							+ "DataSpecificationIEC61360/dataType is STRING by default."));
		}

		// Pass Case 1 : when DataSpecificationIEC61360.dataType == STRING
		ds.setDataType(DataTypeIEC61360.STRING);
		cd.setEmbeddedDataSpecifications(Arrays.asList(eds));
		ShaclValidator.getInstance().validate(cd);
	}

	@Test
	public void testConceptDescription_AASd_072() throws ValidationException, IOException {
		ConceptDescription cd = new DefaultConceptDescription.Builder()
				.description(new LangString("TestDescription"))
				.identification(new DefaultIdentifier.Builder()
						.identifier("some_identifer")
						.idType(IdentifierType.CUSTOM)
						.build())
				.idShort("someidshort")
				.category("DOCUMENT")
				.build();

		DataSpecificationIEC61360 ds = new DefaultDataSpecificationIEC61360.Builder()
				.preferredName(new LangString("ds"))
				.dataType(DataTypeIEC61360.BOOLEAN)
				.definition(new LangString("some english definition", "EN"))
				.build();
		
		EmbeddedDataSpecification eds = new DefaultEmbeddedDataSpecification.Builder()
				.dataSpecificationContent(ds)
				.dataSpecification(new DefaultReference.Builder()
						.key(new DefaultKey.Builder()
								.idType(KeyType.CUSTOM)
								.value("foo_key")
								.build())
						.build())
				.build();

		cd.setEmbeddedDataSpecifications(Arrays.asList(eds));

		// Fail case : when DataSpecificationIEC61360.dataType != STRING
		try {
			ShaclValidator.getInstance().validate(cd);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"(ConceptDataSpecificationIEC61360Document) : AASd-072 - For a ConceptDescription"
							+ " with category DOCUMENT using data specification template IEC61360"
							+ " (http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0) "
							+ "- DataSpecificationIEC61360/dataType shall be one of the following values: STRING or URL."));
		}

		// Pass Case 1 : when DataSpecificationIEC61360.dataType == STRING
		ds.setDataType(DataTypeIEC61360.STRING);
		cd.setEmbeddedDataSpecifications(Arrays.asList(eds));
		ShaclValidator.getInstance().validate(cd);

		// Pass Case 2 : when DataSpecificationIEC61360.dataType == URL
		ds.setDataType(DataTypeIEC61360.URL);
		cd.setEmbeddedDataSpecifications(Arrays.asList(eds));
		ShaclValidator.getInstance().validate(cd);

	}

	@Test
	public void testConceptDescription_AASd_074_76() throws ValidationException, IOException {
		ConceptDescription cd = new DefaultConceptDescription.Builder()
				.description(new LangString("TestDescription"))
				.identification(new DefaultIdentifier.Builder()
						.identifier("some_identifer")
						.idType(IdentifierType.CUSTOM)
						.build())
				.idShort("someidshort")
				.category("VALUE")
				.build();

		DataSpecificationIEC61360 ds = new DefaultDataSpecificationIEC61360.Builder()
				.preferredName(new LangString("ds"))
				.dataType(DataTypeIEC61360.STRING)
				.definition(new LangString("Deutsche Beschreibung", "DE"))
				.build();
		
		EmbeddedDataSpecification eds = new DefaultEmbeddedDataSpecification.Builder()
				.dataSpecificationContent(ds)
				.dataSpecification(new DefaultReference.Builder()
						.key(new DefaultKey.Builder()
								.idType(KeyType.CUSTOM)
								.value("foo_key")
								.build())
						.build())
				.build();

		// ds.setDefinitions(definitions);
		cd.setEmbeddedDataSpecifications(Arrays.asList(eds));

		try {
			ShaclValidator.getInstance().validate(cd);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage()
					.endsWith("(ConceptDataSpecificationIEC61360Value2) : AASd-074 AASd-076 - "
							+ "For all ConceptDescriptions using data specification template IEC61360 "
							+ "(http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0) "
							+ "at least a preferred name in English shall be defined."));
		}

		ds.setDefinitions(Arrays.asList(new LangString("some definition", "EN")));
		eds = new DefaultEmbeddedDataSpecification.Builder()
				.dataSpecificationContent(ds)
				.dataSpecification(new DefaultReference.Builder()
						.key(new DefaultKey.Builder()
								.idType(KeyType.CUSTOM)
								.value("foo_key")
								.build())
						.build())
				.build();
		cd.setEmbeddedDataSpecifications(Arrays.asList(eds));
		

		ShaclValidator.getInstance().validate(cd);

	}

	private void printObject(Object obj) throws IOException {
		Serializer serializer = new Serializer();
		System.out.println("\n...................................................................\n");
		System.out.println(serializer.serialize(obj, RDFLanguages.TURTLE, new
		 HashMap<>()));
		System.out.println("\n...................................................................\n");
	}
}
