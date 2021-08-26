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

import io.adminshell.aas.v3.model.*;
import io.adminshell.aas.v3.model.impl.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the following constraint:
 * <p>
 * <i> The name of an extension within HasExtensions needs to be unique. </i>
 * </p>
 * 
 * @author bader
 *
 */
public class TestAASd_077 {
	@Test
	public void repeatingExtensionName() throws ValidationException {

		Extension extension1 = new DefaultExtension.Builder()
				.name("extension")
				.build();

		Extension extension2 = new DefaultExtension.Builder()
				.name("extension")
				.build();

		Referable referable = new DefaultSubmodel.Builder()
				.idShort("submodel_idShort")
				.identification(new DefaultIdentifier.Builder().identifier("http://example.org/TestSubmodel").idType(IdentifierType.IRI).build())
				//.setExtensions( new ArrayList<Extension>() {{add(extension1); add(extension2);}} )
				.build();

		try {
			ShaclValidator.getInstance().validate(referable);
			//fail(); // TODO Add HasExtensions to Referables
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"The name of an extension within HasExtensions needs to be unique."));
		}


	}

	@Test
	public void uniqueExtensionNames() throws ValidationException {
		Extension extension1 = new DefaultExtension.Builder()
				.name("extension1")
				.build();

		Extension extension2 = new DefaultExtension.Builder()
				.name("extension2")
				.build();

		Referable referable = new DefaultSubmodel.Builder()
				.idShort("submodel_idShort")
				.identification(new DefaultIdentifier.Builder().identifier("http://example.org/TestSubmodel").idType(IdentifierType.IRI).build())
				//.setExtensions( new ArrayList<Extension>() {{add(extension1); add(extension2);}} )
				.build();

		ShaclValidator.getInstance().validate(referable);

	}
}
