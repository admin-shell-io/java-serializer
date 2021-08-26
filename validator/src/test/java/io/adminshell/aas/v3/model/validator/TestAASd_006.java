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

import io.adminshell.aas.v3.model.Qualifier;
import io.adminshell.aas.v3.model.Referable;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.impl.DefaultQualifier;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the following constraint:
 * <p>
 * <i> If both, the value and the valueId of a Qualifier are present then the value needs to be identical to the value
 * of the referenced coded value in Qualifier/valueId. </i>
 * </p>
 * 
 * @author bader, chang
 *
 */
public class TestAASd_006 {
	@Test
	public void missmatchingValueAndValueId() throws ValidationException {

		Reference reference = ConstraintTestHelper.createDummyReference();
		reference.getKeys().get(0).setValue("http://example.org/someRef");

		Qualifier wrongQualifier = new DefaultQualifier.Builder()
				.value("http://example.org")
				.valueType("string")
				.valueId(reference)
				.type("REFERENCE")
				.build();

		try {
			ShaclValidator.getInstance().validate(wrongQualifier);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"If both, the value and the valueId of a Qualifier are present then the value needs to be identical " +
							"to the value of the referenced coded value in Qualifier/valueId."));
		}


	}

	@Test
	public void matchingValueAndValueId() throws ValidationException {
		Reference reference = ConstraintTestHelper.createDummyReference();
		reference.getKeys().get(0).setValue("http://example.org");

		Qualifier qualifier = new DefaultQualifier.Builder()
				.value("http://example.org")
				.valueType("string")
				.valueId(reference)
				.type("REFERENCE")
				.build();

		ShaclValidator.getInstance().validate(qualifier);

	}
}
