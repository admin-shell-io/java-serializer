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

public class UpperSnakeCaseToUpperCamelCaseStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase {

    @Override
    public String translate(String input) {
        return handleUnderscores(handleFirstLetter(input.toLowerCase()));
    }

    private String handleUnderscores(String withUnderscores) {
        String withCamelCase = "";

        boolean charToUpperCase = false;
        for (int i = 0; i < withUnderscores.length(); i++) {
            char currentChar = withUnderscores.charAt(i);
            if (charToUpperCase) {
                withCamelCase += Character.toUpperCase(currentChar);
                charToUpperCase = false;
            } else if (currentChar == '_') {
                charToUpperCase = true;
            } else {
                withCamelCase += currentChar;
            }
        }
        return withCamelCase;
    }

    private String handleFirstLetter(String lowerCase) {
        return lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
    }

}
