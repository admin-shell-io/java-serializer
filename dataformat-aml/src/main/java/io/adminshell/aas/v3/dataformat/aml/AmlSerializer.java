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
import io.adminshell.aas.v3.dataformat.aml.model.caex.AdditionalInformation;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXConstants;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXFile;
import io.adminshell.aas.v3.dataformat.aml.model.caex.ExternalInterface;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InstanceHierarchy;
import io.adminshell.aas.v3.dataformat.aml.model.caex.InternalElement;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RefSemantic;
import io.adminshell.aas.v3.dataformat.aml.model.caex.RoleRequirements;
import io.adminshell.aas.v3.dataformat.aml.model.mixin.AdditionalInformationMixin;
import io.adminshell.aas.v3.dataformat.aml.model.mixin.AttributeMixin;
import io.adminshell.aas.v3.dataformat.aml.model.mixin.CAEXMixin;
import io.adminshell.aas.v3.dataformat.aml.model.mixin.ExternalInterfaceMixin;
import io.adminshell.aas.v3.dataformat.aml.model.mixin.InstanceHierarchyMixin;
import io.adminshell.aas.v3.dataformat.aml.model.mixin.InternalElementMixin;
import io.adminshell.aas.v3.dataformat.aml.model.mixin.RefSemanticMixin;
import io.adminshell.aas.v3.dataformat.aml.model.mixin.RoleRequirementsMixin;
import io.adminshell.aas.v3.dataformat.aml.serialize.mapper.AASEnvironmentMapper;
import io.adminshell.aas.v3.model.AssetAdministrationShell;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.AssetInformation;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.SubmodelElementCollection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AmlSerializer implements Serializer {

    private static final Logger log = LoggerFactory.getLogger(AmlSerializer.class);
    private final MapperFacade mapper = new AASEnvironmentMapper();

    private boolean enableClassLibs = false;

    public AmlSerializer() {
    }

    public AmlSerializer(boolean enableClassLibs) {
        this.enableClassLibs = enableClassLibs;
    }

    @Override
    public String write(AssetAdministrationShellEnvironment aasEnvironment) throws SerializationException {
        try {
            CAEXFile caexFile = modelTransformation(aasEnvironment);
            String xml = convertXml(caexFile);
            String cleanUp = cleanUpXml(xml);
            String result = cleanUp;
            if (enableClassLibs) {
                result = addClassLibs(cleanUp);
            }
            return result;
        } catch (JsonProcessingException ex) {
            throw new SerializationException("error serializing AssetAdministrationShellEnvironment", ex);
        }
    }

    private String addClassLibs(String xml) {
        StringBuilder output = new StringBuilder(xml);
        // Read class lib file
        String path = "automation-ml-class-libs.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());
        String content = null;
        try {
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Delete CAEX closing tag
        if (output.length() > 0) {
            int last, prev = output.length() - 1;
            while ((last = output.lastIndexOf("\n", prev)) == prev) {
                prev = last - 1;
            }
            if (last >= 0) {
                output.delete(last, output.length());
            }
        }
        // Add class libs to xml
        if (content != null) {
            output.append(content);
        }
        // Add new CAEX closing tag
        output.append("</CAEXFile>");
        return output.toString();
    }

    private String cleanUpXml(String xml) {
        String withoutEmptyAttributes = xml.replaceAll("<Attribute/>", "");
        String withoutEmptyLines = withoutEmptyAttributes.replaceAll("(?m)^[ \t]*\r?\n", "");
        return withoutEmptyLines;
    }

    private String convertXml(CAEXFile caexFile) throws JsonProcessingException {
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

    private CAEXFile modelTransformation(AssetAdministrationShellEnvironment environment) {
        CAEXFile caexFile = mapper.map(environment, CAEXFile.class);
        caexFile.setSchemaVersion(CAEXConstants.SCHEMA_VERSION);
        caexFile.setXmlns(CAEXConstants.XMLNS);
        caexFile.setXsi(CAEXConstants.XSI);
        // Add additional information to CAEX
        AdditionalInformation additionalInformation = new AdditionalInformation();
        additionalInformation.setAutomationMLVersion(CAEXConstants.AUTOMATION_ML_VERSION);
        caexFile.setAdditionalInformation(additionalInformation);
        // Add instance hierarchy to CAEX
        InstanceHierarchy instanceHierarchy = new InstanceHierarchy();
        instanceHierarchy.setName("AssetAdministrationShellInstanceHierarchy");
        instanceHierarchy.setVersion("0");
        caexFile.setInstanceHierarchy(instanceHierarchy);
        // Map all asset administration shells to internal elements
        List<AssetAdministrationShell> administrationShells = new ArrayList<>(environment.getAssetAdministrationShells());
        List<InternalElement> shellInternalElements = mapper.mapAsList(administrationShells, InternalElement.class);
        // Inside shell loop
        forEachWithCounter(administrationShells, (i, administrationShell) -> {
            // Map asset info to internal element
//            AssetInformation asset = administrationShell.getAssetInformation();
//            InternalElement assetInternalElement = mapper.map(asset, InternalElement.class);
//            shellInternalElements.get(i).setInternalElements(new ArrayList<>() {
//                {
//                    add(assetInternalElement);
//                }
//            });

//            // Map view
//            List<InternalElement> viewInternalElements = mapper.mapAsList(administrationShell.getViews(), InternalElement.class);
//            shellInternalElements.addAll(viewInternalElements);

//            // Map all submodels to internal elements
//            forEachWithCounter(administrationShell.getSubmodels(), (j, submodel) -> {
//                InternalElement submodelInternalElement = mapper.map(submodel, InternalElement.class);
//                shellInternalElements.get(i).getInternalElements().add(submodelInternalElement);
//            });
        });
        // Inside environment loop
        shellInternalElements.forEach(shellInternalElement -> {
            // Map all submodels to internal elements
            List<Submodel> submodels = new ArrayList<>(environment.getSubmodels());
            submodels.forEach(submodel -> {
                InternalElement submodelInternalElement = mapper.map(submodel, InternalElement.class);
                submodelInternalElement.setInternalElements(new ArrayList<>());
                // Map all submodel elements
                submodel.getSubmodelElements().forEach(submodelElement -> {
                    // Submodels that are not SubmodelElementCollections
                    if (!(submodelElement instanceof SubmodelElementCollection)) {
                        InternalElement internalElement = mapper.map(submodelElement, InternalElement.class);
                        submodelInternalElement.getInternalElements().add(internalElement);
                    }
                    // SubmodelElementCollection
                    if (submodelElement instanceof SubmodelElementCollection) {
                        InternalElement submodelElementInternalElement = mapper.map(submodelElement, InternalElement.class);
                        submodelElementInternalElement.setInternalElements(new ArrayList<>());
                        ((SubmodelElementCollection) submodelElement).getValues().forEach(value -> {
                            InternalElement submodelElementCollectionInternalElement = mapper.map(value, InternalElement.class);
                            submodelElementInternalElement.getInternalElements().add(submodelElementCollectionInternalElement);
                        });
                        submodelInternalElement.getInternalElements().add(submodelElementInternalElement);
                    }
                });
                shellInternalElement.getInternalElements().add(submodelInternalElement);
            });
        });
        instanceHierarchy.setInternalElements(shellInternalElements);
        return caexFile;
    }

    private static <T> void forEachWithCounter(Iterable<T> source, BiConsumer<Integer, T> consumer) {
        int i = 0;
        for (T item : source) {
            consumer.accept(i, item);
            i++;
        }
    }

}
