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

package io.adminshell.aas.v3.dataformat.xml.enums;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UpperCamelCaseEnumNaming<T extends Enum> implements CustomEnumNaming<T> {

    private final Map<String, T> mapping;

    public UpperCamelCaseEnumNaming(Class<T> clazz) {
        PropertyNamingStrategy.PropertyNamingStrategyBase namingStrategy = new UpperSnakeCaseToUpperCamelCaseStrategy();
        mapping = Stream.of(clazz.getEnumConstants())
                .collect(Collectors.toMap(x -> namingStrategy.translate(x.name()), x -> x));
    }

    @Override
    public Map<String, T> getMapping() {
        return mapping;
    }

}
