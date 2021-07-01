/*
Copyright (c) 2021 Fraunhofer IOSB-INA Lemgo,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IOSB-ILT Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IAIS,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IESE,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IWU Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

This source code is licensed under the Apache License 2.0 (see LICENSE.txt).

This source code may use other Open Source software components (see LICENSE.txt).
*/

package io.adminshell.aas.v3.dataformat.xml.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.dataformat.xml.serialization.LangStringsAASSerializer;
import io.adminshell.aas.v3.model.LangString;

@JsonPropertyOrder({ "extensions", "idShort", "displayNames", "category", "descriptions" })
public interface ReferableMixin {

	@JacksonXmlProperty(localName = "aas:description")
	@JsonSerialize(using = LangStringsAASSerializer.class)
    public List<LangString> getDescriptions();
	
	@JacksonXmlProperty(localName = "aas:displayName")
	@JsonSerialize(using = LangStringsAASSerializer.class)
	public List<LangString> getDisplayNames();
	
	@JacksonXmlProperty(localName = "aas:category")
	public String getCategory();

	@JacksonXmlProperty(localName = "aas:idShort")
	@JsonInclude(JsonInclude.Include.ALWAYS)
    public String getIdShort();

}
