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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.DataSpecificationIEC61360;
import io.adminshell.aas.v3.model.DataTypeIEC61360;
import io.adminshell.aas.v3.model.EmbeddedDataSpecification;
import io.adminshell.aas.v3.model.KeyType;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.impl.DefaultDataSpecificationIEC61360;
import io.adminshell.aas.v3.model.impl.DefaultEmbeddedDataSpecification;
import io.adminshell.aas.v3.model.impl.DefaultKey;
import io.adminshell.aas.v3.model.impl.DefaultReference;

/**
 * Tests the following constraint:
 * <p>
 * <i> For all ConceptDescriptions except for ConceptDescriptions of category
 * VALUE using data specification template IEC61360
 * (http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0)
 * - DataSpecificationIEC61360/definition is mandatory and shall be defined at
 * least in English. </i>
 * </p>
 * 
 * @author schnicke
 *
 */
public class TestAASd_074 {

	@Test
	public void conceptDescriptionEnglishDefinition() throws ValidationException {
		LangString definition = new LangString("some english definition", "EN");
		ConceptDescription cd = createConceptDescription(definition);

		ShaclValidator.getInstance().validate(cd);
	}

	@Test
	public void conceptDescriptionNoEnglishDefinition() {
		LangString definition = new LangString("deutsch", "DE");
		ConceptDescription cd = createConceptDescription(definition);

		try {
			ShaclValidator.getInstance().validate(cd);
			fail();
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
			assertTrue(e.getMessage().endsWith(
					"For all ConceptDescriptions except for ConceptDescriptions of category VALUE using data specification template IEC61360 (http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0) - DataSpecificationIEC61360/definition is mandatory and shall be defined at least in English."));
		}
	}

	@Test
	public void conceptDescriptionValueCategoryGermanDefinition() throws ValidationException {
		LangString definition = new LangString("deutsch", "DE");
		ConceptDescription cd = createConceptDescription(definition);
		cd.setCategory("VALUE");

		ShaclValidator.getInstance().validate(cd);
	}

	private ConceptDescription createConceptDescription(LangString definition) {
		ConceptDescription cd = ConstraintTestHelper.createConceptDescription("idShort1", "id",
				"QUALIFIER");

		DataSpecificationIEC61360 urlDataTypeDS = new DefaultDataSpecificationIEC61360.Builder()
				.preferredName(new LangString("ds", "en"))
				.definition(definition)
				.dataType(DataTypeIEC61360.URL)
				.build();
		
		EmbeddedDataSpecification urlDataTypeEDS = new DefaultEmbeddedDataSpecification.Builder()
				.dataSpecificationContent(urlDataTypeDS)
				.dataSpecification(new DefaultReference.Builder()
						.key(new DefaultKey.Builder()
								.idType(KeyType.CUSTOM)
								.value("foo_key")
								.build())
						.build())
				.build();

		cd.setEmbeddedDataSpecifications(Arrays.asList(urlDataTypeEDS));
		return cd;
	}
}
