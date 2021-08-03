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
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
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
//    @Ignore
    public void testExample() throws SerializationException, SAXException, IOException {
        validateAmlSerializer(TestExample.FILE, TestExample.ENVIRONMENT);

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
