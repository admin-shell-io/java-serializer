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
package io.adminshell.aas.v3.dataformat.mapping;

import com.google.common.reflect.TypeToken;
import io.adminshell.aas.v3.dataformat.mapping.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MappingProvider<T extends Mapper> {

    private static final Logger log = LoggerFactory.getLogger(MappingProvider.class);

    private final T defaultMapper;
    private final T defaultCollectionMapper;
    private final Map<TypeToken<?>, List<T>> mappings = new HashMap<>();

    public MappingProvider(Class<T> type,
            Mapper<Object> defaultMapper,
            Mapper<Collection<Object>> defaultCollectionMapper) {
        if (type == null) {
            throw new IllegalArgumentException("type must be non-null");
        }
        if (defaultMapper == null) {
            throw new IllegalArgumentException("defaultMapper must be non-null");
        }
        if (defaultCollectionMapper == null) {
            throw new IllegalArgumentException("defaultCollectionMapper must be non-null");
        }
        if (!type.isAssignableFrom(defaultMapper.getClass())) {
            throw new IllegalArgumentException("defaultMapper must be of type " + type);
        }
        if (!type.isAssignableFrom(defaultCollectionMapper.getClass())) {
            throw new IllegalArgumentException("defaultCollectionMapper must be of type " + type);
        }
        this.defaultMapper = (T) defaultMapper;
        this.defaultCollectionMapper = (T) defaultCollectionMapper;
    }

    public void register(T mapper) {
        TypeToken<?> key = getMappedType(mapper.getClass());
        if (!mappings.containsKey(key)) {
            mappings.put(key, new ArrayList<>());
        }
        mappings.get(key).add(mapper);
    }

    private TypeToken<?> getMappedType(Class<?> type) {
        return TypeToken.of(type)
                .getTypes().stream()
                .filter(y -> Mapper.class.equals(y.getRawType()))
                .findFirst()
                .get()
                .resolveType(Mapper.class.getTypeParameters()[0]);
    }

    public T getMapper(Object obj) {
        if (obj == null) {
            return getMapper(Object.class);
        }
        if (Type.class.isAssignableFrom(obj.getClass())) {
            return getMapper((Type) obj);
        }
        return getMapper(obj.getClass());
    }

    public T getMapper(Type type) {
        Optional<List<T>> customMapper = mappings.entrySet().stream()
                .filter(x -> x.getKey().isSupertypeOf(type))
                .sorted((x, y) -> Objects.compare(x.getKey(), y.getKey(), new TypeUtils.TypeTokenComparator()))
                .map(x -> x.getValue())
                .findFirst();
        if (!customMapper.isPresent() || customMapper.get().isEmpty()) {
            if (TypeToken.of(Collection.class).isSupertypeOf(type) && defaultCollectionMapper != null) {
                return defaultCollectionMapper;
            }
            return defaultMapper;
        }
        if (customMapper.get().size() > 1) {
            log.warn("found {} equally suitable mappers for type '{}'", customMapper.get().size(), type);
        }
        return customMapper.get().get(0);
    }

}
