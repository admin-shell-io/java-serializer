package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AASNamespace;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellRoleClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util.ReferenceConverterUtil;
import io.adminshell.aas.v3.model.RelationshipElement;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class RelationshipElementMapper extends CustomMapper<RelationshipElement, InternalElement> {

    @Override
    public void mapAtoB(RelationshipElement relationshipElement, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.RelationshipElementMapper.getRefBaseRoleClassPath()));

        internalElement.getAttributes().add(new Attribute(
                "first",
                null,
                null,
                ReferenceConverterUtil.convert(relationshipElement.getFirst()),
                new RefSemantic(AASNamespace.RelationshipElement_First.getRefSemantic()),
                null
        ));

        internalElement.getAttributes().add(new Attribute(
                "second",
                null,
                null,
                ReferenceConverterUtil.convert(relationshipElement.getSecond()),
                new RefSemantic(AASNamespace.RelationshipElement_Second.getRefSemantic()),
                null
        ));
    }
}
