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

import io.adminshell.aas.v3.dataformat.aml.AmlGenerator;
import io.adminshell.aas.v3.dataformat.aml.AmlSerializationConfig;
import io.adminshell.aas.v3.dataformat.aml.naming.PropertyNamingStrategy;
import io.adminshell.aas.v3.dataformat.aml.naming.AbstractClassNamingStrategy;
import io.adminshell.aas.v3.dataformat.aml.naming.NumberingClassNamingStrategy;
import io.adminshell.aas.v3.dataformat.aml.header.AutomationMLVersion;
import io.adminshell.aas.v3.dataformat.aml.header.WriterHeader;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.AssetAdministrationShellEnvironmentMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.ConstraintCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.DataSpecificationContentMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.DataSpecificationIEC61360Mapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.DefaultCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.DefaultMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.EmbeddedDataSpecificationCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.FileMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.LangStringCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.OperationVariableCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.OperationVariableMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.QualifierMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.ReferenceCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.ReferenceElementMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.ReferenceMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.RelationshipElementMapper;
import io.adminshell.aas.v3.dataformat.aml.aas2aml.mapper.SubmodelMapper;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXFile;
import io.adminshell.aas.v3.dataformat.mapping.Mapper;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import io.adminshell.aas.v3.dataformat.mapping.MappingProvider;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.ConceptDescription;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.Qualifier;
import io.adminshell.aas.v3.model.Referable;
import org.slf4j.LoggerFactory;

public class AasToAmlMapper extends Mapper<AssetAdministrationShellEnvironment, CAEXFile, AmlSerializationConfig> {

    public static final String DEFAULT_SCHEMA_VERSION = "2.15";
    public static final String DEFAULT_AML_VERSION = "2.0";
    public static final String DEFAULT_FILENAME = "AssetAdministrationShellEnvironment.aml";
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AasToAmlMapper.class);
    private static final String DEFAULT_LANGUAGE = "EN";
    private CAEXFile result;

    public AasToAmlMapper(AmlSerializationConfig config) {
        super(config);
    }

    @Override
    public CAEXFile map(AssetAdministrationShellEnvironment env) throws MappingException {
        return map(env, DEFAULT_SCHEMA_VERSION, DEFAULT_FILENAME);
    }

    public CAEXFile map(AssetAdministrationShellEnvironment env, String schemaVersion, String filename) throws MappingException {
        AbstractClassNamingStrategy classNamingStrategy = new NumberingClassNamingStrategy();
        classNamingStrategy.registerCustomNaming(LangString.class, x -> "aml-lang=" + x.getLanguage());
        PropertyNamingStrategy propertyNamingStrategy = new PropertyNamingStrategy();
        propertyNamingStrategy.registerCustomNaming(Referable.class, "descriptions", "description");
        propertyNamingStrategy.registerCustomNaming(Qualifier.class, x -> "qualifier:" + x.getType() + "=" + x.getValue());
        propertyNamingStrategy.registerCustomNaming(ConceptDescription.class, "isCaseOfs", "isCaseOf");
        MappingProvider mappingProvider = new MappingProvider(
                new DefaultMapper(),
                new DefaultCollectionMapper());

        mappingProvider.register(new AssetAdministrationShellEnvironmentMapper());
        mappingProvider.register(new LangStringCollectionMapper());
        mappingProvider.register(new ReferenceMapper());
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
        Aas2AmlMappingContext context = new Aas2AmlMappingContext(
                mappingProvider,
                config.getIdGenerator(),
                classNamingStrategy,
                propertyNamingStrategy,
                env);
        WriterHeader writerHeader = new WriterHeader();
        writerHeader.setName("foo");
        writerHeader.setId("bar");
        CAEXFile.Builder builder = CAEXFile.builder()
                .withSchemaVersion(schemaVersion)
                .withFileName(filename)
                .addAdditionalInformation(new AutomationMLVersion("2.0"))
                .addAdditionalInformation(writerHeader.wrap());
        AmlGenerator generator = AmlGenerator.builder()
                .file(builder)
                .build();
        context.map(env, generator);
        return builder.build();
    }
}
