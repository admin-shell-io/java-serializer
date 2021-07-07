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
package io.adminshell.aas.v3.dataformat.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.adminshell.aas.v3.dataformat.core.ReflectionHelper;

import io.adminshell.aas.v3.dataformat.SerializationException;
import io.adminshell.aas.v3.dataformat.Serializer;
import io.adminshell.aas.v3.dataformat.core.serialization.EnumSerializer;
import io.adminshell.aas.v3.dataformat.json.modeltype.ModelTypeProcessor;
import io.adminshell.aas.v3.dataformat.core.serialization.EmbeddedDataSpecificationSerializer;
import io.adminshell.aas.v3.model.AssetAdministrationShellEnvironment;
import io.adminshell.aas.v3.model.EmbeddedDataSpecification;

/**
 * Class for serializing an instance of AssetAdministrationShellEnvironment to
 * JSON.
 */
public class JsonSerializer implements Serializer {

    protected JsonMapper mapper;

    public JsonSerializer() {
        buildMapper();
    }

    protected void buildMapper() {
        mapper = JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT)
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .addModule(buildEnumModule())
                .addModule(buildCustomDeserializerModule())
                .annotationIntrospector(new ReflectionAnnotationIntrospector())
                .build();
        ReflectionHelper.MIXINS.entrySet().forEach(x -> mapper.addMixIn(x.getKey(), x.getValue()));
    }

    protected SimpleModule buildCustomDeserializerModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(EmbeddedDataSpecification.class, new EmbeddedDataSpecificationSerializer());
        return module;
    }

    protected SimpleModule buildEnumModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(Enum.class, new EnumSerializer());
        return module;
    }

    @Override
    public String write(AssetAdministrationShellEnvironment aasEnvironment) throws SerializationException {
        try {
            return mapper.writeValueAsString(ModelTypeProcessor.postprocess(mapper.valueToTree(aasEnvironment)));
        } catch (JsonProcessingException ex) {
            throw new SerializationException("error serializing AssetAdministrationShellEnvironment", ex);
        }
    }
}
