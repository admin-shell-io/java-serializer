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
package io.adminshell.aas.v3.dataformat.aml.deserialization;

import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Supports in creating instances of AAS interfaces
 */
public class AasTypeFactory {

    private Map<Class<?>, Class<?>> typeMapping;

    public AasTypeFactory() {
        typeMapping = ReflectionHelper.DEFAULT_IMPLEMENTATIONS.stream().collect(Collectors.toMap(x -> x.getInterfaceType(), x -> x.getImplementationType()));
    }

    /**
     * Defines which implementation class to use when creating instances of
     * aasInterface. Subsequent class with the same aasInterface parameter will
     * override the effects of all previous calls.
     *
     * @param <T> the type of the interface to replace
     * @param aasInterface the class of the interface to replace
     * @param implementation the class implementing the interface that should be
     * used for instantiation
     */
    public <T> void useImplementation(Class<T> aasInterface, Class<? extends T> implementation) {
        typeMapping.put(aasInterface, implementation);
    }

    /**
     * Creates a new instance for a given aasInterface. If the
     *
     * @param <T> type to create
     * @param aasInterface class to find instantiate
     * @return an instance of aasInterface
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException if aasInterface is null or no suitable
     * concrete type to instantiate could be found
     * @throws InvocationTargetException
     */
    public <T> T newInstance(Class<T> aasInterface) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (aasInterface == null) {
            throw new IllegalArgumentException("aasInterface must be non-null");
        }
        Class<?> classToInstantiate = aasInterface;
        if (typeMapping.containsKey(aasInterface)) {
            classToInstantiate = typeMapping.get(aasInterface);
        }
        if (classToInstantiate.isInterface()) {
            throw new IllegalArgumentException("could not resolve type for interface " + classToInstantiate.getName());
        }
        Constructor<?> constructor = classToInstantiate.getConstructor();
        constructor.setAccessible(true);
        return (T) constructor.newInstance();
    }
}
