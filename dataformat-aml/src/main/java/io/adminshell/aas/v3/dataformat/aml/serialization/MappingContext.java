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
package io.adminshell.aas.v3.dataformat.aml.serialization;

import io.adminshell.aas.v3.dataformat.aml.common.naming.NamingStrategy;
import io.adminshell.aas.v3.dataformat.aml.util.ReferencedReferableCollector;
import io.adminshell.aas.v3.dataformat.core.util.AasUtils;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import io.adminshell.aas.v3.dataformat.mapping.MappingProvider;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.dataformat.aml.common.AbstractMappingContext;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.adminshell.aas.v3.model.Reference;
import java.util.Set;

public class MappingContext extends AbstractMappingContext<Mapper> {

    private static final Logger log = LoggerFactory.getLogger(MappingContext.class);
    private final AssetAdministrationShellEnvironment environment;
    private final Set<Reference> referencedReferables;

    public MappingContext(MappingProvider mappingProvider,
            NamingStrategy internalElementNamingStrategy,
            NamingStrategy attributeNamingStrategy,
            AssetAdministrationShellEnvironment environment) {
        this(mappingProvider,
                internalElementNamingStrategy,
                attributeNamingStrategy,
                environment,
                null,
                null);
    }

    private MappingContext(MappingProvider mappingProvider,
            NamingStrategy classNamingStrategy,
            NamingStrategy propertyNamingStrategy,
            AssetAdministrationShellEnvironment environment,
            Set<Reference> referencedReferables,
            PropertyDescriptor property) {
        super(mappingProvider, classNamingStrategy, propertyNamingStrategy, property);
        this.environment = environment;
        if (referencedReferables == null) {
            this.referencedReferables = new ReferencedReferableCollector(environment).collect();
        } else {
            this.referencedReferables = referencedReferables;
        }
    }

    public boolean isTargetOfInternalLink(Reference targetRef) {
        return referencedReferables.stream().anyMatch(x -> AasUtils.sameAs(x, targetRef));
    }

    public <T> void map(T value, AmlGenerator generator) throws MappingException {
        mappingProvider.getMapper(value).map(value, generator, this);
    }

    public <T> void map(Type type, T value, AmlGenerator generator) throws MappingException {
        mappingProvider.getMapper(type).map(value, generator, this);
    }

    public MappingContext with(PropertyDescriptor property) {
        return new MappingContext(
                getMappingProvider(),
                classNamingStrategy,
                propertyNamingStrategy,
                environment,
                referencedReferables,
                property);
    }

    public MappingContext withoutProperty() {
        return new MappingContext(
                mappingProvider,
                classNamingStrategy,
                propertyNamingStrategy,
                environment,
                referencedReferables,
                null);
    }

    public PropertyDescriptor getProperty() {
        return property;
    }

    public AssetAdministrationShellEnvironment getEnvironment() {
        return environment;
    }
}
