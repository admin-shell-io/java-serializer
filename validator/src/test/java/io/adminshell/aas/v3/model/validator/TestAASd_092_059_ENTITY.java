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
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.SubmodelElementCollection;
import io.adminshell.aas.v3.model.impl.DefaultKey;
import io.adminshell.aas.v3.model.impl.DefaultReference;
import io.adminshell.aas.v3.model.impl.DefaultSubmodelElementCollection;

/**
 * Tests the following constraint:
 * <p>
 * <i>If the semanticId of a SubmodelElementCollection with
 * SubmodelElementCollection/allowDuplicates == false references a
 * ConceptDescription then the ConceptDescription/category shall be ENTITY. </i>
 * </p>
 * 
 * Additionally, covers part of AASd-059. Full coverage is achieved in
 * combination with AASd-093
 * 
 * @author schnicke
 *
 */
public class TestAASd_092_059_ENTITY {
	@Test
	public void correctCategoryNoDuplicates() throws ValidationException {
		String conceptDescriptionCollectionId = "conceptDescriptionCollection";

		ConceptDescription correctCategoryCollectionCD = ConstraintTestHelper.createConceptDescription("idShort1",
				conceptDescriptionCollectionId, "ENTITY");

		SubmodelElementCollection noDuplicatesCollection = createSubmodelElementCollection("submodelElementIdShort2",
				conceptDescriptionCollectionId);
		noDuplicatesCollection.setAllowDuplicates(false);

		Submodel sm = ConstraintTestHelper
				.createSubmodel(Arrays.asList(
		));

		AssetAdministrationShellEnvironment correctEnv = ConstraintTestHelper.createEnvironment(sm,
				Arrays.asList(correctCategoryCollectionCD,
						ConstraintTestHelper.getIrrelevantConceptDescription()));

		ShaclValidator.getInstance().validate(correctEnv);
	}

	@Test
	public void wrongCategoryNoDuplicates() {
		String conceptDescriptionCollectionId = "conceptDescriptionCollection";

		ConceptDescription wrongCategoryCollectionCD = ConstraintTestHelper.createConceptDescription("idShort1",
				conceptDescriptionCollectionId, "COLLECTION");

		SubmodelElementCollection noDuplicatesCollection = createSubmodelElementCollection("submodelElementIdShort2",
				conceptDescriptionCollectionId);
		noDuplicatesCollection.setAllowDuplicates(false);

		Submodel sm = ConstraintTestHelper
				.createSubmodel(Arrays.asList(noDuplicatesCollection));

		AssetAdministrationShellEnvironment wrongEnv = ConstraintTestHelper.createEnvironment(sm,
				Arrays.asList(wrongCategoryCollectionCD, ConstraintTestHelper.getIrrelevantConceptDescription()));
		try {
			ShaclValidator.getInstance().validate(wrongEnv);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"If the semanticId of a SubmodelElementCollection with SubmodelElementCollection/allowDuplicates == false references a ConceptDescription then the ConceptDescription/category shall be ENTITY."));
		}
	}

	private SubmodelElementCollection createSubmodelElementCollection(String idShort, String conceptDescriptionId) {
		return new DefaultSubmodelElementCollection.Builder()
				.idShort(idShort)
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
