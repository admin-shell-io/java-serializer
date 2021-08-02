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

import io.adminshell.aas.v3.dataformat.aml.mapper.MappingException;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleClassType;
import io.adminshell.aas.v3.dataformat.aml.util.ReferencedReferableCollector;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.Referable;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MappingContext {

    private static final String EMPTY_STRING = "";
    private static final Logger log = LoggerFactory.getLogger(MappingContext.class);
    private final MappingProvider mappingProvider;
    private final IdGenerator idGenerator;
    private final AssetAdministrationShellEnvironment environment;
    private final Map<Object, String> idCache;

    private final PropertyDescriptor property;
    private final Map<Referable, String> referecedReferableIDs;

    public MappingContext(MappingProvider mappingProvider,
            IdGenerator idGenerator,
            AssetAdministrationShellEnvironment environment) {
        this(mappingProvider, idGenerator, environment, null, null, null);
    }

    private MappingContext(MappingProvider mappingProvider,
            IdGenerator idGenerator,
            AssetAdministrationShellEnvironment environment,
            Map<Referable, String> referecedReferableIDs,
            Map<Object, String> idCache,
            PropertyDescriptor property) {
        this.mappingProvider = mappingProvider;
        this.idGenerator = idGenerator;
        this.environment = environment;
        this.property = property;
        if (referecedReferableIDs == null) {
            this.referecedReferableIDs = new ReferencedReferableCollector(environment).collect().stream()
                    .collect(Collectors.toMap(x -> x, x -> EMPTY_STRING));
        } else {
            this.referecedReferableIDs = referecedReferableIDs;
        }
        if (idCache == null) {
            this.idCache = new HashMap<>();
        } else {
            this.idCache = idCache;
        }
    }

    public String getInternalLinkTargetId(Referable target) {
        if (!isTargetOfInternalLink(target)) {
            referecedReferableIDs.put(target, getCachedId(target));
        }
        return referecedReferableIDs.get(target);
    }

    public String generateId() {
        return idGenerator.generateId();
    }

    public String getCachedId(Object obj) {
        if (idCache.containsKey(obj)) {
            return idCache.get(obj);
        }
        String result = idGenerator.generateId();
        idCache.put(obj, result);
        return result;
    }

    public boolean isTargetOfInternalLink(Referable target) {
        return referecedReferableIDs.containsKey(target) && !referecedReferableIDs.get(target).equals(EMPTY_STRING);
    }

    public <T> void map(T value, AmlGenerator generator) throws MappingException {
        mappingProvider.getMapper(value).map(value, generator, this);
    }

    public <T> void map(Type type, T value, AmlGenerator generator) throws MappingException {
        mappingProvider.getMapper(type).map(value, generator, this);
    }

//
//    public <T> void map(T value) throws MappingException {
//        mappingProvider.getMapper(value).map(value, this);
//    }
//
//    public <T> void map(Type type, T value) throws MappingException {
//        mappingProvider.getMapper(type).map(value, this);
//    }
    public MappingContext with(PropertyDescriptor property) {
        return new MappingContext(mappingProvider, idGenerator, environment, referecedReferableIDs, idCache, property);
    }

    public MappingContext withoutProperty() {
        return new MappingContext(mappingProvider, idGenerator, environment, referecedReferableIDs, idCache, null);
    }

    public MappingContext withoutIdCache() {
        return new MappingContext(mappingProvider, idGenerator, environment, referecedReferableIDs, null, property);
    }

    public PropertyDescriptor getProperty() {
        return property;
    }

    public AssetAdministrationShellEnvironment getEnvironment() {
        return environment;
    }

    public MappingProvider getMappingProvider() {
        return mappingProvider;
    }

    public Map<Referable, String> getReferecedReferableIDs() {
        return referecedReferableIDs;
    }

}
