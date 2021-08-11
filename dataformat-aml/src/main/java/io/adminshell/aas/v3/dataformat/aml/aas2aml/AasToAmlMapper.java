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
package io.adminshell.aas.v3.dataformat.aml.aas2aml;

import io.adminshell.aas.v3.dataformat.aml.AmlSerializationConfig;
import io.adminshell.aas.v3.dataformat.aml.naming.PropertyNamingStrategy;
import io.adminshell.aas.v3.dataformat.aml.naming.AbstractClassNamingStrategy;
import io.adminshell.aas.v3.dataformat.aml.naming.NumberingClassNamingStrategy;
import io.adminshell.aas.v3.dataformat.aml.header.AutomationMLVersion;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.AssetAdministrationShellEnvironmentMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.AssetAdministrationShellMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.ConstraintCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.DataSpecificationContentMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.DataSpecificationIEC61360Mapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.EmbeddedDataSpecificationCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.FileMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.LangStringCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.OperationVariableCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.OperationVariableMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.QualifierMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.ReferenceCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.ReferenceElementMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.RelationshipElementMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.SubmodelMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.custom.ViewMapper;
import io.adminshell.aas.v3.dataformat.aml.id.IntegerIdGenerator;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXFile;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import io.adminshell.aas.v3.dataformat.mapping.MappingProvider;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.Qualifier;
import io.adminshell.aas.v3.model.Referable;
import org.slf4j.LoggerFactory;
import io.adminshell.aas.v3.dataformat.mapping.SourceBasedMapper;

public class AasToAmlMapper {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AasToAmlMapper.class);
    private static final String DEFAULT_LANGUAGE = "EN";
    private CAEXFile result;

    public CAEXFile map(AssetAdministrationShellEnvironment env, AmlSerializationConfig config) throws MappingException {
        AbstractClassNamingStrategy classNamingStrategy = new NumberingClassNamingStrategy();
        classNamingStrategy.registerCustomNaming(LangString.class, x -> "aml-lang=" + x.getLanguage());
        PropertyNamingStrategy propertyNamingStrategy = new PropertyNamingStrategy();
        propertyNamingStrategy.registerCustomNaming(Referable.class, "descriptions", "description");
        propertyNamingStrategy.registerCustomNaming(Qualifier.class, x -> "qualifier:" + x.getType() + "=" + x.getValue());
        propertyNamingStrategy.registerCustomNaming(ConceptDescription.class, "isCaseOfs", "isCaseOf");
        MappingProvider<SourceBasedMapper> mappingProvider = new MappingProvider<>(
                SourceBasedMapper.class,
                new DefaultMapper(),
                new DefaultCollectionMapper());

        mappingProvider.register(new AssetAdministrationShellEnvironmentMapper());
        mappingProvider.register(new LangStringCollectionMapper());
//        mappingProvider.register(new ReferenceMapper());
        mappingProvider.register(new AssetAdministrationShellMapper());
        mappingProvider.register(new OperationVariableMapper());
        mappingProvider.register(new OperationVariableCollectionMapper());
        mappingProvider.register(new ReferenceCollectionMapper());
        mappingProvider.register(new ConstraintCollectionMapper());
        mappingProvider.register(new QualifierMapper());
        mappingProvider.register(new FileMapper());
        mappingProvider.register(new SubmodelMapper());
        mappingProvider.register(new EmbeddedDataSpecificationCollectionMapper());
        mappingProvider.register(new DataSpecificationContentMapper());
        mappingProvider.register(new ReferenceElementMapper());
        mappingProvider.register(new RelationshipElementMapper());
        mappingProvider.register(new DataSpecificationIEC61360Mapper());
        mappingProvider.register(new ViewMapper());
        // FIX
        // ReferenceElement and RelationshipElement reference other Referables.
        // Each such reference is represented as an InternalLink in AML.
        // InternalLinks require target to be an interface but AAS AML serialization
        // does not define creation of such interfaces. Therefore, we do a preprocessing
        // step to identify all Referable which are targeted by such a reference
        // to later add a generic interface to them upon serialization

        // NOTE
        // This has implications on ID generator and requires additional lookup table
        // for Referable --> AML ID of ExternalInterface
        MappingContext context = new MappingContext(
                mappingProvider,
                classNamingStrategy,
                propertyNamingStrategy,
                env);
        CAEXFile.Builder builder = CAEXFile.builder()
                .withSchemaVersion(config.getSchemaVersion())
                .withFileName(config.getFilename())
                .addAdditionalInformation(new AutomationMLVersion(config.getAmlVersion()));
        if (config.getWriterInfo() != null) {
            builder = builder.addAdditionalInformation(config.getWriterInfo().wrap());
        }
        if (!config.getAdditionalInformation().isEmpty()) {
            builder = builder.addAdditionalInformation(config.getAdditionalInformation());
        }
        context.map(env, AmlGenerator.builder()
                .file(builder)
                .idGenerator(config.getIdGenerator())
                .build());
        return builder.build();
    }
}
