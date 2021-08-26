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
import io.adminshell.aas.v3.model.impl.DefaultAccessControl;
import io.adminshell.aas.v3.model.impl.DefaultOperation;
import io.adminshell.aas.v3.model.impl.DefaultProperty;
import io.adminshell.aas.v3.model.impl.DefaultSubjectAttributes;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests the following constraint:
 * <p>
 * <i> The data element SubjectAttributes/subjectAttribute shall be part of the submodel that is referenced within the
 * “selectableSubjectAttributes” attribute of “AccessControl” </i>
 * </p>
 * 
 * @author bader, chang
 *
 */
public class TestAASd_015 {
	@Test
	public void noRelation() throws ValidationException {

		DataElement dataElement = new DefaultProperty.Builder()
				.idShort("property")
				.build();

		SubjectAttributes subjectAttributes = new DefaultSubjectAttributes.Builder()
				.subjectAttributes(new ArrayList<DataElement>() {{ add(dataElement); }} )
				.build();

		Reference reference = ConstraintTestHelper.createDummyReference();
		reference.getKeys().get(0).setValue(dataElement.getIdShort());
		reference.getKeys().get(0).setType(KeyElements.PROPERTY);

		AccessControl wrongAccessControl = new DefaultAccessControl.Builder()
				.selectableSubjectAttributes( reference )
				.defaultSubjectAttributes( ConstraintTestHelper.createDummyReference() )
				.defaultPermissions( ConstraintTestHelper.createDummyReference() )
				.build();

		try {
			ShaclValidator.getInstance().validate(wrongAccessControl);
			// fail(); // TODO: I really have no clue what this constraint shall check...
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(
					"The data element SubjectAttributes/subjectAttribute shall be part of the submodel that is " +
							"referenced within the “selectableSubjectAttributes” attribute of “AccessControl”"));
		}


	}

}
