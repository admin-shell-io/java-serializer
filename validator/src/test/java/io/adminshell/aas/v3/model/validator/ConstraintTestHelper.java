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

import java.util.List;

import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.AssetKind;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.IdentifierType;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.KeyType;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.SubmodelElement;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShell;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.impl.DefaultAssetInformation;
import io.adminshell.aas.v3.model.impl.DefaultConceptDescription;
import io.adminshell.aas.v3.model.impl.DefaultIdentifier;
import io.adminshell.aas.v3.model.impl.DefaultKey;
import io.adminshell.aas.v3.model.impl.DefaultReference;
import io.adminshell.aas.v3.model.impl.DefaultSubmodel;

public class ConstraintTestHelper {

	public static ConceptDescription createConceptDescription(String idShort, String identifier, String category) {
		return new DefaultConceptDescription.Builder()
				.description(new LangString("TestDescription"))
				.identification(new DefaultIdentifier.Builder().identifier(identifier)
						.idType(IdentifierType.CUSTOM).build())
				.category(category).idShort(idShort).build();
	}
	
	public static Submodel createSubmodel(List<SubmodelElement> elements) {
		return new DefaultSubmodel.Builder()
				.identification(
						new DefaultIdentifier.Builder().identifier("submodel").idType(IdentifierType.CUSTOM).build())
				.idShort("smIdShort")
				.submodelElements(elements)
				.build();
	}

	public static ConceptDescription getIrrelevantConceptDescription() {
		return ConstraintTestHelper.createConceptDescription("irrelevantIdShort", "irrelevant", "COLLECTION");
	}
	
	public static AssetAdministrationShell getDummyAAS() {
		return new DefaultAssetAdministrationShell.Builder()
				.identification(new DefaultIdentifier.Builder()
						.identifier("dummyAAS")
						.idType(IdentifierType.CUSTOM)
						.build())
				.idShort("dummyAASIdShort")
				.assetInformation(new DefaultAssetInformation.Builder()
						.assetKind(AssetKind.INSTANCE)
						.build())
				.build();
	}
	
	public static AssetAdministrationShellEnvironment createEnvironment(Submodel sm, List<ConceptDescription> conceptDescriptions) {
		return new DefaultAssetAdministrationShellEnvironment.Builder()
				.assetAdministrationShells(getDummyAAS())
				.submodels(sm)
				.conceptDescriptions(conceptDescriptions)
				.build();
	}

	public static Reference createDummyReference() {
		return new DefaultReference.Builder()
				.key(new DefaultKey.Builder()
						.idType(KeyType.CUSTOM)
						.value("reference")
						.type(KeyElements.GLOBAL_REFERENCE)
						.build())
				.build();
	}
}
