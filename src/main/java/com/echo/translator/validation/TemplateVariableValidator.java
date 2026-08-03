package com.echo.translator.validation;


import java.util.regex.Pattern;

import org.springframework.stereotype.Service;



@Service
public class TemplateVariableValidator {


    private static final Pattern VARIABLE =
            Pattern.compile(
                "\\{\\{.*?\\}\\}"
            );



    public boolean containsVariables(
            String content) {


        return VARIABLE
                .matcher(content)
                .find();
    }
}