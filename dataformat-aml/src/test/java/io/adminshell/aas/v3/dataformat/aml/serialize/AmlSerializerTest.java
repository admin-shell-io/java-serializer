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
package io.adminshell.aas.v3.dataformat.aml.serialize;

import io.adminshell.aas.v3.dataformat.aml.fixtures.FullExample;
import io.adminshell.aas.v3.dataformat.SerializationException;
import io.adminshell.aas.v3.dataformat.aml.AmlSerializationConfig;
import io.adminshell.aas.v3.dataformat.aml.AmlSerializer;
import io.adminshell.aas.v3.dataformat.aml.id.IntegerIdGenerator;
import io.adminshell.aas.v3.dataformat.aml.fixtures.TestExample;
import io.adminshell.aas.v3.dataformat.core.util.AasUtils;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.DataTypeIEC61360;
import io.adminshell.aas.v3.model.IdentifierType;
import io.adminshell.aas.v3.model.KeyElements;
import io.adminshell.aas.v3.model.KeyType;
import io.adminshell.aas.v3.model.LangString;
import io.adminshell.aas.v3.model.ModelingKind;
import io.adminshell.aas.v3.model.Submodel;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShell;
import io.adminshell.aas.v3.model.impl.DefaultAssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.impl.DefaultDataSpecificationIEC61360;
import io.adminshell.aas.v3.model.impl.DefaultEmbeddedDataSpecification;
import io.adminshell.aas.v3.model.impl.DefaultIdentifier;
import io.adminshell.aas.v3.model.impl.DefaultKey;
import io.adminshell.aas.v3.model.impl.DefaultOperation;
import io.adminshell.aas.v3.model.impl.DefaultOperationVariable;
import io.adminshell.aas.v3.model.impl.DefaultProperty;
import io.adminshell.aas.v3.model.impl.DefaultReference;
import io.adminshell.aas.v3.model.impl.DefaultReferenceElement;
import io.adminshell.aas.v3.model.impl.DefaultSubmodel;
import io.adminshell.aas.v3.model.impl.DefaultSubmodelElementCollection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;
import org.xmlunit.diff.ElementSelectors;

public class AmlSerializerTest {

    private final AmlSerializer serializer = new AmlSerializer();

    @Test
    @Ignore
    public void testExample() throws SerializationException, SAXException, IOException {
        validateAmlSerializer(TestExample.FILE, TestExample.ENVIRONMENT);

    }

    @Test
//    @Ignore
    public void testReferenceElement() throws SerializationException, SAXException, IOException {
        Submodel submodel = new DefaultSubmodel.Builder()
                .idShort("submodel1")
                .identification(new DefaultIdentifier.Builder()
                        .idType(IdentifierType.IRI)
                        .identifier("iri:submodel1")
                        .build())
                .kind(ModelingKind.INSTANCE)
                .submodelElement(new DefaultProperty.Builder()
                        .idShort("property1")
                        .build())
                .submodelElement(new DefaultOperation.Builder()
                        .idShort("operation1")
                        .inputVariable(new DefaultOperationVariable.Builder()
                                .value(new DefaultSubmodelElementCollection.Builder()
                                        .idShort("submodelElementCollection1")
                                        .value(new DefaultReferenceElement.Builder()
                                                .idShort("refElement1")
                                                .value(new DefaultReference.Builder()
                                                        .key(new DefaultKey.Builder()
                                                                .idType(KeyType.IRI)
                                                                .type(KeyElements.SUBMODEL)
                                                                .value("iri:submodel1")
                                                                .build())
                                                        .key(new DefaultKey.Builder()
                                                                .idType(KeyType.ID_SHORT)
                                                                .type(KeyElements.PROPERTY)
                                                                .value("property1")
                                                                .build())
                                                        .build())
                                                .build())
                                        .value(new DefaultReferenceElement.Builder()
                                                .idShort("refElement2")
                                                .value(new DefaultReference.Builder()
                                                        .key(new DefaultKey.Builder()
                                                                .idType(KeyType.IRI)
                                                                .type(KeyElements.SUBMODEL)
                                                                .value("iri:submodel1")
                                                                .build())
                                                        .key(new DefaultKey.Builder()
                                                                .idType(KeyType.ID_SHORT)
                                                                .type(KeyElements.OPERATION)
                                                                .value("operation1")
                                                                .build())
                                                        .key(new DefaultKey.Builder()
                                                                .idType(KeyType.ID_SHORT)
                                                                .type(KeyElements.SUBMODEL_ELEMENT_COLLECTION)
                                                                .value("submodelElementCollection1")
                                                                .build())
                                                        .key(new DefaultKey.Builder()
                                                                .idType(KeyType.ID_SHORT)
                                                                .type(KeyElements.REFERENCE_ELEMENT)
                                                                .value("refElement1")
                                                                .build())
                                                        .build())
                                                .build())
                                        .build())
                                .build())
                        .build())
                .build();
        AssetAdministrationShellEnvironment environment = new DefaultAssetAdministrationShellEnvironment.Builder()
                .assetAdministrationShells(new DefaultAssetAdministrationShell.Builder()
                        .identification(new DefaultIdentifier.Builder()
                                .idType(IdentifierType.IRI)
                                .identifier("iri:AAS1")
                                .build())
                        .idShort("AAS1")
                        .submodel(AasUtils.identifiableToReference(submodel))
                        .build())
                .submodels(submodel)
                .build();
        String actual = new AmlSerializer().write(environment, AmlSerializationConfig.builder()
                .idGenerator(new IntegerIdGenerator())
                .build());
        System.out.println(actual);

    }

    @Test
    public void testEmbeddedDataSpecification() throws SerializationException, SAXException, IOException {
        Submodel submodel = new DefaultSubmodel.Builder()
                .idShort("submodel1")
                .identification(new DefaultIdentifier.Builder()
                        .idType(IdentifierType.IRI)
                        .identifier("iri:submodel1")
                        .build())
                .kind(ModelingKind.INSTANCE)
                .embeddedDataSpecification(new DefaultEmbeddedDataSpecification.Builder()
                        .dataSpecificationContent(new DefaultDataSpecificationIEC61360.Builder()
                                .dataType(DataTypeIEC61360.INTEGER_COUNT)
                                .preferredName(new LangString("de", "preferredName1"))
                                .shortName(new LangString("de", "shortName1"))
                                .symbol("some symbol1")
                                .value("value1")
                                .unit("unit1")
                                .build())
                        .build())
                .embeddedDataSpecification(new DefaultEmbeddedDataSpecification.Builder()
                        .dataSpecificationContent(new DefaultDataSpecificationIEC61360.Builder()
                                .dataType(DataTypeIEC61360.INTEGER_COUNT)
                                .preferredName(new LangString("de", "preferredName2"))
                                .shortName(new LangString("de", "shortName2"))
                                .symbol("some symbol2")
                                .value("value2")
                                .unit("unit2")
                                .build())
                        .build())
                .build();
        AssetAdministrationShellEnvironment environment = new DefaultAssetAdministrationShellEnvironment.Builder()
                .assetAdministrationShells(new DefaultAssetAdministrationShell.Builder()
                        .identification(new DefaultIdentifier.Builder()
                                .idType(IdentifierType.IRI)
                                .identifier("iri:AAS1")
                                .build())
                        .idShort("AAS1")
                        .submodel(AasUtils.identifiableToReference(submodel))
                        .build())
                .submodels(submodel)
                .build();
        String actual = new AmlSerializer().write(environment, AmlSerializationConfig.builder()
                .idGenerator(new IntegerIdGenerator())
                .build());
        System.out.println(actual);

    }

    @Test
    @Ignore
    public void testSAPFullExample() throws SerializationException {
        String actual = serializer.write(FullExample.ENVIRONMENT);
    }

    private void validateAmlSerializer(File expectedFile, AssetAdministrationShellEnvironment environment)
            throws SerializationException, SAXException, IOException {
        String expected = Files.readString(expectedFile.toPath());
        String actual = new AmlSerializer().write(environment, AmlSerializationConfig.builder()
                .idGenerator(new IntegerIdGenerator())
                .build());
        System.out.println(actual);
        Diff diff = DiffBuilder
                .compare(actual)
                .withTest(expected)
                //                .checkForSimilar()
                //                .checkForIdentical()
                .normalizeWhitespace()
                .withNodeMatcher(new DefaultNodeMatcher(ElementSelectors.byNameAndText))
                .ignoreComments()
                .ignoreWhitespace()
                .withNodeFilter(node -> !node.getNodeName().equals("LastWritingDateTime"))
                .build();
        Iterator<Difference> iter = diff.getDifferences().iterator();
        int size = 0;
        while (iter.hasNext()) {
            System.out.println(iter.next());
            size++;
        }
        assert (size == 0);
    }

}
