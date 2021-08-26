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
import java.util.List;

import org.junit.Test;

import io.adminshell.aas.v3.model.ConceptDescription;

/**
 * Tests the following constraint:
 * <p>
 * <i> A ConceptDescription shall have one of the following categories: VALUE,
 * PROPERTY, REFERENCE, DOCUMENT, CAPABILITY, RELATIONSHIP, COLLECTION,
 * FUNCTION, EVENT, ENTITY, APPLICATION_CLASS, QUALIFIER, VIEW. </i>
 * </p>
 * 
 * @author schnicke, kannoth
 *
 */
public class TestAASd_051 {
	@Test
	public void correctCategory() throws ValidationException {
		List<String> categories = Arrays.asList("VALUE", "PROPERTY", "REFERENCE", "DOCUMENT", "CAPABILITY",
				"RELATIONSHIP", "COLLECTION", "FUNCTION", "EVENT", "ENTITY", "APPLICATION_CLASS", "QUALIFIER", "VIEW");
		
		for(String category : categories) {
			ConceptDescription cd = ConstraintTestHelper.createConceptDescription("idShort", "identifier", category);
			ShaclValidator.getInstance().validate(cd);
		}
	}

	@Test
	public void incorrectCategory() {
		ConceptDescription cd = ConstraintTestHelper.createConceptDescription("idShort", "identifier", "WRONG");
		try {
			ShaclValidator.getInstance().validate(cd);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"A ConceptDescription shall have one of the following categories: VALUE, PROPERTY, REFERENCE, DOCUMENT, CAPABILITY, RELATIONSHIP, COLLECTION, FUNCTION, EVENT, ENTITY, APPLICATION_CLASS, QUALIFIER, VIEW."));
		}
	}
}
