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

import io.adminshell.aas.v3.model.Key;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.KeyType;
import io.adminshell.aas.v3.model.impl.DefaultKey;

/**
 * Tests the following constraint:
 * <p>
 * <i>In case Key/type == GlobalReference idType shall not be any LocalKeyType
 * (IdShort, FragmentId). </i>
 * </p>
 * 
 * @author schnicke
 *
 */
public class TestAASd_080 {
	private static final String ERRORMSG = "In case Key/type == GlobalReference idType shall not be any LocalKeyType (IdShort, FragmentId).";

	@Test
	public void correctIdType() throws ValidationException {
		Key key = createWithTypeGlobalReferenceKey(KeyType.CUSTOM);

		ShaclValidator.getInstance().validate(key);
	}

	@Test
	public void wrongIdTypeIdShort() {
		Key key = createWithTypeGlobalReferenceKey(KeyType.ID_SHORT);
		try {
			ShaclValidator.getInstance().validate(key);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(ERRORMSG));
		}
	}

	@Test
	public void wrongIdTypeFragmentId() {
		Key key = createWithTypeGlobalReferenceKey(KeyType.FRAGMENT_ID);

		try {
			ShaclValidator.getInstance().validate(key);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(ERRORMSG));
		}
	}

	private Key createWithTypeGlobalReferenceKey(KeyType custom) {
		return new DefaultKey.Builder()
				.value("value")
				.type(KeyElements.GLOBAL_REFERENCE)
				.idType(custom)
				.build();
	}

}
