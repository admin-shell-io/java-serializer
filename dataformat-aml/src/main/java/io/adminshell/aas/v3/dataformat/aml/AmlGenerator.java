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

import io.adminshell.aas.v3.dataformat.aml.aas2aml.Aas2AmlMappingContext;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AttributeType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXFile;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXObject;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleClassType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.SystemUnitClassType;
import io.adminshell.aas.v3.model.Referable;
import java.beans.PropertyDescriptor;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AmlGenerator {

    private static final Logger log = LoggerFactory.getLogger(AmlGenerator.class);
    private static final String DEFAULT_REF_SEMANTIC_PREFIX = "AAS:";
    private final String refSemanticPrefix;
    private CAEXObject.Builder current;
    private CAEXFile.Builder fileBuilder;
    private final AmlDocumentInfo documentInfo;

    private AmlGenerator(AmlDocumentInfo documentInfo, String refSemanticPrefix, CAEXFile.Builder fileBuilder, CAEXObject.Builder current) {
        this.documentInfo = documentInfo;
        this.refSemanticPrefix = refSemanticPrefix;
        this.fileBuilder = fileBuilder;
        this.current = current;
    }

    public AmlGenerator with(CAEXObject.Builder current) {
        return new AmlGenerator(documentInfo, refSemanticPrefix, fileBuilder, current);
    }

    public void addAdditionalInformation(List<Object> additionalInformation) {
        fileBuilder.addAdditionalInformation(additionalInformation);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String refSemanticPrefix = DEFAULT_REF_SEMANTIC_PREFIX;
        private CAEXObject.Builder current;
        private CAEXFile.Builder fileBuilder = CAEXFile.builder();
        private AmlDocumentInfo documentInfo = new AmlDocumentInfo();

        public AmlGenerator build() {
            return new AmlGenerator(documentInfo, refSemanticPrefix, fileBuilder, current);
        }

        public Builder refSemanticPrefix(String value) {
            this.refSemanticPrefix = value;
            return this;
        }

        public Builder current(CAEXObject.Builder value) {
            this.current = value;
            return this;
        }

        public Builder file(CAEXFile.Builder value) {
            this.fileBuilder = value;
            return this;
        }

        public Builder documentInfo(AmlDocumentInfo value) {
            this.documentInfo = value;
            return this;
        }
    }

    public void add(CAEXObject caexObject) {
        if (caexObject == null) {
            return;
        }
        if (AttributeType.class.isAssignableFrom(caexObject.getClass())) {
            addAttribute((AttributeType) caexObject);
        } else if (InternalElementType.class.isAssignableFrom(caexObject.getClass())) {
            addInternalElement((InternalElementType) caexObject);
        } else {
            log.warn("adding caex object failed because unsupported type '{}'", caexObject.getClass());
        }
    }

    public void addAttribute(AttributeType attribute) {
        if (attribute == null) {
            return;
        }
        if (AttributeType.Builder.class.isAssignableFrom(current.getClass())) {
            AttributeType.Builder builder = (AttributeType.Builder) current;
            builder.addAttribute(attribute);
        } else if (InternalElementType.Builder.class.isAssignableFrom(current.getClass())) {
            InternalElementType.Builder builder = (InternalElementType.Builder) current;
            builder.addAttribute(attribute);
        } else {
            log.warn("adding attribute failed because no parent builder defined");
        }
    }

    public void addInternalLink(SystemUnitClassType.InternalLink internalLink) {
        if (internalLink == null) {
            return;
        }
        if (InternalElementType.Builder.class.isAssignableFrom(current.getClass())) {
            InternalElementType.Builder builder = (InternalElementType.Builder) current;
            builder.addInternalLink(internalLink);
        } else {
            log.warn("adding internal link failed because no parent builder defined");
        }
    }

    public void addExternalInterface(RoleClassType.ExternalInterface externalInterface) {
        if (externalInterface == null) {
            return;
        }
        if (InternalElementType.Builder.class.isAssignableFrom(current.getClass())) {
            InternalElementType.Builder builder = (InternalElementType.Builder) current;
            builder.addExternalInterface(externalInterface);
        } else {
            log.warn("adding external interface failed because no parent builder defined");
        }
    }

    public void addSystemUnitClassLib(CAEXFile.SystemUnitClassLib systemUnitClassLib) {
        if (systemUnitClassLib == null) {
            return;
        }
        if (fileBuilder != null) {
            fileBuilder = fileBuilder.addSystemUnitClassLib(systemUnitClassLib);
        }
    }

    public void addInstanceHierarchy(CAEXFile.InstanceHierarchy instanceHierarchy) {
        if (instanceHierarchy == null) {
            return;
        }
        if (fileBuilder != null) {
            fileBuilder = fileBuilder.addInstanceHierarchy(instanceHierarchy);
        }
    }

    public void addInternalElement(InternalElementType internalElement) {
        if (internalElement == null) {
            return;
        }
        if (InternalElementType.Builder.class.isAssignableFrom(current.getClass())) {
            InternalElementType.Builder builder = (InternalElementType.Builder) current;
            builder.addInternalElement(internalElement);
        } else if (CAEXFile.InstanceHierarchy.Builder.class.isAssignableFrom(current.getClass())) {
            CAEXFile.InstanceHierarchy.Builder builder = (CAEXFile.InstanceHierarchy.Builder) current;
            builder.addInternalElement(internalElement);
        } else {
            log.warn("adding internalElement failed because no parent builder defined");
        }
    }

    public void appendReferenceTargetInterfaceIfRequired(Object obj, Aas2AmlMappingContext context) {
        RoleClassType.ExternalInterface referenceTargetInterface = getReferenceTargetInterface(obj, context);
        if (referenceTargetInterface != null) {
            addExternalInterface(referenceTargetInterface);
        }
    }

    public RoleClassType.ExternalInterface getReferenceTargetInterface(Object obj, Aas2AmlMappingContext context) {
        RoleClassType.ExternalInterface result = null;
        if (obj != null && Referable.class.isAssignableFrom(obj.getClass())) {
            Referable referable = (Referable) obj;
            if (context.isTargetOfInternalLink(referable)) {
                result = RoleClassType.ExternalInterface.builder()
                        .withID(context.generateId())
                        .withName("externalReferenceTarget")
                        .withRefBaseClassPath("AutomationMLInterfaceClassLib/AutomationMLBaseInterface")
                        .build();
            }
        }
        return result;
    }

    public InternalElementType.RoleRequirements roleRequirement(String value) {
        return InternalElementType.RoleRequirements.builder()
                .withRefBaseRoleClassPath(documentInfo.getAssetAdministrationShellRoleClassLib() + "/" + value)
                .build();
    }

    public AttributeType.RefSemantic refSemantic(Class<?> type, String propertyName) {
        return AttributeType.RefSemantic.builder()
                .withCorrespondingAttributePath(refSemanticPrefix + type.getSimpleName() + "/" + propertyName)
                .build();
    }

    public AttributeType.RefSemantic refSemantic(PropertyDescriptor property) {
        return refSemantic(property.getReadMethod().getDeclaringClass(), property.getName());
    }

    public String refBaseSystemUnitPath(Object value, Aas2AmlMappingContext context) {
        return documentInfo.getAssetAdministrationShellSystemUnitClassLib()
                + "/" + context.getInternalElementNamingStrategy().getName(
                        value.getClass(),
                        value,
                        null);
    }

    public AmlDocumentInfo getDocumentInfo() {
        return documentInfo;
    }
}
