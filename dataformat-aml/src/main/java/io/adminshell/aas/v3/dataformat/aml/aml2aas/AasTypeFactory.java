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
package io.adminshell.aas.v3.dataformat.aml.aml2aas;

import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class AasTypeFactory {

    private Map<Class<?>, Class<?>> typeMapping;

    public AasTypeFactory() {
        ReflectionHelper.DEFAULT_IMPLEMENTATIONS.forEach(x -> typeMapping.put(x.getInterfaceType(), x.getImplementationType()));
    }

    public <T> void useImplementation(Class<T> aasInterface, Class<? extends T> implementation) {
        typeMapping.put(aasInterface, implementation);
    }

    public <T> T newInstance(Class<T> aasInterface) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> classToInstantiate = aasInterface;
        if (typeMapping.containsKey(aasInterface)) {
            classToInstantiate = typeMapping.get(aasInterface);
        }
        Constructor<?> constructor = classToInstantiate.getConstructor();
        constructor.setAccessible(true);
        return (T) constructor.newInstance();
    }
}
