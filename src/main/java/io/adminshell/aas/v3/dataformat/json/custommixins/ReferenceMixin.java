package io.adminshell.aas.v3.dataformat.json.custommixins;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Reference")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "modelType")
public interface ReferenceMixin {

}
