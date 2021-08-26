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

import io.adminshell.aas.v3.model.Property;
import io.adminshell.aas.v3.model.Qualifier;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.impl.DefaultProperty;
import io.adminshell.aas.v3.model.impl.DefaultQualifier;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the following constraint:
 * <p>
 * <i> If both, the Property/value and the Property/valueId are present then the value of Property/value needs to be
 * identical to the value of the referenced coded value in Property/valueId. </i>
 * </p>
 * 
 * @author bader, chang
 *
 */
public class TestAASd_007 {
	@Test
	public void missmatchingValueAndValueId() throws ValidationException {

		Reference reference = ConstraintTestHelper.createDummyReference();
		reference.getKeys().get(0).setValue("http://example.org/someRef");

		Property wrongProperty = new DefaultProperty.Builder()
				.idShort("idShort")
				.value("http://example.org")
				.valueType("string")
				.valueId(reference)
				.build();

		try {
			ShaclValidator.getInstance().validate(wrongProperty);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"If both, the Property/value and the Property/valueId are present then the value of Property/value " +
							"needs to be identical to the value of the referenced coded value in Property/valueId."));
		}


	}

	@Test
	public void matchingValueAndValueId() throws ValidationException {
		Reference reference = ConstraintTestHelper.createDummyReference();
		reference.getKeys().get(0).setValue("http://example.org");

		Property property = new DefaultProperty.Builder()
				.idShort("idShort")
				.value("http://example.org")
				.valueType("string")
				.valueId(reference)
				.build();

		ShaclValidator.getInstance().validate(property);

	}
}
