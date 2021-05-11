package de.fraunhofer.iais.eis.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = JsonAasEnumSerializer.class)
public enum EnumMixin {
}
