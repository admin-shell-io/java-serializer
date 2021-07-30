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

import io.adminshell.aas.v3.dataformat.aml.header.AutomationMLVersion;
import io.adminshell.aas.v3.dataformat.aml.header.WriterHeader;
import io.adminshell.aas.v3.dataformat.aml.mapper.AssetAdministrationShellEnvironmentMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.ConstraintCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.DataSpecificationContentMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.DataSpecificationIEC61360Mapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.DefaultCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.DefaultMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.EmbeddedDataSpecificationCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.FileMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.LangStringCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.MappingException;
import io.adminshell.aas.v3.dataformat.aml.mapper.OperationVariableCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.OperationVariableMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.QualifierMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.ReferenceCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.ReferenceElementMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.ReferenceMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.RelationshipElementMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.SubmodelCollectionMapper;
import io.adminshell.aas.v3.dataformat.aml.mapper.SubmodelMapper;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXFile;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.Referable;
import java.util.List;
import org.slf4j.LoggerFactory;

public class AasToAmlMapper {

    public static final String DEFAULT_SCHEMA_VERSION = "2.15";
    public static final String DEFAULT_AML_VERSION = "2.0";
    public static final String DEFAULT_FILENAME = "AssetAdministrationShellEnvironment.aml";
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(AasToAmlMapper.class);
    private static final String DEFAULT_LANGUAGE = "EN";
    private CAEXFile result;

    public CAEXFile map(AssetAdministrationShellEnvironment env) throws MappingException {
        return map(env, DEFAULT_SCHEMA_VERSION, DEFAULT_FILENAME);
    }

    public CAEXFile map(AssetAdministrationShellEnvironment env, String schemaVersion, String filename) throws MappingException {
        AbstractClassNamingStrategy classNamingStrategy = new NumberingClassNamingStrategy();
        classNamingStrategy.registerCustomNaming(LangString.class, x -> "aml-lang=" + x.getLanguage());
        PropertyNamingStrategy propertyNamingStrategy = new PropertyNamingStrategy();
        propertyNamingStrategy.registerCustomNaming(Referable.class, "descriptions", "description");
        MappingProvider mappingProvider = new MappingProvider(
                new DefaultMapper(),
                new DefaultCollectionMapper(),
                List.of(
                        new AssetAdministrationShellEnvironmentMapper(),
                        new SubmodelCollectionMapper(),
                        new LangStringCollectionMapper(),
                        new ReferenceMapper(),
                        new OperationVariableMapper(),
                        new OperationVariableCollectionMapper(),
                        new ReferenceCollectionMapper(),
                        new ConstraintCollectionMapper(),
                        new QualifierMapper(),
                        new FileMapper(),
                        new SubmodelMapper(),
                        new EmbeddedDataSpecificationCollectionMapper(),
                        new DataSpecificationContentMapper(),
                        new ReferenceElementMapper(),
                        new RelationshipElementMapper(),
                        new DataSpecificationIEC61360Mapper()),
                classNamingStrategy,
                propertyNamingStrategy
        );
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
        MappingContext context = new MappingContext(mappingProvider, env);
        context.getFileBuilder().withSchemaVersion(schemaVersion);
        context.getFileBuilder().withFileName(filename);
        context.getFileBuilder().withAdditionalInformation(new AutomationMLVersion("2.0"));
        WriterHeader writerHeader = new WriterHeader();
        writerHeader.setName("foo");
        writerHeader.setId("bar");
        context.getFileBuilder().withAdditionalInformation(writerHeader.wrap());
        context.map(env);
        return context.getFileBuilder().build();
    }
}
