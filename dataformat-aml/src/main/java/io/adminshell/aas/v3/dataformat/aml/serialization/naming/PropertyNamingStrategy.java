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
package io.adminshell.aas.v3.dataformat.aml.serialization.naming;

import com.google.common.reflect.TypeToken;
import io.adminshell.aas.v3.dataformat.core.util.MostSpecificTypeTokenComparator;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PropertyNamingStrategy implements NamingStrategy {

    protected List<TypeSafeFunction> customNamings = new ArrayList<>();

    protected class TypeSafeFunction<T> {

        public TypeSafeFunction(Class<T> inputType, BiFunction<T, String, String> provider, boolean applyToRefSemantic) {
            this.inputType = TypeToken.of(inputType);
            this.provider = provider;
            this.applyToRefSemantic = applyToRefSemantic;
        }
        TypeToken inputType;
        BiFunction<T, String, String> provider;
        boolean applyToRefSemantic;
    }

    public <T> void registerCustomNaming(Class<T> type, BiFunction<T, String, String> provider, boolean applyToRefSemantic) {
        customNamings.add(new TypeSafeFunction(type, provider, applyToRefSemantic));
    }

    public void registerCustomNaming(Class<?> type, String oldName, String newName, boolean applyToRefSemantic) {
        customNamings.add(new TypeSafeFunction(type, (obj, property) -> Objects.equals(oldName, property) ? newName : null, applyToRefSemantic));
    }

    public <T> void registerCustomNaming(Class<T> type, Function<T, String> provider, boolean applyToRefSemantic) {
        customNamings.add(new TypeSafeFunction(type, (x, y) -> provider.apply((T) x), applyToRefSemantic));
    }

    private List<TypeSafeFunction> getCustomNaming(Type type, String property, boolean applyToRefSemantic) {
        return customNamings.stream()
                .filter(x -> x.inputType.isSupertypeOf(type))
                .filter(x -> !applyToRefSemantic || x.applyToRefSemantic)
                .sorted((x, y) -> Objects.compare(x.inputType, y.inputType, new MostSpecificTypeTokenComparator()))
                .collect(Collectors.toList());
    }

    @Override
    public String getName(Type type, Object obj, String property) {
        for (TypeSafeFunction customNaming : getCustomNaming(type, property, false)) {
            String result = (String) customNaming.provider.apply(obj, property);
            if (result != null) {
                return result;
            }
        }
        return property;
    }

    @Override
    public String getNameForRefSemantic(Type type, Object obj, String property) {
        for (TypeSafeFunction customNaming : getCustomNaming(type, property, true)) {
            String result = (String) customNaming.provider.apply(obj, property);
            if (result != null) {
                return result;
            }
        }
        return property;
    }

}
