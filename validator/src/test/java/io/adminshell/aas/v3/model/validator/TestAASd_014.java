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

import io.adminshell.aas.v3.model.Entity;
import io.adminshell.aas.v3.model.EntityType;
import io.adminshell.aas.v3.model.IdentifierKeyValuePair;
import io.adminshell.aas.v3.model.impl.DefaultEntity;
import io.adminshell.aas.v3.model.impl.DefaultIdentifierKeyValuePair;

/**
 * Tests the following constraint:
 * <p>
 * <i> Either the attribute globalAssetId or specificAssetId of an Entity must
 * be set if Entity/entityType is set to “SelfManagedEntity”. They are not
 * existing otherwise. </i>
 * </p>
 * 
 * @author schnicke, kannoth
 *
 */
public class TestAASd_014 {
	private static final String ERRORMSG = "Either the attribute globalAssetId or specificAssetId of an Entity must be set if Entity/entityType is set to “SelfManagedEntity”. They are not existing otherwise.";

	@Test
	public void selfManagedEntityWithGlobalAssetIdSet() throws ValidationException {
		Entity entity = createSelfManagedEntity();
		entity.setGlobalAssetId(ConstraintTestHelper.createDummyReference());

		ShaclValidator.getInstance().validate(entity);
	}

	@Test
	public void selfManagedEntityWithSpecificAssetIdSet() throws ValidationException {
		Entity entity = createSelfManagedEntity();
		IdentifierKeyValuePair idKeyPair = createKeyValuePair();
		entity.setSpecificAssetId(idKeyPair);

		ShaclValidator.getInstance().validate(entity);
	}

	@Test
	public void selfManagedEntityWithNoAssetIdSet() {
		Entity entity = createSelfManagedEntity();

		try {
			ShaclValidator.getInstance().validate(entity);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(ERRORMSG));
		}
	}

	@Test
	public void selfManagedEntitiyWithBothAssetIdsSet() {
		Entity entity = createSelfManagedEntity();
		IdentifierKeyValuePair idKeyPair = createKeyValuePair();
		entity.setSpecificAssetId(idKeyPair);
		entity.setGlobalAssetId(ConstraintTestHelper.createDummyReference());

		try {
			ShaclValidator.getInstance().validate(entity);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(ERRORMSG));
		}
	}


	@Test
	public void coManagedEntityWithNoAssetIdSet() throws ValidationException {
		Entity entity = createCoManagedEntity();

		ShaclValidator.getInstance().validate(entity);
	}

	@Test
	public void coManagedEntityWithSpecificAssetIdSet() {
		Entity entity = createCoManagedEntity();
		IdentifierKeyValuePair idKeyPair = createKeyValuePair();
		entity.setSpecificAssetId(idKeyPair);

		try {
			ShaclValidator.getInstance().validate(entity);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(ERRORMSG));
		}
	}

	@Test
	public void coManagedEntityWithGlobalAssetIdSet() {
		Entity entity = createCoManagedEntity();
		entity.setGlobalAssetId(ConstraintTestHelper.createDummyReference());

		try {
			ShaclValidator.getInstance().validate(entity);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(ERRORMSG));
		}
	}

	@Test
	public void coManagedEntityWithBothAssetIdsSet() {
		Entity entity = createCoManagedEntity();
		entity.setGlobalAssetId(ConstraintTestHelper.createDummyReference());
		IdentifierKeyValuePair idKeyPair = createKeyValuePair();
		entity.setSpecificAssetId(idKeyPair);

		try {
			ShaclValidator.getInstance().validate(entity);
			fail();
		} catch (ValidationException e) {
			assertTrue(e.getMessage().endsWith(ERRORMSG));
		}
	}

	private Entity createSelfManagedEntity() {
		DefaultEntity entity = new DefaultEntity();
		entity.setIdShort("entityIdShort");
		entity.setEntityType(EntityType.SELF_MANAGED_ENTITY);
		return entity;
	}
	
	private Entity createCoManagedEntity() {
		DefaultEntity entity = new DefaultEntity();
		entity.setIdShort("entityIdShort");
		entity.setEntityType(EntityType.CO_MANAGED_ENTITY);
		return entity;
	}

	private IdentifierKeyValuePair createKeyValuePair() {
		return new DefaultIdentifierKeyValuePair.Builder()
				.externalSubjectId(ConstraintTestHelper.createDummyReference())
				.value("foo").key("foo_key").build();
	}
}
