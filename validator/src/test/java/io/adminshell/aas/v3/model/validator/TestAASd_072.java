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

import java.io.IOException;
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
 * <i>For a ConceptDescription with category DOCUMENT using data specification
 * template IEC61360
 * (http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0)
 * - DataSpecificationIEC61360/dataType shall be one of the following values:
 * STRING or URL.</i>
 * </p>
 * 
 * @author schnicke
 *
 */
public class TestAASd_072 {

	@Test
	public void correctDataTypeURL() throws ValidationException, IOException {
		ConceptDescription correctURLCD = createConceptDescription(DataTypeIEC61360.URL);

		ShaclValidator.getInstance().validate(correctURLCD);
	}

	@Test
	public void wrongDataType() {
		ConceptDescription wrongCD = createConceptDescription(DataTypeIEC61360.DATE);

		try {
			ShaclValidator.getInstance().validate(wrongCD);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"For a ConceptDescription with category DOCUMENT using data specification template IEC61360 (http://admin-shell.io/DataSpecificationTemplates/DataSpecificationIEC61360/2/0) - DataSpecificationIEC61360/dataType shall be one of the following values: STRING or URL."));
		}
	}

	@Test
	public void correctDataTypeString() throws ValidationException {
		ConceptDescription correctStringCD = createConceptDescription(DataTypeIEC61360.STRING);


		ShaclValidator.getInstance().validate(correctStringCD);
	}

	private ConceptDescription createConceptDescription(DataTypeIEC61360 dataType) {
		ConceptDescription correctURLCD = ConstraintTestHelper.createConceptDescription("idShort1",
				"conceptDescriptionURL", "DOCUMENT");

		DataSpecificationIEC61360 urlDataTypeDS = new DefaultDataSpecificationIEC61360.Builder()
				.preferredName(new LangString("ds", "en")).definition(new LangString("some english definition", "EN"))
				.dataType(dataType).build();

		EmbeddedDataSpecification urlDataTypeEDS = new DefaultEmbeddedDataSpecification.Builder()
				.dataSpecificationContent(urlDataTypeDS).dataSpecification(new DefaultReference.Builder()
						.key(new DefaultKey.Builder().idType(KeyType.CUSTOM).value("foo_key").build()).build())
				.build();

		correctURLCD.setEmbeddedDataSpecifications(Arrays.asList(urlDataTypeEDS));
		return correctURLCD;
	}
}
