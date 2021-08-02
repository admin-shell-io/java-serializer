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

import io.adminshell.aas.v3.dataformat.aml.model.caex.AttributeType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXFile;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXObject;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElementType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleClassType;
import io.adminshell.aas.v3.dataformat.aml.model.caex.SystemUnitClassType;
import io.adminshell.aas.v3.model.Referable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AmlGenerator {

    private static final Logger log = LoggerFactory.getLogger(AmlGenerator.class);
    private CAEXObject.Builder current;
    private CAEXFile.Builder fileBuilder;

    public AmlGenerator() {
        this.fileBuilder = CAEXFile.builder();
    }

    public AmlGenerator(CAEXFile.Builder fileBuilder) {
        this.fileBuilder = fileBuilder;
    }

    private AmlGenerator(CAEXFile.Builder fileBuilder, CAEXObject.Builder current) {
        this.fileBuilder = fileBuilder;
        this.current = current;
    }

    public AmlGenerator with(CAEXObject.Builder current) {
        return new AmlGenerator(fileBuilder, current);
    }

    public void addAdditionalInformation(List<Object> additionalInformation) {
        fileBuilder.addAdditionalInformation(additionalInformation);
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

    public void appendReferenceTargetInterfaceIfRequired(Object obj, MappingContext context) {
        RoleClassType.ExternalInterface referenceTargetInterface = getReferenceTargetInterface(obj, context);
        if (referenceTargetInterface != null) {
            addExternalInterface(referenceTargetInterface);
        }
    }

    public RoleClassType.ExternalInterface getReferenceTargetInterface(Object obj, MappingContext context) {
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
}
