/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.adminshell.aas.v3.dataformat.i4aas.parsers;

import static io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASIdentifier.AASAssetAdministrationShellType;
import static io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASIdentifier.AASSubmodelType;

import java.util.function.Predicate;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASIdentifier;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShellEnvironment;

public class EnvironmentParser extends I4AASParser<AssetAdministrationShellEnvironment> {

	private Predicate<UANodeWrapper> pred;

	public EnvironmentParser(UANodeWrapper src, ParserContext ctx) {
		super(src, ctx, I4AASIdentifier.AASEnvironmentType);
		this.<Submodel>addChildMapping(ofType(AASSubmodelType), new SubmodelParser(src, ctx), target.getSubmodels()::add);
		this.<AssetAdministrationShell>addChildMapping(ofType(AASAssetAdministrationShellType), new AssetAdministrationShellParser(src, ctx), target.getAssetAdministrationShells()::add);
	}

	@Override
	protected AssetAdministrationShellEnvironment createTargetObject() {
		target = new DefaultAssetAdministrationShellEnvironment();
		return target;
	}
	
}
