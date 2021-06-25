package io.adminshell.aas.v3.dataformat.json.enums;

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
