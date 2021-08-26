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

import io.adminshell.aas.v3.model.AdministrativeInformation;
import io.adminshell.aas.v3.model.Referable;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.impl.DefaultAdministrativeInformation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the following constraint:
 * <p>
 * <i> idShort of Referables shall only feature letters, digits, underscore ("_");
 * starting mandatory with a letter. I.e. [a-zA-Z][a-zA-Z0-9_]+. </i>
 * </p>
 * 
 * @author bader, chang
 *
 */
public class TestAASd_002 {
	@Test
	public void idShortWithNotAllowedCharacters() throws ValidationException {
		Referable wrongReferable = ConstraintTestHelper.createSubmodel(new ArrayList<>());
		wrongReferable.setIdShort("_idShort");

		try {
			ShaclValidator.getInstance().validate(wrongReferable);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith("starting mandatory with a letter. I.e. [a-zA-Z][a-zA-Z0-9_]+."));
		}

		wrongReferable.setIdShort("0idShort");
		try {
			ShaclValidator.getInstance().validate(wrongReferable);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith("starting mandatory with a letter. I.e. [a-zA-Z][a-zA-Z0-9_]+."));
		}

		wrongReferable.setIdShort("");
		try {
			ShaclValidator.getInstance().validate(wrongReferable);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith("starting mandatory with a letter. I.e. [a-zA-Z][a-zA-Z0-9_]+."));
		}

		wrongReferable.setIdShort("%");
		try {
			ShaclValidator.getInstance().validate(wrongReferable);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith("starting mandatory with a letter. I.e. [a-zA-Z][a-zA-Z0-9_]+."));
		}
	}

	@Test
	public void idShortWithAllowedCharacters() throws ValidationException {
		Referable referable = ConstraintTestHelper.createSubmodel(new ArrayList<>());
		referable.setIdShort("id_Short");
		ShaclValidator.getInstance().validate(referable);
	}
}
