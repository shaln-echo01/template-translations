package com.echo.translator.validation;


import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;

import org.springframework.stereotype.Service;

import com.echo.translator.exception.GeminiTranslationException;



@Service
public class TemplateContentValidator {


    private static final Pattern VARIABLE_PATTERN =
            Pattern.compile(
                    "\\{\\{.*?\\}\\}"
            );



    public void validateVariables(
            String originalContent,
            String translatedContent) {


        Set<String> originalVariables =
                extractVariables(
                        originalContent
                );


        Set<String> translatedVariables =
                extractVariables(
                        translatedContent
                );


        if (!originalVariables.equals(
                translatedVariables)) {


            throw new GeminiTranslationException(
                    "Template variables changed during translation. "
                    + "Expected: "
                    + originalVariables
                    + " Found: "
                    + translatedVariables
            );
        }
    }



    private Set<String> extractVariables(
            String content) {


        Matcher matcher =
                VARIABLE_PATTERN.matcher(
                        content
                );


        Set<String> variables =
                new java.util.HashSet<>();


        while (matcher.find()) {

            variables.add(
                matcher.group()
            );
        }


        return variables;
    }
}