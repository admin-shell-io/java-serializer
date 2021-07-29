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
package io.adminshell.aas.v3.dataformat.xml;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlAnnotationIntrospector;

/**
 * This class helps to dynamically decide how to de-/serialize classes and
 * properties defined in the AAS model library. It will automatically add a default namespace
 * to property names.
 */
public class XmlDataformatAnnotationIntrospector extends JacksonXmlAnnotationIntrospector {
    private static final long serialVersionUID = 1L;

    protected String myDefaultNamespace = "";

    public XmlDataformatAnnotationIntrospector() {
        super();
        myDefaultNamespace = AasXmlNamespaceContext.AAS_URI;
    }

    @Override
    public String findNamespace(Annotated ann) {
        String ns = super.findNamespace(ann);
        if (ns == null) {
            return myDefaultNamespace;
        } else {
            return ns;
        }
    }
}
