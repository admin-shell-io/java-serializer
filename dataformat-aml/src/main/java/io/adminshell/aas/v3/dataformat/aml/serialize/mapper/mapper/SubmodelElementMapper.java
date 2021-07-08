package io.adminshell.aas.v3.dataformat.aml.serialize.mapper.mapper;

import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.util.QualifierConverterUtil;
import io.adminshell.aas.v3.model.SubmodelElement;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class SubmodelElementMapper extends CustomMapper<SubmodelElement, InternalElement> {

    @Override
    public void mapAtoB(SubmodelElement submodelElement, InternalElement internalElement, MappingContext context) {
        QualifierConverterUtil.createQualifierAttributesForSubmodelElement(submodelElement, internalElement);
    }
}
