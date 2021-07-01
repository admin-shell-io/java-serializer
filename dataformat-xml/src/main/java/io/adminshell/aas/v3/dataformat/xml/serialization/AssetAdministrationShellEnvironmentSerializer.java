/*******************************************************************************
 * Copyright (C) 2021 the Eclipse BaSyx Authors
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/

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

package io.adminshell.aas.v3.dataformat.xml.serialization;

import java.io.IOException;
import java.util.List;

import javax.xml.namespace.QName;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.Submodel;

public class AssetAdministrationShellEnvironmentSerializer extends JsonSerializer<AssetAdministrationShellEnvironment> {

	private final static String[] AAS_NAMESPACE = { "xmlns:aas", "http://www.admin-shell.io/aas/3/0" };
	private final static String[] IEC61630_NAMESPACE = { "xmlns:IEC61360", "http://www.admin-shell.io/IEC61360/3/0" };
	private final static String[] XSI_NAMESPACE = { "xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance" };
	private final static String[] SCHEMA_LOCATION = { "xsi:schemaLocation", "http://www.admin-shell.io/aas/2/0 AAS.xsd http://www.admin-shell.io/IEC61360/2/0 IEC61360.xsd" };

	private final static String AASLIST_TAG = "aas:assetAdministrationShells";
	private final static String AAS_TAG = "aas:assetAdministrationShell";

	private final static String CONCEPTDICTIONARYLIST_TAG = "aas:conceptDescriptions";
	private final static String CONCEPTDICTIONARY_TAG = "aas:conceptDescription";

	private final static String SUBMODELLIST_TAG = "aas:submodels";
	private final static String SUBMODEL_TAG = "aas:submodel";

	@Override
	public void serialize(AssetAdministrationShellEnvironment value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		ToXmlGenerator xgen = (ToXmlGenerator) gen;

		writeOpeningTag(xgen);
		writeContent(value, xgen);
		closeOpeningTag(xgen);
	}

	private void writeOpeningTag(ToXmlGenerator xgen) throws IOException {
		xgen.setNextName(new QName("aas:aasenv"));
		xgen.writeStartObject();

		writeAttribute(xgen, AAS_NAMESPACE[0], AAS_NAMESPACE[1]);
		writeAttribute(xgen, IEC61630_NAMESPACE[0], IEC61630_NAMESPACE[1]);
		writeAttribute(xgen, XSI_NAMESPACE[0], XSI_NAMESPACE[1]);
		writeAttribute(xgen, SCHEMA_LOCATION[0], SCHEMA_LOCATION[1]);
	}

	private void writeAttribute(ToXmlGenerator xgen, String fieldName, String value) throws IOException {
		xgen.setNextIsAttribute(true);
		xgen.writeFieldName(fieldName);
		xgen.writeString(value);
		xgen.setNextIsAttribute(false);
	}

	private void writeContent(AssetAdministrationShellEnvironment value, ToXmlGenerator xgen) throws IOException {
		writeAssetAdministrationShells(xgen, value.getAssetAdministrationShells());
		writeConceptDescriptions(xgen, value.getConceptDescriptions());
		writeSubmodels(xgen, value.getSubmodels());
	}

	private void writeAssetAdministrationShells(ToXmlGenerator xgen, List<AssetAdministrationShell> aasList) throws IOException {
		writeWrappedArray(xgen, AASLIST_TAG, AAS_TAG, aasList);
	}

	private void writeConceptDescriptions(ToXmlGenerator xgen, List<ConceptDescription> conceptDescriptions) throws IOException {
		if (conceptDescriptions.size() == 0) {
			return;
		}

		writeWrappedArray(xgen, CONCEPTDICTIONARYLIST_TAG, CONCEPTDICTIONARY_TAG, conceptDescriptions);
	}

	private void writeSubmodels(ToXmlGenerator xgen, List<Submodel> submodels) throws IOException {
		if (submodels.size() == 0) {
			return;
		}

		writeWrappedArray(xgen, SUBMODELLIST_TAG, SUBMODEL_TAG, submodels);
	}

	private void writeWrappedArray(ToXmlGenerator xgen, String wrapper, String wrapped, List<?> list) throws IOException {
		xgen.writeFieldName(wrapper);

		QName qWrapper = new QName(wrapper);
		QName qWrapped = new QName(wrapped);

		xgen.writeStartArray();
		xgen.startWrappedValue(qWrapper, qWrapped);
		for (Object aas : list) {
			xgen.writeObject(aas);
		}
		xgen.finishWrappedValue(qWrapper, qWrapped);
		xgen.writeEndArray();
	}

	private void closeOpeningTag(ToXmlGenerator xgen) throws IOException {
		xgen.writeEndObject();
	}

}
