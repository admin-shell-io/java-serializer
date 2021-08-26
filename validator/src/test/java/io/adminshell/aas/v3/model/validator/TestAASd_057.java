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
import io.adminshell.aas.v3.model.Blob;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.File;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.KeyType;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.impl.DefaultBlob;
import io.adminshell.aas.v3.model.impl.DefaultFile;
import io.adminshell.aas.v3.model.impl.DefaultKey;
import io.adminshell.aas.v3.model.impl.DefaultReference;

/**
 * Tests the following constraint:
 * <p>
 * <i> The semanticId of a File or Blob submodel element shall only reference a
 * ConceptDescription with the category DOCUMENT</i>
 * </p>
 * 
 * @author schnicke
 *
 */
public class TestAASd_057 {

	private final static String ERRORMSG = "The semanticId of a File or Blob submodel element shall only reference a ConceptDescription with the category DOCUMENT.";

	@Test
	public void correctCategoryFile() throws ValidationException {
		String conceptDescriptionId = "conceptDescription";

		ConceptDescription correctCD = ConstraintTestHelper.createConceptDescription("idShort1",
				conceptDescriptionId, "DOCUMENT");

		Submodel sm = ConstraintTestHelper
				.createSubmodel(
						Arrays.asList(createFile("submodelElementIdShort", conceptDescriptionId)));

		AssetAdministrationShellEnvironment correctEnv = ConstraintTestHelper.createEnvironment(sm,
				Arrays.asList(correctCD,
						ConstraintTestHelper.getIrrelevantConceptDescription()));

		ShaclValidator.getInstance().validate(correctEnv);
	}

	@Test
	public void wrongCategoryFile() {
		String conceptDescriptionWrong = "conceptDescriptionWrong";

		ConceptDescription wrongCD = ConstraintTestHelper.createConceptDescription("idShort1", conceptDescriptionWrong,
				"COLLECTION");

		Submodel sm = ConstraintTestHelper
				.createSubmodel(Arrays.asList(createFile("submodelElementIdShort", conceptDescriptionWrong)));
		
		AssetAdministrationShellEnvironment wrongEnv = ConstraintTestHelper.createEnvironment(sm,
				Arrays.asList(wrongCD, ConstraintTestHelper.getIrrelevantConceptDescription()));
		try {
			ShaclValidator.getInstance().validate(wrongEnv);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(ERRORMSG));
		}
	}
	
	@Test
	public void correctCategoryBlob() throws ValidationException {
		String conceptDescriptionId = "conceptDescription";

		ConceptDescription correctCD = ConstraintTestHelper
				.createConceptDescription("idShort1", conceptDescriptionId, "DOCUMENT");

		Submodel sm = ConstraintTestHelper
				.createSubmodel(
						Arrays.asList(createBlob("submodelElementIdShort", conceptDescriptionId)));

		AssetAdministrationShellEnvironment correctEnv = ConstraintTestHelper.createEnvironment(sm,
				Arrays.asList(correctCD,
						ConstraintTestHelper.getIrrelevantConceptDescription()));

		ShaclValidator.getInstance().validate(correctEnv);
	}

	@Test
	public void wrongCategoryBlob() {
		String conceptDescriptionWrongId = "conceptDescriptionWrong";

		ConceptDescription wrongCD = ConstraintTestHelper.createConceptDescription("idShort1", conceptDescriptionWrongId,
				"COLLECTION");

		Submodel sm = ConstraintTestHelper
				.createSubmodel(Arrays.asList(createBlob("submodelElementIdShort", conceptDescriptionWrongId)));
		
		AssetAdministrationShellEnvironment wrongEnv = ConstraintTestHelper.createEnvironment(sm,
				Arrays.asList(wrongCD, ConstraintTestHelper.getIrrelevantConceptDescription()));
		try {
			ShaclValidator.getInstance().validate(wrongEnv);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(ERRORMSG));
		}
	}

	private File createFile(String idShort, String conceptDescriptionId) {
		return new DefaultFile.Builder()
				.idShort(idShort)
				.mimeType("application/json").value("test.json")
				.semanticId(new DefaultReference.Builder()
						.key(new DefaultKey.Builder()
								.idType(KeyType.CUSTOM)
								.value(conceptDescriptionId)
								.type(KeyElements.CONCEPT_DESCRIPTION)
								.build())
						.build())
				.build();
	}
	
	private Blob createBlob(String idShort, String conceptDescriptionId) {
		return new DefaultBlob.Builder()
				.idShort(idShort)
				.mimeType("application/json")
				.value(new byte[] { 0, 1, 2 })
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
