package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.AASNamespace;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AssetAdministrationShellRoleClassLib;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.model.Range;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class RangeMapper extends CustomMapper<Range, InternalElement> {

    @Override
    public void mapAtoB(Range range, InternalElement internalElement, MappingContext context) {
        internalElement.setRoleRequirements(new RoleRequirements(AssetAdministrationShellRoleClassLib.Range.getRefBaseRoleClassPath()));
        internalElement.getAttributes().add(new Attribute(
                "max",
                "xs:" + range.getValueType(),
                null,
                range.getMax(),
                new RefSemantic(AASNamespace.Range_Max.getRefSemantic()),
                null
        ));
        internalElement.getAttributes().add(new Attribute(
                "min",
                "xs:" + range.getValueType(),
                null,
                range.getMin(),
                new RefSemantic(AASNamespace.Range_Min.getRefSemantic()),
                null
        ));
    }
}
