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
import io.adminshell.aas.v3.dataformat.aml.model.caex.AttributeType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXFile;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXFile.InstanceHierarchy;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXFile.SystemUnitClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleClassType;
import io.adminshell.aas.v3.dataformat.aml.util.ReferencedReferableCollector;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.Referable;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MappingContext {

    private static final String EMPTY_STRING = "";
    private static final Logger log = LoggerFactory.getLogger(MappingContext.class);
    private IdentityProvider identityProvider;
    private final MappingProvider mappingProvider;
    private final AssetAdministrationShellEnvironment environment;
    private CAEXFile.Builder fileBuilder;
    private final InstanceHierarchy.Builder hierarchyBuilder;
    private final InternalElementType.Builder internalElementBuilder;
    private final AttributeType.Builder attributeBuilder;
    private final PropertyDescriptor property;
    private final Map<Referable, String> referecedReferableIDs;

    public MappingContext(MappingProvider mappingProvider, AssetAdministrationShellEnvironment environment) {
        this(mappingProvider, null, environment, null, null, null, null, null);
    }

    public String getInternalLinkTargetId(Referable target) {
        if (!isTargetOfInternalLink(target)) {
            referecedReferableIDs.put(target, identityProvider.getCachedId(target));
        }
        return referecedReferableIDs.get(target);
    }

    public boolean isTargetOfInternalLink(Referable target) {
        return referecedReferableIDs.containsKey(target) && !referecedReferableIDs.get(target).equals(EMPTY_STRING);
    }

    public void appendReferenceTargetInterfaceIfRequired(Object obj) {
        RoleClassType.ExternalInterface referenceTargetInterface = getReferenceTargetInterface(obj);
        if (referenceTargetInterface != null) {
            internalElementBuilder.addExternalInterface(referenceTargetInterface);
        }
    }

    public RoleClassType.ExternalInterface getReferenceTargetInterface(Object obj) {
        RoleClassType.ExternalInterface result = null;
        if (obj != null && Referable.class.isAssignableFrom(obj.getClass())) {
            Referable referable = (Referable) obj;
            if (isTargetOfInternalLink(referable)) {
                result = RoleClassType.ExternalInterface.builder()
                        .withID(identityProvider.generateId())
                        .withName("externalReferenceTarget")
                        .withRefBaseClassPath("AutomationMLInterfaceClassLib/AutomationMLBaseInterface")
                        .build();
            }
        }
        return result;
    }

    public MappingContext(MappingProvider mappingProvider,
            IdentityProvider identityProvider,
            AssetAdministrationShellEnvironment environment,
            Map<Referable, String> referecedReferableIDs,
            InstanceHierarchy.Builder hierarchyBuilder,
            InternalElementType.Builder internalElementBuilder, AttributeType.Builder attributeBuilder,
            PropertyDescriptor property) {
        if (identityProvider == null) {
            this.identityProvider = new IdentityProvider();
        } else {
            this.identityProvider = identityProvider;
        }
        if (referecedReferableIDs == null) {
            this.referecedReferableIDs = new ReferencedReferableCollector(environment).collect().stream()
                    .collect(Collectors.toMap(x -> x, x -> EMPTY_STRING));
        } else {
            this.referecedReferableIDs = referecedReferableIDs;
        }
        this.fileBuilder = CAEXFile.builder();
        this.mappingProvider = mappingProvider;
        this.environment = environment;
        this.hierarchyBuilder = hierarchyBuilder;
        this.internalElementBuilder = internalElementBuilder;
        this.attributeBuilder = attributeBuilder;
        this.property = property;
    }

    public void addAttribute(AttributeType attribute) {
        if (attributeBuilder != null) {
            attributeBuilder.addAttribute(attribute);
        } else if (internalElementBuilder != null) {
            internalElementBuilder.addAttribute(attribute);
        } else {
            log.warn("adding attribute failed because no parent builder defined");
        }
    }

    public void addSystemUnitClassLib(SystemUnitClassLib systemUnitClassLib) {
        if (fileBuilder != null) {
            fileBuilder = fileBuilder.withSystemUnitClassLib(systemUnitClassLib);
        }
    }

    public void addInternalElement(InternalElementType internalElement) {
        if (internalElementBuilder != null) {
            internalElementBuilder.addInternalElement(internalElement);
        } else if (hierarchyBuilder != null) {
            hierarchyBuilder.addInternalElement(internalElement);
        } else {
            log.warn("adding internalElement failed because no parent builder defined");
        }
    }

    public <T> void map(T value) throws MappingException {
        mappingProvider.getMapper(value).map(value, this);
    }

    public <T> void map(Type type, T value) throws MappingException {
        mappingProvider.getMapper(type).map(value, this);
    }

    public MappingContext with(InstanceHierarchy.Builder hierarchyBuilder) {
        return new MappingContext(mappingProvider, identityProvider, environment, referecedReferableIDs, hierarchyBuilder, internalElementBuilder, attributeBuilder, property);
    }

    public MappingContext with(InternalElementType.Builder internalElementBuilder) {
        return new MappingContext(mappingProvider, identityProvider, environment, referecedReferableIDs, hierarchyBuilder, internalElementBuilder, attributeBuilder, property);
    }

    public MappingContext with(AttributeType.Builder attributeBuilder) {
        return new MappingContext(mappingProvider, identityProvider, environment, referecedReferableIDs, hierarchyBuilder, internalElementBuilder, attributeBuilder, property);
    }

    public MappingContext with(PropertyDescriptor property) {
        return new MappingContext(mappingProvider, identityProvider, environment, referecedReferableIDs, hierarchyBuilder, internalElementBuilder, attributeBuilder, property);
    }

    public MappingContext withoutProperty() {
        return new MappingContext(mappingProvider, identityProvider, environment, referecedReferableIDs, hierarchyBuilder, internalElementBuilder, attributeBuilder, null);
    }

    public MappingContext withoutIdentidyProvider() {
        return new MappingContext(mappingProvider, new IdentityProvider(), environment, referecedReferableIDs, hierarchyBuilder, internalElementBuilder, attributeBuilder, null);
    }

    public void resetIdentityProvider() {
        this.identityProvider = new IdentityProvider();
    }

    public void etIdentityProvider(IdentityProvider identityProvider) {
        this.identityProvider = identityProvider;
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

    public CAEXFile.Builder getFileBuilder() {
        return fileBuilder;
    }

    public IdentityProvider getIdentityProvider() {
        return identityProvider;
    }

    public InstanceHierarchy.Builder getHierarchyBuilder() {
        return hierarchyBuilder;
    }

    public InternalElementType.Builder getInternalElementBuilder() {
        return internalElementBuilder;
    }

    public AttributeType.Builder getAttributeBuilder() {
        return attributeBuilder;
    }

    public Map<Referable, String> getReferecedReferableIDs() {
        return referecedReferableIDs;
    }

}
