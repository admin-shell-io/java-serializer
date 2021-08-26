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

import org.junit.Test;

import io.adminshell.aas.v3.model.AdministrativeInformation;
import io.adminshell.aas.v3.model.impl.DefaultAdministrativeInformation;

/**
 * Tests the following constraint:
 * <p>
 * <i> A revision requires a version. </i>
 * </p>
 * 
 * @author schnicke, chang
 *
 */
public class TestAASd_005 {
	@Test
	public void revisionWithoutVersion() throws ValidationException {
		AdministrativeInformation admInfo = new DefaultAdministrativeInformation.Builder().revision("1").build();

		try {
			ShaclValidator.getInstance().validate(admInfo);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith("A revision requires a version."));
		}
	}

	@Test
	public void revisionWithVersion()
			throws ValidationException {
		AdministrativeInformation admInfo = new DefaultAdministrativeInformation.Builder().revision("1").version("1")
				.build();
		ShaclValidator.getInstance().validate(admInfo);
	}
}
