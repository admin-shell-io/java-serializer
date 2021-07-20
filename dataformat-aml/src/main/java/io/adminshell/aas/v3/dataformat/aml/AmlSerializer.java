/*
 * Copyright (c) 2021 Fraunhofer-Gesellschaft zur Foerderung der angewandten Forschung e. V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.adminshell.aas.v3.dataformat.aml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.adminshell.aas.v3.dataformat.SerializationException;
import io.adminshell.aas.v3.dataformat.Serializer;
import io.adminshell.aas.v3.dataformat.aml.model.caex.*;
import io.adminshell.aas.v3.dataformat.aml.model.mixin.*;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.AASEnvironmentMapper;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.Submodel;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class AmlSerializer implements Serializer {

    private static final Logger log = LoggerFactory.getLogger(AmlSerializer.class);

    private final MapperFacade mapper = new AASEnvironmentMapper();

    @Override
    public String write(AssetAdministrationShellEnvironment aasEnvironment) throws SerializationException {
        try {
            CAEXFile caexFile = modelTransformation(aasEnvironment);
            String xml = convertXml(caexFile);
            String result = cleanUpXml(xml);
            return result;
        } catch (JsonProcessingException ex) {
            throw new SerializationException("error serializing AssetAdministrationShellEnvironment", ex);
        }
    }

    private CAEXFile modelTransformation(AssetAdministrationShellEnvironment environment) {
        // Map environment to CAEX file
        CAEXFile caexFile = mapper.map(environment, CAEXFile.class);
        caexFile.setSchemaVersion(CAEXConstants.SCHEMA_VERSION);
        caexFile.setXmlns(CAEXConstants.XMLNS);
        caexFile.setXsi(CAEXConstants.XSI);

        // Add additional information to CAEX
        AdditionalInformation additionalInformation = new AdditionalInformation();
        additionalInformation.setAutomationMLVersion(CAEXConstants.AUTOMATION_ML_VERSION);
        caexFile.setAdditionalInformation(additionalInformation);

        // Add AssetAdministrationShell instance hierarchy to CAEX
        InstanceHierarchy instanceHierarchy = new InstanceHierarchy();
        instanceHierarchy.setName(CAEXConstants.AAS_INSTANCE_HIERARCHY);
        instanceHierarchy.setVersion(CAEXConstants.AAS_INSTANCE_HIERARCHY_VERSION);
        caexFile.setInstanceHierarchy(instanceHierarchy);

        // Map all asset administration shells to internal elements
        List<AssetAdministrationShell> administrationShells = new ArrayList<>(environment.getAssetAdministrationShells());
        List<InternalElement> shellInternalElements = mapper.mapAsList(administrationShells, InternalElement.class);
        instanceHierarchy.setInternalElements(shellInternalElements);

        // Map all submodels to internal elements
        List<Submodel> submodels = new ArrayList<>(environment.getSubmodels());
        List<InternalElement> submodelInternalElements = mapper.mapAsList(submodels, InternalElement.class);
        instanceHierarchy.getInternalElements().addAll(submodelInternalElements);

        return caexFile;
    }

    private String convertXml(CAEXFile caexFile) throws JsonProcessingException {
        caexFile.setInterfaceClassLibs(new ArrayList<>(){{
            add(readClassLib("interface-class-lib_aas.automl"));
            add(readClassLib("interface-class-lib_automl_interface.automl"));
            add(readClassLib("interface-class-lib_automl_bpr.automl"));
        }});

        caexFile.setRoleClassLibs(new ArrayList<>(){{
            add(readClassLib("role-class-lib_aas.automl"));
            add(readClassLib("role-class-lib_automl_base.automl"));
            add(readClassLib("role-class-lib_automl_bpr.automl"));
        }});

        caexFile.setSystemUnitClassLibs(new ArrayList<>(){{
            add(readClassLib("system-unit-class-lib_aas.automl"));
            add(readClassLib("system-unit-class-lib_aas_data_specification_templates.automl"));
        }});

        XmlMapper xmlMapper = XmlMapper.builder()
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .addMixIn(CAEXFile.class, CAEXMixin.class)
                .addMixIn(ExternalInterface.class, ExternalInterfaceMixin.class)
                .addMixIn(AdditionalInformation.class, AdditionalInformationMixin.class)
                .addMixIn(InstanceHierarchy.class, InstanceHierarchyMixin.class)
                .addMixIn(InternalElement.class, InternalElementMixin.class)
                .addMixIn(Attribute.class, AttributeMixin.class)
                .addMixIn(RefSemantic.class, RefSemanticMixin.class)
                .addMixIn(RoleRequirements.class, RoleRequirementsMixin.class)
                .build();

        return xmlMapper.writeValueAsString(caexFile);
    }

    private String readClassLib(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        String content = null;
        try {
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private String cleanUpXml(String xml) {
        String withoutEmptyAttributes = xml.replaceAll("<Attribute/>", "");
        String withoutEmptyLines = withoutEmptyAttributes.replaceAll("(?m)^[ \t]*\r?\n", "");
        return withoutEmptyLines;
    }
}
