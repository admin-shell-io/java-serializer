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
import io.adminshell.aas.v3.model.Property;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.impl.DefaultOperation;
import io.adminshell.aas.v3.model.impl.DefaultProperty;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the following constraint:
 * <p>
 * <i> The submodel element value of an operation variable shall be of kind=Template. </i>
 * </p>
 * 
 * @author bader, chang
 *
 */
public class TestAASd_008 {
	@Test
	public void wrongKind() throws ValidationException {

		Operation wrongOperation = new DefaultOperation.Builder()
				.idShort("idShort")
				.kind(ModelingKind.INSTANCE)
				.build();

		try {
			ShaclValidator.getInstance().validate(wrongOperation);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"The submodel element value of an operation variable shall be of kind=Template."));
		}


	}

	@Test
	public void correctKind() throws ValidationException {

		Operation operation = new DefaultOperation.Builder()
				.idShort("idShort")
				.kind(ModelingKind.TEMPLATE)
				.build();

		ShaclValidator.getInstance().validate(operation);

	}
}
