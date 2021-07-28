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
package io.adminshell.aas.v3.dataformat.i4aas;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.opcfoundation.ua._2011._03.uanodeset.UANode;
import org.opcfoundation.ua._2011._03.uanodeset.UANodeSet;

import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.dataformat.Deserializer;
import io.adminshell.aas.v3.dataformat.i4aas.parsers.EnvironmentParser;
import io.adminshell.aas.v3.dataformat.i4aas.parsers.ParserContext;
import io.adminshell.aas.v3.dataformat.i4aas.parsers.UANodeWrapper;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;

public class I4AASDeserializer implements Deserializer {

	@Override
	public AssetAdministrationShellEnvironment read(String value) throws DeserializationException {
		try {
			UANodeSet uaNodeSet = new UANodeSetUnmarshaller().unmarshall(value);
			ParserContext parserContext = new ParserContext(uaNodeSet);
			UANodeWrapper environment = parserContext.getPreparsedEnvironment();
			return new EnvironmentParser(environment, new ParserContext(uaNodeSet)).parse();
		} catch (JAXBException e) {
			throw new DeserializationException("Deserialization failed due to a JAXBException.", e);
		}
	}

	protected UANode findAASEnvironment(UANodeSet nodeSet) throws DeserializationException {
		List<UANode> uaObjectOrUAVariableOrUAMethod = nodeSet.getUAObjectOrUAVariableOrUAMethod();
		for (UANode uaNode : uaObjectOrUAVariableOrUAMethod) {
			if (uaNode.getBrowseName().contains("AASEnvironment")) {
				return uaNode;
			}
		}
		throw new DeserializationException("No AASEnvironment found.");
	}

	@Override
	public <T> void useImplementation(Class<T> aasInterface, Class<? extends T> implementation) {
	}

}
