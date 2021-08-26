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

import io.adminshell.aas.v3.model.ModelingKind;
import io.adminshell.aas.v3.model.Operation;
import io.adminshell.aas.v3.model.Qualifier;
import io.adminshell.aas.v3.model.impl.DefaultOperation;
import io.adminshell.aas.v3.model.impl.DefaultQualifier;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the following constraint:
 * <p>
 * <i> The value of Qualifier/value shall be consistent to the data type as defined in Qualifier/valueType. </i>
 * </p>
 * 
 * @author bader, chang
 *
 */
public class TestAASd_020 {
	@Test
	public void wrongIntegerValue() throws ValidationException {

		Qualifier wrongQualifier = new DefaultQualifier.Builder()
				.valueType("http://www.w3.org/2001/XMLSchema#int")
				.value("test")
				.type("integer")
				.build();

		try {
			ShaclValidator.getInstance().validate(wrongQualifier);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"The value of Qualifier/value shall be consistent to the data type as defined in Qualifier/valueType."));
		}
	}

	// TODO: non-strings can not be passed as Qualifier.value --> Strings will always pass, others are not recognized.
	/*
	@Test
	public void wrongStringValue() throws ValidationException {

		Qualifier wrongQualifier = new DefaultQualifier.Builder()
				.valueType("http://www.w3.org/2001/XMLSchema#string")
				.value("1")
				.type("integer")
				.build();

		try {
			ShaclValidator.getInstance().validate(wrongQualifier);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"The value of Qualifier/value shall be consistent to the data type as defined in Qualifier/valueType."));
		}
	}
	 */


	@Test
	public void correctString() throws ValidationException {

		Qualifier wrongQualifier = new DefaultQualifier.Builder()
				.valueType("http://www.w3.org/2001/XMLSchema#string")
				.value("a string")
				.type("string")
				.build();

		ShaclValidator.getInstance().validate(wrongQualifier);

	}
}
