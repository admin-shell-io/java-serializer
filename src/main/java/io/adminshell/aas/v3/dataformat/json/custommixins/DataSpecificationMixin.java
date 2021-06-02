package io.adminshell.aas.v3.dataformat.json.custommixins;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("DataSpecification")
// TODO how to handle this in generator? It is the only case where typeinfo is not de-/serialized
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
public interface DataSpecificationMixin {

}
