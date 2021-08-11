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

import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.dataformat.aml.AmlDeserializer;
import io.adminshell.aas.v3.dataformat.aml.fixtures.TestExample;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import java.io.FileNotFoundException;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;

public class AmlDeserializerTest {

    private final AmlDeserializer deserializer = new AmlDeserializer();

    @Test
    @Ignore
    public void testExample() throws FileNotFoundException, DeserializationException {
        AssetAdministrationShellEnvironment actual = deserializer.read(TestExample.FILE);
        assertEquals(TestExample.ENVIRONMENT, actual);
    }
}
