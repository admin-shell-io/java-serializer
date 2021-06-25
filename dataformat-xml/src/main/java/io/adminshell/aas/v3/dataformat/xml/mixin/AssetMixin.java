package io.adminshell.aas.v3.dataformat.xml.mixin;



import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "extensions", "idShort", "displayName", "category", "descriptions", "administration",
        "identification", "embeddedDataSpecification" })
public interface AssetMixin {

}
