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

import io.adminshell.aas.v3.model.Blob;
import io.adminshell.aas.v3.model.File;
import io.adminshell.aas.v3.model.Property;
import io.adminshell.aas.v3.model.impl.DefaultBlob;
import io.adminshell.aas.v3.model.impl.DefaultFile;
import io.adminshell.aas.v3.model.impl.DefaultProperty;

/**
 * Tests the following constraint:
 * <p>
 * <i>For data elements DataElement/category shall be one of the following
 * values: CONSTANT, PARAMETER or VARIABLE. Exception: File and Blob data
 * elements. </i>
 * </p>
 * 
 * @author schnicke
 *
 */
public class TestAASd_090 {
	@Test
	public void correctCategoryConstant() throws ValidationException {
		ShaclValidator.getInstance().validate(createProperty("idShort1", "CONSTANT"));
	}

	@Test
	public void correctCategoryParameter() throws ValidationException {
		ShaclValidator.getInstance().validate(createProperty("idShort1", "PARAMETER"));
	}

	@Test
	public void correctCategoryVariable() throws ValidationException {
		ShaclValidator.getInstance().validate(createProperty("idShort1", "VARIABLE"));
	}

	@Test
	public void fileCategoryDoesntMatter() throws ValidationException {
		ShaclValidator.getInstance().validate(createFile());
	}

	@Test
	public void blobCategoryDoesntMatter() throws ValidationException {
		ShaclValidator.getInstance().validate(createBlob());
	}

	@Test
	public void wrongCategory() {
		try {
			ShaclValidator.getInstance().validate(createProperty("idShort1", "WRONG"));
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"For data elements DataElement/category shall be one of the following values: CONSTANT, PARAMETER or VARIABLE. Exception: File and Blob data elements."));
		}
	}

	private Property createProperty(String idShort, String category) {
		return new DefaultProperty.Builder()
				.idShort(idShort)
				.category(category)
				.value("test")
				.valueType("string")
				.build();
	}

	private File createFile() {
		return new DefaultFile.Builder()
				.idShort("idShort1")
				.category("DOESNTMATTER")
				.mimeType("application/json").value("test.json")
				.build();
	}
	
	private Blob createBlob() {
		return new DefaultBlob.Builder()
				.idShort("idShort2")
				.category("DOESNTMATTER")
				.mimeType("application/json")
				.value(new byte[] { 0, 1, 2 })
				.build();
	}

}
