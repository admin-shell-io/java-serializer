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

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import io.adminshell.aas.v3.dataformat.i4aas.mappers.utils.BasicIdentifier;

public abstract class I4AASParser<TARGET> {

	protected ParserContext ctx;
	protected UANodeWrapper source;
	protected TARGET target;
	protected BasicIdentifier type;
	protected List<ConditionParserAction<Object>> genericChildParserLogic;

	public I4AASParser(UANodeWrapper src, ParserContext ctx, BasicIdentifier forUaType) {
		this.source = src;
		this.ctx = ctx;
		this.type = forUaType;
	}

	protected abstract TARGET createTargetObject();

	public final TARGET parse() {
		target = createTargetObject();
		parseAndAttachChildren();
		return target;
	}

	protected final <CHILDTARGET> void addChildMapping(Predicate<UANodeWrapper> condition, I4AASParser<CHILDTARGET> parser,
			Consumer<CHILDTARGET> action) {
		genericChildParserLogic.add((ConditionParserAction<Object>) ConditionParserAction.<CHILDTARGET>of(condition, parser, action));
	}
	
	protected Predicate<UANodeWrapper> ofType(BasicIdentifier identifier) {
		return node -> node.getType() == identifier;
	}

	protected void parseAndAttachChildren() {
		for (UANodeWrapper uaNodeWrapper : source.getPropertiesAndComponents()) {
			for (ConditionParserAction<Object> logic : genericChildParserLogic) {
				if (logic.condition.test(uaNodeWrapper)) {
					Object parse = logic.parser.parse();
					logic.action.accept(parse);
				}
			}
		}
	}


}
