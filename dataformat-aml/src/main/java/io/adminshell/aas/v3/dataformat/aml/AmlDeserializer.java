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

import io.adminshell.aas.v3.dataformat.DeserializationException;
import io.adminshell.aas.v3.dataformat.Deserializer;
import io.adminshell.aas.v3.dataformat.aml.aml2aas.Aml2AasObjectMapper;
import io.adminshell.aas.v3.dataformat.aml.model.caex.CAEXFile;
import io.adminshell.aas.v3.dataformat.mapping.MappingException;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import java.io.StringReader;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AmlDeserializer implements Deserializer {

    private static final Logger log = LoggerFactory.getLogger(AmlDeserializer.class);

    @Override
    public AssetAdministrationShellEnvironment read(String value) throws DeserializationException {
        try {
            Unmarshaller unmarshaller = JAXBContextFactory.createContext(new Class[]{CAEXFile.class}, null).createUnmarshaller();
            StringReader reader = new StringReader(value);
            CAEXFile aml = (CAEXFile) unmarshaller.unmarshal(reader);
            Aml2AasObjectMapper mapper = new Aml2AasObjectMapper(new Aml2AasConfig());
            return mapper.map(aml);
        } catch (JAXBException ex) {
            throw new DeserializationException("error deserializing AssetAdministrationShellEnvironment", ex);
        } catch (MappingException ex) {
            throw new DeserializationException("error mapping AML document to AssetAdministrationShellEnvironment", ex);
        }
    }

    @Override
    public <T> void useImplementation(Class<T> aasInterface, Class<? extends T> implementation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
