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

import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.dataformat.Deserializer;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;

/**
 * Deserializer Implementation for I4AAS
 *
 */
public class I4AASDeserializer implements Deserializer {

	/**
	 * reads a I4AAS as string and return the model object
	 */
	@Override
	public AssetAdministrationShellEnvironment read(String value) throws DeserializationException {
		throw new UnsupportedOperationException("Not Implemented yet.");
	}

	@Override
	public <T> void useImplementation(Class<T> aasInterface, Class<? extends T> implementation) {
	}

}
