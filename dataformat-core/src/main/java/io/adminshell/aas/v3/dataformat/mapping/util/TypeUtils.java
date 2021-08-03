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
package io.adminshell.aas.v3.dataformat.mapping.util;

import com.google.common.reflect.TypeToken;
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.LoggerFactory;

public class TypeUtils {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TypeUtils.class);

    private TypeUtils() {
    }

    public static List<PropertyDescriptor> getAASProperties(Class<?> type) {
        Class<?> aasType = ReflectionHelper.getAasInterface(type);
        Set<Class<?>> types = new HashSet<>();
        if (aasType != null) {
            types.add(aasType);
            types.addAll(ReflectionHelper.getSuperTypes(aasType, true));
        }
        return types.stream()
                .flatMap(x -> {
                    try {
                        return Stream.of(Introspector.getBeanInfo(x).getPropertyDescriptors());
                    } catch (IntrospectionException ex) {
                        log.warn("error finding properties of class '{}'", type, ex);
                    }
                    return Stream.empty();
                })
                .sorted(Comparator.comparing(x -> x.getName()))
                .collect(Collectors.toList());
    }

    public static class TypeTokenComparator implements Comparator<TypeToken> {

        @Override
        public int compare(TypeToken x, TypeToken y) {
            if (x.isSubtypeOf(y)) {
                if (y.isSubtypeOf(x)) {
                    return 0;
                }
                return -1;
            }
            return 1;
        }
    }
}
