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
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.dataformat.Deserializer;
import io.adminshell.aas.v3.dataformat.aml.model.caex.AdditionalInformation;
import io.adminshell.aas.v3.dataformat.aml.model.caex.Attribute;
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
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AmlDeserializer implements Deserializer {

    private static final Logger log = LoggerFactory.getLogger(AmlDeserializer.class);
    private final MapperFacade mapper = new AASEnvironmentMapper();

    public CAEXMixin convertXMLToCAEX(String xml) {
        XmlMapper xmlMapper = XmlMapper.builder()
                //                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .addMixIn(CAEXFile.class, CAEXMixin.class)
                .addMixIn(ExternalInterface.class, ExternalInterfaceMixin.class)
                .addMixIn(AdditionalInformation.class, AdditionalInformationMixin.class)
                .addMixIn(InstanceHierarchy.class, InstanceHierarchyMixin.class)
                .addMixIn(InternalElement.class, InternalElementMixin.class)
                .addMixIn(Attribute.class, AttributeMixin.class)
                .addMixIn(RefSemantic.class, RefSemanticMixin.class)
                .addMixIn(RoleRequirements.class, RoleRequirementsMixin.class)
                .build();

        CAEXMixin caex = null;
        try {
            caex = xmlMapper.readValue(xml, CAEXMixin.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //log.debug(caex.toString());
        //System.out.println(JSON.);
        return caex;
    }

    public AssetAdministrationShellEnvironment modelTransformation(CAEXFile caex) {
        AssetAdministrationShellEnvironment environment = null;

        return environment;
    }

    @Override
    public AssetAdministrationShellEnvironment read(String value) throws DeserializationException {
        // XML --> CAEX
        CAEXMixin caex = this.convertXMLToCAEX(value);
        // CAEX --> AAS
//        return this.modelTransformation(caex);
        return null;
    }

    @Override
    public <T> void useImplementation(Class<T> aasInterface, Class<? extends T> implementation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
