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
package io.adminshell.aas.v3.dataformat.aml;

import com.google.common.reflect.TypeToken;
import io.adminshell.aas.v3.dataformat.aml.mapper.Mapper;
import io.adminshell.aas.v3.dataformat.aml.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MappingProvider {

    private static final Logger log = LoggerFactory.getLogger(MappingProvider.class);
    private final NamingStrategy internalElementNamingStrategy;
    private final NamingStrategy attributeNamingStrategy;
    private Mapper<Object> defaultMapper;
    private Mapper<Collection<Object>> defaultCollectionMapper;
    private Map<TypeToken<?>, List<Mapper>> mappings;

    public MappingProvider(Mapper<Object> defaultMapper,
            Mapper<Collection<Object>> defaultCollectionMapper,
            List<Mapper<?>> mappers,
            NamingStrategy internalElementNamingStrategy,
            NamingStrategy attributeNamingStrategy) {
        this.defaultMapper = defaultMapper;
        this.defaultCollectionMapper = defaultCollectionMapper;
        this.internalElementNamingStrategy = internalElementNamingStrategy;
        this.attributeNamingStrategy = attributeNamingStrategy;
        initializeMappings(mappers);
    }

    public MappingProvider copy() {
        return new MappingProvider(defaultMapper, defaultCollectionMapper, mappings, internalElementNamingStrategy, attributeNamingStrategy);
    }

    public void forceMapper(TypeToken<?> typeToken, Mapper mapper) {
        mappings.put(typeToken, Arrays.asList(mapper));
    }

    public void forceMapper(Class<?> type, Mapper mapper) {
        mappings.put(TypeToken.of(type), Arrays.asList(mapper));
    }

    protected MappingProvider(Mapper<Object> defaultMapper,
            Mapper<Collection<Object>> defaultCollectionMapper,
            Map<TypeToken<?>, List<Mapper>> mappings,
            NamingStrategy internalElementNamingStrategy,
            NamingStrategy attributeNamingStrategy) {
        this.defaultMapper = defaultMapper;
        this.defaultCollectionMapper = defaultCollectionMapper;
        this.internalElementNamingStrategy = internalElementNamingStrategy;
        this.attributeNamingStrategy = attributeNamingStrategy;
        this.mappings = mappings;
    }

    private void initializeMappings(List<Mapper<?>> mappers) {
        // can be extremely simplified as same interface cannot be implemented twice in one class!
        mappings = mappers.stream().flatMap(x -> TypeToken.of(x.getClass())
                .getTypes().stream()
                .filter(y -> Mapper.class.equals(y.getRawType()))
                .map(y -> new Object() {
            TypeToken<?> type = y.resolveType(Mapper.class.getTypeParameters()[0]);
            Mapper mapper = x;
        })).collect(groupingBy(
                x -> x.type,
                Collectors.mapping(x -> x.mapper, Collectors.toList())));
    }

    public Mapper<Object> getMapper(Object obj) {
        if (obj == null) {
            return getMapper(Object.class);
        }
        if (Type.class.isAssignableFrom(obj.getClass())) {
            return getMapper((Type) obj);
        }
        return getMapper(obj.getClass());
    }

    public Mapper<Object> getMapper(Type type) {
        Optional<List<Mapper>> customMapper = mappings.entrySet().stream()
                .filter(x -> x.getKey().isSupertypeOf(type))
                .sorted((x, y) -> Objects.compare(x.getKey(), y.getKey(), new TypeUtils.TypeTokenComparator()))
                .map(x -> x.getValue())
                .findFirst();
        if (!customMapper.isPresent() || customMapper.get().isEmpty()) {
            if (TypeToken.of(Collection.class).isSupertypeOf(type) && defaultCollectionMapper != null) {
                return (Mapper) defaultCollectionMapper;
            }
            return defaultMapper;
        }
        if (customMapper.get().size() > 1) {
            log.warn("found {} equally suitable mappers for type '{}'", customMapper.get().size(), type);
        }
        return customMapper.get().get(0);
    }

    public NamingStrategy getInternalElementNamingStrategy() {
        return internalElementNamingStrategy;
    }

    public NamingStrategy getAttributeNamingStrategy() {
        return attributeNamingStrategy;
    }
}
