/*
Copyright (c) 2021 Fraunhofer IOSB-INA Lemgo,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IOSB-ILT Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IAIS,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IESE,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

Copyright (c) 2021 Fraunhofer IWU Karlsruhe,
    eine rechtlich nicht selbstaendige Einrichtung der Fraunhofer-Gesellschaft
    zur Foerderung der angewandten Forschung e.V.

This source code is licensed under the Apache License 2.0 (see LICENSE.txt).

This source code may use other Open Source software components (see LICENSE.txt).
*/

package io.adminshell.aas.v3.dataformat.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.adminshell.aas.v3.dataformat.SerializationException;
import io.adminshell.aas.v3.dataformat.Serializer;
import io.adminshell.aas.v3.dataformat.json.serialization.EnumSerializer;
import io.adminshell.aas.v3.dataformat.json.modeltype.ModelTypeProcessor;
import io.adminshell.aas.v3.dataformat.json.serialization.EmbeddedDataSpecificationSerializer;
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
