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
