/*******************************************************************************
 * Copyright (C) 2021 the Eclipse BaSyx Authors
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/

package io.adminshell.aas.v3.model.validator;

import io.adminshell.aas.v3.model.*;
import io.adminshell.aas.v3.model.impl.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the following constraint:
 * <p>
 * <i> If the semanticId of a MultiLanguageProperty references a ConceptDescription then
 * DataSpecificationIEC61360/dataType shall be STRING_TRANSLATABLE. </i>
 * </p>
 * 
 * @author bader, chang
 *
 */
public class TestAASd_067 {
	@Test
	public void wrongConceptDescriptionDatatype() throws ValidationException {

		DataSpecificationIEC61360 ds = new DefaultDataSpecificationIEC61360.Builder()
				.dataType(DataTypeIEC61360.DATE) // This should be DataTypeIEC61360.STRING_TRANSLATABLE
				.preferredName(new LangString("Wrong Data Specification", "en"))
				.definition(new LangString("A definition of a Date in English.", "en"))
				.build();

		EmbeddedDataSpecification embeddedDataSpecification = new DefaultEmbeddedDataSpecification.Builder()
				.dataSpecificationContent(ds)
				.dataSpecification( ConstraintTestHelper.createDummyReference() )
				.build();

		ConceptDescription cd = ConstraintTestHelper.createConceptDescription("Concept-Description", "http://example.org/MultilanguageCD", "constant");
		cd.setCategory("PROPERTY");
		cd.setEmbeddedDataSpecifications(new ArrayList<>(){{ add(embeddedDataSpecification) ; }} );

		Reference reference = ConstraintTestHelper.createDummyReference();
		Key key = reference.getKeys().get(0);
		key.setIdType(KeyType.IRI);
		key.setType(KeyElements.CONCEPT_DESCRIPTION);
		key.setValue(cd.getIdentification().getIdentifier());

		MultiLanguageProperty property = new DefaultMultiLanguageProperty.Builder()
				.idShort("idShort")
				.semanticId(reference)
				.build();

		Submodel submodel = new DefaultSubmodel.Builder()
				.idShort("submodel_idShort")
				.identification(new DefaultIdentifier.Builder().identifier("http://example.org/TestSubmodel").idType(IdentifierType.IRI).build())
				.submodelElements(new ArrayList<>() {{add(property);}})
				.build();

		AssetAdministrationShellEnvironment wrongAssetAdministrationShellEnvironment = ConstraintTestHelper
				.createEnvironment(
						submodel,
						new ArrayList<>() {{add(cd);}}
				);

		try {
			ShaclValidator.getInstance().validate(wrongAssetAdministrationShellEnvironment);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"If the semanticId of a MultiLanguageProperty references a ConceptDescription then DataSpecificationIEC61360/dataType shall be STRING_TRANSLATABLE."));
		}


	}

	@Test
	public void correctConceptDescriptionDatatype() throws ValidationException {


		DataSpecificationIEC61360 ds = new DefaultDataSpecificationIEC61360.Builder()
				.dataType(DataTypeIEC61360.STRING_TRANSLATABLE) // This should be DataTypeIEC61360.STRING_TRANSLATABLE
				.preferredName(new LangString("Data Specification", "en"))
				.definition(new LangString("A definition of a STRING_TRANSLATABLE in English.", "en"))
				.build();

		EmbeddedDataSpecification embeddedDataSpecification = new DefaultEmbeddedDataSpecification.Builder()
				.dataSpecificationContent(ds)
				.dataSpecification( ConstraintTestHelper.createDummyReference() )
				.build();

		ConceptDescription cd = ConstraintTestHelper.createConceptDescription("Concept-Description", "http://example.org/MultilanguageCD", "constant");
		cd.setCategory("PROPERTY");
		cd.setEmbeddedDataSpecifications(new ArrayList<>(){{ add(embeddedDataSpecification) ; }} );

		Reference reference = ConstraintTestHelper.createDummyReference();
		Key key = reference.getKeys().get(0);
		key.setIdType(KeyType.IRI);
		key.setType(KeyElements.CONCEPT_DESCRIPTION);
		key.setValue(cd.getIdentification().getIdentifier());

		MultiLanguageProperty property = new DefaultMultiLanguageProperty.Builder()
				.idShort("idShort")
				.semanticId(reference)
				.build();

		Submodel submodel = new DefaultSubmodel.Builder()
				.idShort("submodel_idShort")
				.identification(new DefaultIdentifier.Builder().identifier("http://example.org/TestSubmodel").idType(IdentifierType.IRI).build())
				.submodelElements(new ArrayList<>() {{add(property);}})
				.build();

		AssetAdministrationShellEnvironment assetAdministrationShellEnvironment = ConstraintTestHelper
				.createEnvironment(
						submodel,
						new ArrayList<>() {{add(cd);}}
				);


		ShaclValidator.getInstance().validate(assetAdministrationShellEnvironment);

	}
}
