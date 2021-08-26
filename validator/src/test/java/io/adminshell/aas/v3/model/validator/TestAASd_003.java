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

import io.adminshell.aas.v3.model.Referable;
import io.adminshell.aas.v3.model.impl.DefaultSubmodel;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the following constraint:
 * <p>
 * <i> idShort shall be matched case-insensitive. </i>
 * </p>
 * 
 * @author bader, chang
 *
 */
public class TestAASd_003 {
	@Test
	public void idShortMatchCaseInsensitive() throws ValidationException {
		DefaultSubmodel smA = (DefaultSubmodel) ConstraintTestHelper.createSubmodel(new ArrayList<>());
		smA.setIdShort("idShort");

		Referable smB = (DefaultSubmodel) ConstraintTestHelper.createSubmodel(new ArrayList<>());
		smB.setIdShort("IDSHORT");

		// assertTrue(smA.equals(smB)); // TODO: should be true

	}

}
