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

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.adminshell.aas.v3.model.AssetInformation;
import io.adminshell.aas.v3.model.Reference;
import io.adminshell.aas.v3.model.View;

@JsonPropertyOrder({ "extensions", "idShort", "displayNames", "category", "descriptions", "administration",
	"identification", "dataSpecifications", "security", "derivedFrom", "submodels",
	"assetInformation", "views" })
public interface AssetAdministrationShellMixin {
	
	@JacksonXmlProperty(localName = "aas:derivedFrom")
	public Reference getDerivedFrom();

    @JacksonXmlProperty(localName = "aas:submodelRef")
    @JacksonXmlElementWrapper(localName = "aas:submodelRefs")
    public List<Reference> getSubmodels();
    
    @JacksonXmlProperty(localName = "aas:view")
    @JacksonXmlElementWrapper(localName = "aas:views")
    public List<View> getViews();

    @JacksonXmlProperty(localName = "aas:assetInformation")
    public AssetInformation getAssetInformation();
}
