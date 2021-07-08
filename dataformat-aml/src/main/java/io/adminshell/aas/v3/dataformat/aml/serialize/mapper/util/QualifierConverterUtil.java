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
package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AASNamespace;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import io.adminshell.aas.v3.model.Constraint;
import io.adminshell.aas.v3.model.Qualifier;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.SubmodelElement;
import java.util.ArrayList;

public class QualifierConverterUtil {

    public static void createQualifierAttributesForSubmodelElement(SubmodelElement submodelElement, InternalElement internalElement) {
        if (submodelElement.getQualifiers() == null) {
            return;
        }
        submodelElement.getQualifiers().forEach(qualifier -> {
            createAttributes(qualifier, internalElement);
        });
    }

    public static void createQualifierAttributesForSubmodel(Submodel submodel, InternalElement internalElement) {
        if (submodel.getQualifiers() == null) {
            return;
        }
        submodel.getQualifiers().forEach(qualifier -> {
            createAttributes(qualifier, internalElement);
        });
    }

    private static void createAttributes(Constraint constraint, InternalElement internalElement) {
        if (constraint instanceof Qualifier) {

            // Root qualifier attribute
            Attribute rootAttribute = new Attribute(
                    String.format("qualifier:%s=%s",
                            ((Qualifier) constraint).getType(),
                            ((Qualifier) constraint).getValue()),
                    null,
                    null,
                    null,
                    new RefSemantic(AASNamespace.Qualifier_Qualifier.getRefSemantic()),
                    null
            );
            rootAttribute.setAttributes(new ArrayList<>());

            // Nested attributes
            rootAttribute.getAttributes().add(new Attribute(
                    "type",
                    null,
                    null,
                    "xs:" + ((Qualifier) constraint).getValueType(),
                    new RefSemantic(AASNamespace.Qualifier_Type.getRefSemantic()),
                    null
            )
            );
            rootAttribute.getAttributes().add(new Attribute(
                    "value",
                    null,
                    null,
                    ((Qualifier) constraint).getValue(),
                    new RefSemantic(AASNamespace.Qualifier_Value.getRefSemantic()),
                    null
            )
            );
            rootAttribute.getAttributes().add(new Attribute(
                    "semanticId",
                    null,
                    null,
                    ReferenceConverterUtil.convert(((Qualifier) constraint).getSemanticId()),
                    new RefSemantic(AASNamespace.HasSemantics_SemanticId.getRefSemantic()),
                    null
            ));

            internalElement.getAttributes().add(rootAttribute);
        }
    }
}
