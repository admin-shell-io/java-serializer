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

import java.util.function.Consumer;
import java.util.function.Predicate;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.I4AASIdentifier;
import io.adminshell.aas.v3.model.AdministrativeInformation;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.impl.DefaultSubmodel;

public class SubmodelParser extends I4AASParser<Submodel> {

	private I4AASParser<AdministrativeInformation> pars = new I4AASParser<AdministrativeInformation>(source, ctx, type) {
		
		@Override
		protected AdministrativeInformation createTargetObject() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	public SubmodelParser(UANodeWrapper src, ParserContext ctx) {
		super(src, ctx, I4AASIdentifier.AASSubmodelElementType);
		//Predicate<UANodeWrapper> pred;
		//Consumer<AdministrativeInformation> act;
		//this.<AdministrativeInformation>addChildMapping(pred, pars , act);
	}

	@Override
	protected Submodel createTargetObject() {
		DefaultSubmodel defaultSubmodel = new DefaultSubmodel();
		return defaultSubmodel;
	}

	@Override
	protected void parseAndAttachChildren() {
		// TODO Auto-generated method stub
	}


}
