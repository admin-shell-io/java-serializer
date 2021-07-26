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

import org.opcfoundation.ua._2011._03.uanodeset.UANode;

import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShellEnvironment;

public class EnvironmentParser extends I4AASParser<UANode, AssetAdministrationShellEnvironment> {

	public EnvironmentParser(UANode src, ParserContext ctx) {
		super(src, ctx);
	}
	
	@Override
	protected AssetAdministrationShellEnvironment createTargetObject() {
		target = new DefaultAssetAdministrationShellEnvironment();
		return target;
	}

	@Override
	protected void parseAndAttachChildren() {
		// TODO Auto-generated method stub
	}

}
