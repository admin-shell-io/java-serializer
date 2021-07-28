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

public class ConditionParserAction<TARGET> {
	
	public final Predicate<UANodeWrapper> condition;
	public final I4AASParser<TARGET> parser;
	public final Consumer<TARGET> action;

	private ConditionParserAction(Predicate<UANodeWrapper> condition, I4AASParser<TARGET> parser, Consumer<TARGET> action) {
		this.condition = condition;
		this.parser = parser;
		this.action = action;
	}
	
	public static <TARGET> ConditionParserAction<TARGET> of(Predicate<UANodeWrapper> condition, I4AASParser<TARGET> parser, Consumer<TARGET> action) {
		return new ConditionParserAction<TARGET>(condition, parser, action);
	}

}
