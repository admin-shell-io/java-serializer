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
package io.adminshell.aas.v3.dataformat.aml.aml2aas;

import io.adminshell.aas.v3.dataformat.aml.aml2aas.custom.AssetAdministrationShellEnvironmentMapper;
import io.adminshell.aas.v3.dataformat.aml.AmlDeserializationConfig;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXFile;
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import io.adminshell.aas.v3.dataformat.mapping.MappingProvider;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import java.util.List;

public class Aml2AasMapper {

    private AmlDeserializationConfig config;

    public Aml2AasMapper(AmlDeserializationConfig config) {
        this.config = config;
    }

    public AssetAdministrationShellEnvironment map(CAEXFile aml) throws MappingException {
        // unclear how to handle additional information
        List<Object> additionalInformation = aml.getAdditionalInformation();
        // find AAS elements throughout all instance hierarchies
        AmlParser parser = new AmlParser(aml);
        MappingProvider mappingProvider = new MappingProvider(Mapper.class, new DefaultMapper(), new DefaultMapper());
        mappingProvider.register(new AssetAdministrationShellEnvironmentMapper());
        AasTypeFactory typeFactory = new AasTypeFactory();
        MappingContext context = new MappingContext(mappingProvider, typeFactory);
        Object result = context.getMappingProvider().getMapper(AssetAdministrationShellEnvironment.class).map(parser, context);
        String foo = "";
        return (AssetAdministrationShellEnvironment) result;
    }
}
