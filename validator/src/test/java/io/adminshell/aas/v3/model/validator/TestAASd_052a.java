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

import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.KeyType;
import io.adminshell.aas.v3.model.Property;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.impl.DefaultKey;
import io.adminshell.aas.v3.model.impl.DefaultProperty;
import io.adminshell.aas.v3.model.impl.DefaultReference;

/**
 * Tests the following constraint:
 * <p>
 * <i> If the semanticId of a Property references a ConceptDescription then the
 * ConceptDescription/category shall be one of following values: VALUE,
 * PROPERTY. </i>
 * </p>
 * 
 * @author schnicke
 *
 */
public class TestAASd_052a {
	@Test
	public void correctCategories() throws ValidationException {
		String conceptDescriptionPropertyId = "conceptDescriptionProperty";
		String conceptDescriptionValueId = "conceptDescriptionValue";

		ConceptDescription correctPropertyCD = ConstraintTestHelper
				.createConceptDescription("idShort1", conceptDescriptionPropertyId, "PROPERTY");

		ConceptDescription correctValueCD = ConstraintTestHelper.createConceptDescription("idShort2",
				conceptDescriptionValueId, "VALUE");

		Submodel sm = ConstraintTestHelper
				.createSubmodel(Arrays.asList(createProperty("submodelElementIdShort1", conceptDescriptionValueId),
						createProperty("submodelElementIdShort2", conceptDescriptionPropertyId)));

		AssetAdministrationShellEnvironment correctEnv = ConstraintTestHelper.createEnvironment(sm, Arrays.asList(correctValueCD, correctPropertyCD,
						ConstraintTestHelper.getIrrelevantConceptDescription()));

		ShaclValidator.getInstance().validate(correctEnv);
	}

	@Test
	public void wrongCategory() {
		String conceptDescriptionWrongId = "conceptDescriptionWrong";

		ConceptDescription wrongCD = ConstraintTestHelper.createConceptDescription("idShort1", conceptDescriptionWrongId,
				"COLLECTION");

		Submodel sm = ConstraintTestHelper
				.createSubmodel(Arrays.asList(createProperty("submodelElementIdShort", conceptDescriptionWrongId)));
		
		AssetAdministrationShellEnvironment wrongEnv = ConstraintTestHelper.createEnvironment(sm,
				Arrays.asList(wrongCD, ConstraintTestHelper.getIrrelevantConceptDescription()));

		try {
			ShaclValidator.getInstance().validate(wrongEnv);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"If the semanticId of a Property references a ConceptDescription then the ConceptDescription/category shall be one of following values: VALUE, PROPERTY."));
		}
	}

	private Property createProperty(String idShort, String conceptDescriptionId) {
		return new DefaultProperty.Builder()
				.idShort(idShort)
				.valueType("string")
				.value("TestValue")
				.semanticId(new DefaultReference.Builder()
						.key(new DefaultKey.Builder()
								.idType(KeyType.CUSTOM)
								.value(conceptDescriptionId)
								.type(KeyElements.CONCEPT_DESCRIPTION)
								.build())
						.build())
				.build();
	}


}
